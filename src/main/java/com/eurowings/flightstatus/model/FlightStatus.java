package com.eurowings.flightstatus.model;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class FlightStatus {
    private long id;
    private String flightPrefix;
    private String flightId;
    private String aircraft;
    private enum status {
        ACTIVE("A", "Active"),
        CANCELLED("C", "Cancelled"),
        NODATA("DN", "No Data Yet"),
        LANDED("L", "Landed"),
        NOOPS("NO", "No Ops"),
        REDIRECTED("R", "Redirected"),
        SCHEDULED("S", "Scheduled"),
        UNKNOWN("U", "Unknown");

        private final String statusVal;
        private final String statusCode;

        private status(String statusCode, String statusVal) {
            this.statusCode = statusCode;
            this.statusVal = statusVal;
        }
        private String getStatusCode() {
            return this.statusCode;
        }
        private String getStatus() {
            return this.statusVal;
        }
    };
    private String estimatedDepartureAirport;
    private String estimatedDepartureAirportCode;
    private Date estimatedDepartureDateTime;
    private String estimatedArrivalAirport;
    private String estimatedArrivalAirportCode;
    private Date estimatedArrivalDateTime;


}