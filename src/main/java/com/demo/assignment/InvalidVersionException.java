package com.demo.assignment;

public class InvalidVersionException extends RuntimeException {
    public InvalidVersionException() {
        super();
    }

    public InvalidVersionException(String s) {
        super(s);
    }
}
