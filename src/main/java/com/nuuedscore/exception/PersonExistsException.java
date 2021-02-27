package com.nuuedscore.exception;

/**
 * PersonExists Exception
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public class PersonExistsException extends Throwable {

	private static final long serialVersionUID = 4167206872810410602L;

	public PersonExistsException(final String message) {
        super(message);
    }

}