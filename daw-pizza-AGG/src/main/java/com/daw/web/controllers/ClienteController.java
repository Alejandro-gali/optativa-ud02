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

import com.daw.persistence.entities.ClienteEntity;
import com.daw.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteEntity>> list() {
		return ResponseEntity.ok(this.clienteService.findAll());
	}

	// Obtener un cliente mediante su id
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return ResponseEntity.ok(this.clienteService.findById(id));
	}

	// Crear un cliente
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClienteEntity clienteEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.create(clienteEntity));
	}

	// Modificar un cliente
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody ClienteEntity clienteEntity) {
		return ResponseEntity.ok(this.clienteService.update(id, clienteEntity));
	}

	// Borrar un cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		this.clienteService.delete(id);
		return ResponseEntity.ok().build();
	}
}