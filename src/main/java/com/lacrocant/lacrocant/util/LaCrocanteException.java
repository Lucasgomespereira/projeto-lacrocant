package com.lacrocant.lacrocant.util;

import java.util.Arrays;
import java.util.List;

public class LaCrocanteException extends Exception {

    private static final long serialVersionUID = 1L;

    private int statusCode;
    private List<String> messages;

    public LaCrocanteException(int statusCode, String message) {
        super(message);
        this.messages = Arrays.asList(message);
        this.statusCode = statusCode;
    }

    public LaCrocanteException(int statusCode, List<String> messages) {
        super(messages.toString());
        this.messages = messages;
        this.statusCode = statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

}