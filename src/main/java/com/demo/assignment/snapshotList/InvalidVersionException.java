package com.demo.assignment.snapshotList;

public class InvalidVersionException extends RuntimeException {
    public InvalidVersionException() {
        super();
    }

    public InvalidVersionException(String s) {
        super(s);
    }
}
