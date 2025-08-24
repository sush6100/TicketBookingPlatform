package com.company.weather.exceptions;

public class ReportFetchException extends RuntimeException{
    public ReportFetchException(String message) {
        super(message);
    }
}
