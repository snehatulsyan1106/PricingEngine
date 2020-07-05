package com.example.demo.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.stereotype.Service;

import com.example.demo.constants.FileMapping;
import com.example.demo.models.Request;

@Service
public class Executor {

	public void executeEngine(BlockingQueue<Request> blockingQueue) throws InterruptedException, IOException {

		FileWriter fileWriter = new FileWriter(FileMapping.responseJsonFile);
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		while (!blockingQueue.isEmpty()) {
			executor.execute(new Pricing(blockingQueue.take(), fileWriter));
		}

	}

}
