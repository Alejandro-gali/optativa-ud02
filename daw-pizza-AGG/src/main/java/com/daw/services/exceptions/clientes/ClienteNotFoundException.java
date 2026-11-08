package com.daw.services.exceptions.clientes;

public class ClienteNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4363612211319549663L;

	public ClienteNotFoundException(String message) {
		super(message);
	}

}
