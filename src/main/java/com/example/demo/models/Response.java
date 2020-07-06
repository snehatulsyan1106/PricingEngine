package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Response {
	private int id;
	private int price;
	private PriceListResponse priceDistribution;
	private String msg;
	private int year;

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Response(int id, int price, PriceListResponse priceDistribution, String msg, int year) {
		super();
		this.id = id;
		this.price = price;
		this.priceDistribution = priceDistribution;
		this.msg = msg;
		this.year = year;
	}

	public Response() {
		super();
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
