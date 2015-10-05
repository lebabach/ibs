/*
 * InvalidLoginException
 */
package com.ecard.api.controller.handler;

/**
 *
 * @author vinhla
 */
public class InvalidLoginException extends RuntimeException {
    
    private static final long serialVersionUID = 4964618022965545172L;

    public InvalidLoginException()
    {
    }

    public InvalidLoginException(String message)
    {
        super(message);
    }

    public InvalidLoginException(Throwable cause)
    {
        super(cause);
    }

    public InvalidLoginException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
}
