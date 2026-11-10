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
		return ResponseEntity.ok(this.pizzaService.findById(id));
	}

	// Crear una Pizza
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PizzaEntity pizzaEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaService.create(pizzaEntity));
	}

	// Modificar una pizza
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody PizzaEntity pizzaEntity) {
		return ResponseEntity.ok(this.pizzaService.update(id, pizzaEntity));
	}

	// Borrar una Pizza
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		this.pizzaService.delete(id);
		return ResponseEntity.ok().build();
	}
}