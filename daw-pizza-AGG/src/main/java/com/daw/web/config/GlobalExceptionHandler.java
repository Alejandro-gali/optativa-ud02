package com.daw.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daw.services.exceptions.clientes.ClienteException;
import com.daw.services.exceptions.clientes.ClienteNotFoundException;
import com.daw.services.exceptions.pedidos.PedidoException;
import com.daw.services.exceptions.pedidos.PedidoNotFoundException;
import com.daw.services.exceptions.pizzaPedidos.PizzaPedidoException;
import com.daw.services.exceptions.pizzaPedidos.PizzaPedidoNotFoundException;
import com.daw.services.exceptions.pizzas.PizzaException;
import com.daw.services.exceptions.pizzas.PizzaNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Excepciones de Pizza
	@ExceptionHandler(PizzaNotFoundException.class)
	public ResponseEntity<String> handleNotFound(PizzaNotFoundException ae) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
	}

	@ExceptionHandler(PizzaException.class)
	public ResponseEntity<String> handleBadRequest(PizzaException ae) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
	}

	// Excepciones de Pedido
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<String> handleNotFound(PedidoNotFoundException ae) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
	}

	@ExceptionHandler(PedidoException.class)
	public ResponseEntity<String> handleBadRequest(PedidoException ae) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
	}

	// Excepciones de Cliente
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<String> handleNotFound(ClienteNotFoundException ae) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
	}

	@ExceptionHandler(ClienteException.class)
	public ResponseEntity<String> handleBadRequest(ClienteException ae) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
	}

	// Excepciones de PizzaPedido
	@ExceptionHandler(PizzaPedidoNotFoundException.class)
	public ResponseEntity<String> handleNotFound(PizzaPedidoNotFoundException ae) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
	}

	@ExceptionHandler(PizzaPedidoException.class)
	public ResponseEntity<String> handleBadRequest(PizzaPedidoException ae) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
	}
}
