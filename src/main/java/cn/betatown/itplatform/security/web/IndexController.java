package cn.betatown.itplatform.security.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @项目名称：lyd-channel
 * @包名：com.lyd.channel.web @类描述：
 * @创建人：wyait @创建时间：2017-11-28 18:52 @version：V1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String index() {
		logger.debug("-------------index------------");
		return "index";
	}

	// http://localhost:8080/log?filename=BETATOWN-ITPLATFORM-error.log
	@RequestMapping(value = "/log", method = RequestMethod.GET) // 匹配的是href中的download请求
	public ResponseEntity<org.springframework.core.io.Resource> log(@RequestParam("filename") String filename,
			Model model) throws IOException {

		String downloadFilePath = "D:/log/logs";// 从我们的上传文件夹中去取

		File file = new File(downloadFilePath + File.separator + filename);// 新建一个文件
		org.springframework.core.io.Resource body = new FileSystemResource(file);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String header = request.getHeader("User-Agent").toUpperCase();
		HttpStatus status = HttpStatus.CREATED;

		String file_name = filename;
		try {
			if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
				file_name = URLEncoder.encode(file_name, "UTF-8");
				file_name = file_name.replace("+", "%20"); // IE下载文件名空格变+号问题
				status = HttpStatus.OK;
			} else {
				file_name = new String(file_name.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", file_name);
		headers.setContentLength(file.length());

		return new ResponseEntity<org.springframework.core.io.Resource>(body, headers, status);

	}

	@RequestMapping("/home")
	public String toHome() {
		logger.debug("===111-------------home------------");
		return "home";
	}

	@RequestMapping("/login")
	public String toLogin() {
		logger.debug("===111-------------login------------");
		return "login";
	}

	@RequestMapping("/{page}")
	public String toPage(@PathVariable("page") String page) {
		logger.debug("-------------toindex------------" + page);
		return page;
	}
}
