package com.nbr.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nbr.soap.domain.GetCountryNameRequest;
import com.nbr.soap.domain.GetCountryNameResponse;
import com.nbr.soap.domain.GetCountryRequest;
import com.nbr.soap.domain.GetCountryResponse;
import com.nbr.soap.repository.CountryRepository;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://nbrsolutions.com";
	private CountryRepository countryRepository;

	//wsdl: http://localhost:8080/ws/countries.wsdl
	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		return response;
	}
	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryNameRequest")
	@ResponsePayload
	public GetCountryNameResponse getCountryName(@RequestPayload GetCountryNameRequest request) {
		GetCountryNameResponse response = new GetCountryNameResponse();
		response.setName(countryRepository.findCountry(request.getName()).getName());
		return response;
	}
}