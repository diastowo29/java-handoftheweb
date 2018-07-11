package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Config;
import com.example.repo.ConfigRepository;

@RestController
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	ConfigRepository configRepo;

	@RequestMapping(method = RequestMethod.GET)
	public HashMap<String, Object> getByDomain(@RequestParam("domain") String domain) {
		HashMap<String, Object> hMap = new HashMap<>();
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		for (Config conf : configRepo.findByDomain(domain)) {
			hMap = new HashMap<>();
			hMap.put("id", conf.getId());
			hMap.put("domain", conf.getDomain());
			hMap.put("title", conf.getTitle());
			hMap.put("duration", conf.getDuration());
			hMap.put("config", conf.getConfig());
			list.add(hMap);
		}
		hMap = new HashMap<>();
		hMap.put("config", list);
		return hMap;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postDomain(@RequestBody Map<String, String> paramMap) {
		HashMap<String, Object> hMap = new HashMap<>();
		hMap.put("method", "POST");
		/*
		 * if (paramMap.get("domain") == null || paramMap.get("domain") == null ||
		 * paramMap.get("domain") == null || ) { hMap.put("reason",
		 * "cannot proceed with blank/null value"); return new
		 * ResponseEntity<Object>(hMap, HttpStatus.BAD_REQUEST); } else if
		 * (paramMap.get("domain").isEmpty()) { hMap.put("reason",
		 * "cannot proceed with blank/null value"); return new
		 * ResponseEntity<Object>(hMap, HttpStatus.BAD_REQUEST); } else {
		 */
		Config config = configRepo.save(new Config(paramMap.get("domain"), paramMap.get("config"),
				paramMap.get("duration"), paramMap.get("title")));
		hMap.put("id", config.getId());
		return new ResponseEntity<Object>(hMap, HttpStatus.OK);
		/* } */
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public HashMap<String, Object> getAllDomain() {
		HashMap<String, Object> hMap = new HashMap<>();
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		for (Config conf : configRepo.findAll()) {
			hMap = new HashMap<>();
			hMap.put("id", conf.getId());
			hMap.put("domain", conf.getDomain());
			hMap.put("title", conf.getTitle());
			hMap.put("duration", conf.getDuration());
			hMap.put("config", conf.getConfig());
			list.add(hMap);
		}
		hMap = new HashMap<>();
		hMap.put("config", list);
		return hMap;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteConfig(@RequestParam("id") long id) {
		configRepo.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.GONE);
	}
}
