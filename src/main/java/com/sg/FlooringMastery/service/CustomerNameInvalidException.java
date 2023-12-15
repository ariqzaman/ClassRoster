package com.sg.FlooringMastery.service;

public class CustomerNameInvalidException extends Exception {
    public CustomerNameInvalidException() {
        super();
    }

    public CustomerNameInvalidException(String message) {
        super(message);
    }
}
