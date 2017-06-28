package com.test.gameapp.event;

/**
 * Created by Android Developer on 4/13/2017.
 */

public class ErrorEvent {

    private String errorMessage;

    public ErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
