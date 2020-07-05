package com.example.demo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.example.demo.models.ChainAssembly;
import com.example.demo.models.Frames;
import com.example.demo.models.HandleBarBrake;
import com.example.demo.models.PriceListResponse;
import com.example.demo.models.Request;
import com.example.demo.models.Response;
import com.example.demo.models.Seating;
import com.example.demo.models.Wheels;

class PricingTest {

	@Test
	void test() {
		PriceListResponse priceList = new PriceListResponse(400, 800, 1200, 500, 1100);
		Response expectedResponse = new Response(1, 4000, priceList, "Success");
		Request request = new Request(1, new ChainAssembly(2), new Frames("carbonFiber"),
				new HandleBarBrake("steel", "round", "disc"), new Seating("leather"), new Wheels(40, "steel", true));

		Pricing pricing = new Pricing(request, null);
		Response actualResponse = pricing.pricingEngine();
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

}
