package com.eurowings.flightstatus.controller;

import com.eurowings.flightstatus.service.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StatusController {
    @Autowired
    private final FlightStatusService flightStatusService;

    @Autowired
    public StatusController (FlightStatusService flightStatusService) {
        this.flightStatusService = flightStatusService;
    }

    @ResponseBody
    @GetMapping("/flight-status")
    public ResponseEntity getFlightStatus(
        @RequestParam(value = "flightPrefix", required = false, defaultValue = "EW") String flightPrefix,
        @RequestParam(value = "flightNo", required = false, defaultValue = "2465") String flightNo,
        @RequestParam(value = "travelDate", required = true) String travelDate
    ) {
        log.info("flight status search ongoing for params - flightPrefix: " + flightPrefix + " flightNo: " + flightNo + " travelDate: " + travelDate);
        String flightStatus = this.flightStatusService.getFlightStatus(flightPrefix, flightNo, travelDate);
        log.info("flight status search successful!");
        return new ResponseEntity<>(flightStatus, HttpStatus.OK);
    }
}