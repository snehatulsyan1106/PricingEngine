package com.example.demo.controller;

import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.ConstantTerms;
import com.example.demo.constants.FileMapping;
import com.example.demo.models.Request;
import com.example.demo.models.Result;
import com.example.demo.service.Executor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class PricingEngineController {
	private static final Logger logger = LoggerFactory.getLogger(PricingEngineController.class);

	@Autowired
	Executor executor;

	@GetMapping("/test")
	public ResponseEntity<Result> hitAPI() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(FileMapping.requestJsonFile)) {
			Object obj = jsonParser.parse(reader);
			JSONArray cycleList = (JSONArray) obj;
			BlockingQueue<Request> blockingQueue = new ArrayBlockingQueue<Request>(cycleList.size());
			ObjectMapper m = new ObjectMapper();
			for (int i = 0; i < cycleList.size(); i++) {
				try {
					Request request = m.readValue(((JSONObject) cycleList.get(i)).toString(), Request.class);
					blockingQueue.add(request);
				} catch (JsonProcessingException e) {
					logger.info("Exception in parsing JSON for request: " + e.getMessage());
					return new ResponseEntity<Result>(new Result(ConstantTerms.FAILED), HttpStatus.BAD_REQUEST);
				}
			}
			executor.executeEngine(blockingQueue);
			return new ResponseEntity<Result>(new Result(ConstantTerms.SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Exception in calculating pricing : " + e.getMessage());
			return new ResponseEntity<Result>(new Result(ConstantTerms.FAILED), HttpStatus.CONFLICT);
		}
	}

}
