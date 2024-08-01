package com.escooter.service_layer.entities.exceptions;

public class RideNotPossibleException extends Exception {

    public RideNotPossibleException() {
        super();
    }
    public RideNotPossibleException(final String s) {
        super(s);
    }
}
