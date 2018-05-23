package cn.betatown.itplatform.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrJTest {

	// 指定solr服务器的地址
	private final static String SOLR_URL = "http://localhost:8983/solr/";

	/**
	 * 创建SolrServer对象
	 * 
	 * 该对象有两个可以使用，都是线程安全的 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的 2、
	 * EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了 3、solr
	 * 4.0之后好像添加了不少东西，其中CommonsHttpSolrServer这个类改名为HttpSolrClient
	 * 
	 * @return
	 */
	public HttpSolrClient createSolrServer() {
		HttpSolrClient solr = null;
		solr = new HttpSolrClient(SOLR_URL);
		return solr;
	}

	/**
	 * 往索引库添加文档
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void addDoc() throws SolrServerException, IOException {

		HttpSolrClient solr = new HttpSolrClient(SOLR_URL + "first-core");
		// 构造一篇文档

		List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
		for (int i = 0; i < 1000000; i++) {
			SolrInputDocument document = new SolrInputDocument();
			// 往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
			document.addField("id", i + "");
			document.addField("name", "周新星"+i);
			document.addField("description", "一个灰常牛逼的军事家");
			
			// 获得一个solr服务端的请求，去提交 ,选择具体的某一个solr core

			int c = (i%10);
			document.addField("category", c);
			documents.add(document);
		}
		
		solr.add(documents);
		//solr.commit(true, true);
		solr.commit();
		
		solr.optimize();
		solr.close();
	}

	/**
	 * 根据id从索引库删除文档
	 */
	public void deleteDocumentById() throws Exception {
		// 选择具体的某一个solr core
		HttpSolrClient server = new HttpSolrClient(SOLR_URL + "first-core");
		// 删除文档
		server.deleteById("8");
		// 删除所有的索引
		// solr.deleteByQuery("*:*");
		// 提交修改
		server.commit();
		server.close();
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void querySolr() throws Exception {
		HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL + "first-core/");
		SolrQuery query = new SolrQuery();
		// 下面设置solr查询参数
		// query.set("q", "*:*");// 参数q 查询所有
		query.set("q", "*:*");// 相关查询，比如某条数据某个字段含有周、星、驰三个字 将会查询出来 ，这个作用适用于联想查询

		// 参数fq, 给query增加过滤查询条件
		query.addFilterQuery("id:[0 TO 9]");// id为0-4
		
		// 给query增加布尔过滤条件
		// query.addFilterQuery("description:演员"); //description字段中含有“演员”两字的数据

		// 参数df,给query设置默认搜索域
		query.set("df", "name");

		// 参数sort,设置返回结果的排序规则
		query.setSort("id", SolrQuery.ORDER.desc);

		// 设置分页参数
		query.setStart(0);
		query.setRows(10);// 每一页多少值

		// 参数hl,设置高亮
		query.setHighlight(true);
		// 设置高亮的字段
		query.addHighlightField("name");
		// 设置高亮的样式
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");

		// 获取查询结果
		QueryResponse response = solrServer.query(query);
		// 两种结果获取：得到文档集合或者实体对象

		System.out.println(response.toString());
		System.out.println(response.getResults());
		
		// 查询得到文档的集合
//		SolrDocumentList solrDocumentList = response.getResults();
//		System.out.println("通过文档集合获取查询的结果");
//		System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
//		// 遍历列表
//		for (SolrDocument doc : solrDocumentList) {
//			System.out.println(
//					"id:" + doc.get("id") + "   name:" + doc.get("name") + "    description:" + doc.get("description"));
//		}
//
//		// 得到实体对象
//		List<Person> tmpLists = response.getBeans(Person.class);
//		if (tmpLists != null && tmpLists.size() > 0) {
//			System.out.println("通过文档集合获取查询的结果");
//			for (Person per : tmpLists) {
//				System.out.println(
//						"id:" + per.getId() + "   name:" + per.getName() + "    description:" + per.getDescription());
//			}
//		}
	}

	public static void main(String[] args) throws Exception {
		long t1 = System.currentTimeMillis();
		SolrJTest solr = new SolrJTest();
		long t2 = System.currentTimeMillis();
		
		System.out.printf("初始化服务,用时%d毫秒\n", t2 - t1);
		// solr.createSolrServer();
		solr.addDoc();
		long t3 = System.currentTimeMillis();
		System.out.printf(String.format("插入数据,用时%d毫秒\n", t3 - t2));
		//solr.deleteDocumentById();
		long t4 = System.currentTimeMillis();
		System.out.printf(String.format("删除数据,用时%d毫秒\n", t4 - t3));
		solr.querySolr();
		long t5 = System.currentTimeMillis();
		System.out.printf(String.format("查询数据,用时%d毫秒\n", t5 - t4));
		
		//http://localhost:8983/solr/first-core/select?q=*:*&stats=true&stats.field=name
		//http://localhost:8983/solr/first-core/select?q=*:*&facet=true&facet.field=category&facet.mincount=-1
		//http://localhost:8983/solr/first-core/select?q=*:*&facet=true&facet.field=name&facet.mincount=-1
		//http://localhost:8983/solr/first-core/select?q=*:*&facet=true&facet.field=category&facet.field=name&facet.mincount=-1
		//http://localhost:8983/solr/first-core/select?q=*:*&facet=true&facet.field=category&facet.field=name&facet.mincount=-1&fq=category:2
		
		
		//http://localhost:8983/solr/first-core/select?q=*:*&stats=true&stats.field=category&stats.facet=category&stats.mincount=-1
//		SDR_B
//		USINESS_PERSPECTIVE -------------solr查询参 数:qt=/select&q=*:*&stats=true&stats.field=standard
//		_insurance_d&fq=sales_type_s:1+OR+sales_type_s:2+OR+sales_type_s:3&fq=level2code_s:0000000
//		0000003&fq=statistics_date_type_s:D&fq=version_s:20170512&stats.facet=level3code_s        
//		[BETATOWN-QJZK-APP] 2018-05-12 21:21:28 INFO  BtSolrTemplateImpl: 44 --------------- SDR_B
//		USINESS_PERSPECTIVE -------------solr获取连接耗时:0                                              
//		[BETATOWN-QJZK-APP] 2018-05-12 21:21:33 INFO  BtSolrTemplateImpl: 44 --------------- SDR_B
//		USINESS_PERSPECTIVE -------------solr执行查询耗时:5256  
		
		
	}
}
