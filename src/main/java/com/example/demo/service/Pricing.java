package com.example.demo.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.demo.constants.ConstantTerms;
import com.example.demo.models.PriceListResponse;
import com.example.demo.models.Request;
import com.example.demo.models.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pricing implements Runnable {

	private Request request;
	private FileWriter fileWriter;

	public Pricing(Request request, FileWriter fileWriter) {
		super();
		this.request = request;
		this.fileWriter = fileWriter;
	}

	public Response pricingEngine() {
		int totalPrice = 0;
		int chainPrice = 0;
		int framePrice = 0;
		int handlePrice = 0;
		int seatingPrice = 0;
		int wheelPrice = 0;
		int yearDiff = 0;

		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
			properties.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (request.getYear() < ConstantTerms.BASEYEAR) {
			return new Response(request.getId(), 0, null,
					"Model below " + ConstantTerms.BASEYEAR + " are not supported", request.getYear());
		} else {
			yearDiff = request.getYear() - ConstantTerms.BASEYEAR;
		}
		PriceListResponse priceListResponse = new PriceListResponse();
		if (request.getChainAssembly() != null) {
			if (request.getChainAssembly().getGears() > 4) {
				return new Response(request.getId(), 0, null, "Max 4 gears Supported", request.getYear());
			}

			else if (request.getChainAssembly().getGears() != 0) {
				chainPrice = chainPrice + request.getChainAssembly().getGears()
						* Integer.parseInt(properties.getProperty("chain.default"));
			} else {
				chainPrice = chainPrice + Integer.parseInt(properties.getProperty("chain.default"));
			}
		}

		else {
			chainPrice = chainPrice + Integer.parseInt(properties.getProperty("chain.default"));
		}

		chainPrice = (int) Math.round(chainPrice * Math.pow(1 + (ConstantTerms.INFLATION), yearDiff));
		priceListResponse.setChainAssembly(chainPrice);
//		---------------------------------------------------------
		if (request.getFrames() != null) {
			if (request.getFrames().getMaterial() != null) {

				String price = properties.getProperty("frames." + request.getFrames().getMaterial());
				if (price != null) {
					framePrice = framePrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "Frame Material Not Supported", request.getYear());
				}
			} else {
				framePrice = framePrice + Integer.parseInt(properties.getProperty("frames.default"));
			}

		} else {
			framePrice = framePrice + Integer.parseInt(properties.getProperty("frames.default"));
		}
		framePrice = (int) Math.round(framePrice * Math.pow(1 + (ConstantTerms.INFLATION), yearDiff));
		priceListResponse.setFrames(framePrice);
//		----------------------------------------------------------------------------
		if (request.getHandleAndBrakes() != null) {
			if (request.getHandleAndBrakes().getHandleMaterial() != null) {
				String price = properties
						.getProperty("handleMaterial." + request.getHandleAndBrakes().getHandleMaterial());
				if (price != null) {
					handlePrice = handlePrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "handle Material Not Supported", request.getYear());
				}
			} else {
				handlePrice = handlePrice + Integer.parseInt(properties.getProperty("handleMaterial.default"));
			}
			if (request.getHandleAndBrakes().getHandleDesign() != null) {
				String price = properties.getProperty("handleDesign." + request.getHandleAndBrakes().getHandleDesign());
				if (price != null) {
					handlePrice = handlePrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "handle Design Material Not Supported",
							request.getYear());
				}
			} else {
				handlePrice = handlePrice + Integer.parseInt(properties.getProperty("handleMaterial.default"));
			}
			if (request.getHandleAndBrakes().getBrakes() != null) {
				String price = properties.getProperty("brakes." + request.getHandleAndBrakes().getBrakes());
				if (price != null) {
					handlePrice = handlePrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "brakes Material Not Supported", request.getYear());
				}
			} else {
				handlePrice = handlePrice + Integer.parseInt(properties.getProperty("brakes.default"));
			}

		} else {
			handlePrice = handlePrice + Integer.parseInt(properties.getProperty("handleMaterial.default"))
					+ Integer.parseInt(properties.getProperty("handleDesign.default"))
					+ Integer.parseInt(properties.getProperty("brakes.default"));
		}

		handlePrice = (int) Math.round(handlePrice * Math.pow(1 + (ConstantTerms.INFLATION), yearDiff));
		priceListResponse.setHandleAndBrakes(handlePrice);
//		----------------------------------------------------------------
		if (request.getSeating() != null) {
			if (request.getSeating().getSeatingMaterial() != null) {

				String price = properties.getProperty("seatingMaterial." + request.getSeating().getSeatingMaterial());
				if (price != null) {
					seatingPrice = seatingPrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "seating Material Not Supported", request.getYear());
				}
			} else {
				seatingPrice = seatingPrice + Integer.parseInt(properties.getProperty("seatingMaterial.default"));
			}

		} else {
			seatingPrice = seatingPrice + Integer.parseInt(properties.getProperty("seatingMaterial.default"));
		}
		seatingPrice = (int) Math.round(seatingPrice * Math.pow(1 + (ConstantTerms.INFLATION), yearDiff));
		priceListResponse.setSeating(seatingPrice);
//		--------------------------------------------------------------------------------
		if (request.getWheels() != null) {
			if (request.getWheels().getRimMaterial() != null) {

				String price = properties.getProperty("rimMaterial." + request.getWheels().getRimMaterial());
				if (price != null) {
					wheelPrice = wheelPrice + Integer.parseInt(price);
				} else {
					return new Response(request.getId(), 0, null, "rim Material Not Supported", request.getYear());
				}
			} else {
				wheelPrice = wheelPrice + Integer.parseInt(properties.getProperty("rimMaterial.default"));
			}
			if (request.getWheels().getSpokes() > 40) {

				return new Response(request.getId(), 0, null, "spokes upto 40 are Supported", request.getYear());

			}

			else if (request.getWheels().getSpokes() != 0) {

				String price = properties.getProperty("spokes.default");
				wheelPrice = wheelPrice + (Integer.parseInt(price) * request.getWheels().getSpokes());
			} else {
				wheelPrice = wheelPrice + (Integer.parseInt(properties.getProperty("spokes.default")) * 10);
			}

			if (request.getWheels().isTube()) {
				String price = properties.getProperty("tube.default");
				wheelPrice = wheelPrice + Integer.parseInt(price);
				String price1 = properties.getProperty("tyreMaterial.default");
				wheelPrice = wheelPrice + Integer.parseInt(price1);
			} else {
				String price1 = properties.getProperty("tyreMaterial.tubeless");
				wheelPrice = wheelPrice + Integer.parseInt(price1);
			}

		} else {
			wheelPrice = wheelPrice + (Integer.parseInt(properties.getProperty("spokes.default")) * 10)
					+ Integer.parseInt(properties.getProperty("rimMaterial.default"))
					+ Integer.parseInt(properties.getProperty("tube.default"))
					+ Integer.parseInt(properties.getProperty("tyreMaterial.default"));
		}

		wheelPrice = (int) Math.round(wheelPrice * Math.pow(1 + (ConstantTerms.INFLATION), yearDiff));
		priceListResponse.setWheels(wheelPrice);
		totalPrice = wheelPrice + seatingPrice + framePrice + chainPrice + handlePrice;
		Response response = new Response(request.getId(), totalPrice, priceListResponse, ConstantTerms.SUCCESS,
				request.getYear());

		return response;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
			ObjectMapper mapper = new ObjectMapper();
			Response response = pricingEngine();
			System.out.println(response.toString());
			fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
			fileWriter.flush();
			System.out.println("Thread id : " + Thread.currentThread().getId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
