package com.daw.services.exceptions.pedidos;

public class PedidoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5446574632391408187L;

	public PedidoNotFoundException(String message) {
		super(message);
	}

}
