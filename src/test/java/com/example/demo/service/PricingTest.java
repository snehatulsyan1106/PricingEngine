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
		PriceListResponse priceList = new PriceListResponse(484, 968, 1452, 605, 1331);
		Response expectedResponse = new Response(1, 4840, priceList, "Success", 2018);
		Request request = new Request(1, new ChainAssembly(2), new Frames("carbonFiber"),
				new HandleBarBrake("steel", "round", "disc"), new Seating("leather"), new Wheels(40, "steel", true),
				2018);

		Pricing pricing = new Pricing(request, null);
		Response actualResponse = pricing.pricingEngine();
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

}
