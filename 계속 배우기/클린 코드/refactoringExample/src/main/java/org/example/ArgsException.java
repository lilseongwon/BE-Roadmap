package org.example;

public class ArgsException extends Exception {
    private char errorArgumentId = '\0';
    private String errorParameter = "TILT";
    private ErrorCode errorCode = ErrorCode.OK;

    public ArgsException() {}

    public ArgsException(String message) {super(message);}

    public enum ErrorCode {
        OK, MISSING_STRING, MISSING_INTEGER, MISSING_DOUBLE, INVALID_INTEGER, INVALID_DOUBLE, UNEXPECTED_ARGUMENT
    }
}
