package zx.soft.sdn.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.model.Test;
import zx.soft.sdn.api.service.TestService;

/**
 * 控制器
 * 
 * @author xuran
 *
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService TestService;

	@RequestMapping(value = "/json/{key}/{value}", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> json(@PathVariable(value = "key") String key,
			@PathVariable(value = "value") String value) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(key, value);
		return jsonMap;
	}

	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public @ResponseBody String getNameById(int id) {
		return TestService.getNameById(id);
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public @ResponseBody String getIdByName(String name) {
		return TestService.getIdByName(name);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Test getIdByName(@PathVariable(value = "id") int id) {
		return TestService.getTestById(id);
	}

	@RequestMapping(value = "/index/{key}/{value}", method = RequestMethod.GET)
	public String index(@PathVariable(value = "key") String key, @PathVariable(value = "value") String value,
			Model model) {
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		return "index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(@RequestBody Test test) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("key", String.valueOf(test.getId()));
		jsonMap.put("value", test.getName());
		return jsonMap;
	}

	//JSON请求：curl -X POST --header 'Content-Type:application/json' --header 'Accept:application/json' http://localhost:8900/test --data '{"id":1,"name":"xuran"}'
	//XML请求：curl -X POST --header 'Content-Type:application/xml' --header 'Accept:application/xml' http://localhost:8900/test --data '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><test><id>1</id><name>xuran</name></test>'
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Test test(@RequestBody Test test) {
		return test;
	}
}
