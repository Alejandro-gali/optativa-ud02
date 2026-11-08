package com.daw.services.exceptions.pizzas;

public class PizzaNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688355434062941600L;

	public PizzaNotFoundException(String message) {
		super(message);
	}

}
