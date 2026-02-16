package com.booking.platform;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private String errorMessage;
    private  int status;
    private List<String> details;

    public ExceptionResponse(String errorMessage, int status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
    public ExceptionResponse(String errorMessage, int status, List<String> details) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.details = details;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
