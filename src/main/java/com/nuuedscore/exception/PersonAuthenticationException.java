package com.nuuedscore.exception;

/**
 * PersonAuthentication Exception
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public class PersonAuthenticationException extends Throwable {

	private static final long serialVersionUID = 4167206872810410602L;

	public PersonAuthenticationException(final String message) {
        super(message);
    }

}