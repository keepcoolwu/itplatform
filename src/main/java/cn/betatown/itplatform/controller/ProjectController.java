package cn.betatown.itplatform.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.betatown.itplatform.service.project.ProjectService;

/**
 * Created By Donghua.Chen on 2018/1/9
 */
@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ResponseBody
	@RequestMapping(value = "/add/project-file", method = RequestMethod.POST)
	public ResponseEntity addProjectFile() {
		File file = new File("C:\\Users\\donghua.chen\\Desktop\\项目计划导入模板-2018011201 - 副本.mpp");
		projectService.readMmpFileToDB(file);
		return ResponseEntity.ok("导入成功!!");
	}

	@ResponseBody
	@RequestMapping(value = "/project-file/{batchNum}", method = RequestMethod.GET)
	public ResponseEntity writeProjectFile(@PathVariable("batchNum") String batchNum) {
		File file = new File("C:\\Users\\donghua.chen\\Desktop\\开办新公司 - 导出模板.mpp");
		projectService.writeMppFileToDB("C:\\Users\\donghua.chen\\Desktop", batchNum, file);
		return ResponseEntity.ok("导出成功");
	}

	//http://localhost:1111/project/all/2/100
	@ResponseBody
	@RequestMapping(value = "/project/all/{pageNum}/{pageSize}", produces = { "application/json;charset=UTF-8" })
	public Object findAllProject(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
		return projectService.findAllProject(pageNum, pageSize);
	}
}
