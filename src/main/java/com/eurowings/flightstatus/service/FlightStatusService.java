package com.eurowings.flightstatus.service;

import com.eurowings.flightstatus.model.FlightStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public FlightStatus getFlightStatus(String flightPrefix, String flightNo, String travelDate) {
        FlightStatus flightStatus = null;
        if (flightPrefix.isEmpty() || flightNo.isEmpty() || travelDate.isEmpty()) {
            return null;
        }
        String requestUrl = flightstatusBase.concat(flightPrefix)
                            .concat("/")
                            .concat(flightNo)
                            .concat(travelDate.replace('-', '/'))
                            .concat("/")
                            .concat("?rqid=")
                            .concat(flightstatusReqID);

        String response = restTemplate.getForObject(requestUrl, String.class);
        if (response == null) {
            return null;
        }

        try {
            ObjectMapper objMapper = new ObjectMapper();
            flightStatus = objMapper.readValue(response, objMapper.getTypeFactory().constructCollectionType(List.class, FlightStatus.class));
        } catch (Exception e) {

        }
        return flightStatus;
    }
}