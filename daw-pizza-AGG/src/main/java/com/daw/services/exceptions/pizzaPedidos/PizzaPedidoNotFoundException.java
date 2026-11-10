package com.daw.services.exceptions.pizzaPedidos;

public class PizzaPedidoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532325134153420743L;

	public PizzaPedidoNotFoundException(String message) {
		super(message);
	}

}
