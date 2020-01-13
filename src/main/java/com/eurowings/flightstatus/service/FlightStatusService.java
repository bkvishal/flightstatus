package com.eurowings.flightstatus.service;

import com.eurowings.flightstatus.model.FlightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import com.google.gson.Gson;

@Service
public class FlightStatusService {
    private final RestTemplate restTemplate;

    @Value("${flightstatus.base}")
    private String flightstatusBase;

    @Value("${flightstatus.rqid}")
    private String flightstatusReqID;

    @Autowired
    public FlightStatusService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFlightStatus(String flightPrefix, String flightNo, String travelDate) {
        FlightStatus flightStatus = new FlightStatus();
        Gson gson = new Gson();
        String flightStatusJsonString = "{}";

        if (flightPrefix.isEmpty() || flightNo.isEmpty() || travelDate.isEmpty()) {
            return flightStatusJsonString;
        }
        String requestUrl = flightstatusBase.concat(flightPrefix)
                            .concat("/")
                            .concat(flightNo)
                            .concat("/")
                            .concat(travelDate.replace('-', '/'))
                            .concat("/")
                            .concat("?rqid=")
                            .concat(flightstatusReqID);

        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        if (response == null) {
            return flightStatusJsonString;
        }

        try {
            JSONObject responseObj = new JSONObject(response.getBody());
            JSONObject responseObjData = responseObj.getJSONObject("data");

            flightStatus = flightStatus.fsMapper(responseObjData);
            flightStatusJsonString = gson.toJson(flightStatus);

        } catch (Exception e) { }

        return flightStatusJsonString;
    }
}