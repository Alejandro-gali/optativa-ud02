package com.daw.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.PizzaEntity;
import com.daw.services.PizzaService;
import com.daw.services.exceptions.pizzas.PizzaException;
import com.daw.services.exceptions.pizzas.PizzaNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

	private final PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<PizzaEntity>> list() {
		return ResponseEntity.ok(this.pizzaService.findAll());
	}

	// Obtener una pizza mediante su id
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(this.pizzaService.findById(id));
		} catch (PizzaNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		}
	}

	// Crear una Pizza
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PizzaEntity pizzaEntity) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaService.create(pizzaEntity));
		} catch (PizzaException ae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
		}
	}

	// Modificar una pizza
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody PizzaEntity pizzaEntity) {
		try {
			return ResponseEntity.ok(this.pizzaService.update(id, pizzaEntity));
		} catch (PizzaNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		} catch (PizzaException ae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
		}
	}
	
	// Borrar una Pizza
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		try {
			this.pizzaService.delete(id);
			return ResponseEntity.ok().build();
		} catch(PizzaNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		}
	}
}