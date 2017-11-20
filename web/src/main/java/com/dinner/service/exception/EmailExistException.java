package com.dinner.service.exception;

/**
 * Created by Tomek on 05-Feb-17.
 */
public class EmailExistException extends Exception {
    private static final long serialVersionUID = -4072047721454465349L;

    public EmailExistException(String message) {
        super(message);
    }

}
