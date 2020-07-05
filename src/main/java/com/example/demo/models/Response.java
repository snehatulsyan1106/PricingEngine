package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Response {
	private int id;
	private int price;
	private PriceListResponse priceDistribution;
	private String msg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public PriceListResponse getPriceDistribution() {
		return priceDistribution;
	}
	public void setPriceDistribution(PriceListResponse priceDistribution) {
		this.priceDistribution = priceDistribution;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public Response(int id, int price, PriceListResponse priceDistribution, String msg) {
		super();
		this.id = id;
		this.price = price;
		this.priceDistribution = priceDistribution;
		this.msg = msg;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	

}
