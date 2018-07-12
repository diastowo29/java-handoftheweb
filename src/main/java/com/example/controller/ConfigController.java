package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Config;
import com.example.repo.ConfigRepository;

@RestController
@CrossOrigin
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
			hMap.put("agent", conf.getAgent());
			list.add(hMap);
		}
		hMap = new HashMap<>();
		hMap.put("config", list);
		return hMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/id")
	public ResponseEntity<Object> getConfigById(@RequestParam("id") long id) {
		HashMap<String, Object> hMap = new HashMap<>();
		HashMap<String, Object> configMap = new HashMap<>();
		Config conf = configRepo.findById(id).get();
		System.out.println(conf.getTitle());
		configMap.put("id", conf.getId());
		configMap.put("domain", conf.getDomain());
		configMap.put("title", conf.getTitle());
		configMap.put("config", conf.getConfig());
		configMap.put("duration", conf.getDuration());
		configMap.put("agent", conf.getAgent());

		hMap.put("config", configMap);
		return new ResponseEntity<Object>(hMap, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postDomain(@RequestBody Map<String, String> paramMap) {
		HashMap<String, Object> hMap = new HashMap<>();
		hMap.put("method", "POST");
		Config config = configRepo.save(new Config(paramMap.get("domain"), paramMap.get("config"),
				paramMap.get("duration"), paramMap.get("title"), paramMap.get("agent")));
		hMap.put("id", config.getId());
		return new ResponseEntity<Object>(hMap, HttpStatus.OK);
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
