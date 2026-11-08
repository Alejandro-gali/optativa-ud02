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

import com.daw.persistence.entities.PedidoEntity;
import com.daw.services.PedidoService;
import com.daw.services.dtos.PedidoDTO;
import com.daw.services.exceptions.pedidos.PedidoException;
import com.daw.services.exceptions.pedidos.PedidoNotFoundException;
import com.daw.services.mappers.PedidoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoEntity>> list() {
		return ResponseEntity.ok(this.pedidoService.findAll());
	}

	// Obtener un pedido mediante su id
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(PedidoMapper.toDTO(this.pedidoService.findById(id)));
		} catch (PedidoNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		}
	}

	// Crear un pedido
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PedidoDTO pedidoDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(PedidoMapper.toDTO(this.pedidoService.create(PedidoMapper.toEntity(pedidoDTO))));
		} catch (PedidoException ae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
		}
	}

	// Modificar un pedido
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody PedidoDTO pedidoDTO) {
		try {
			return ResponseEntity
					.ok(PedidoMapper.toDTO(this.pedidoService.update(id, PedidoMapper.toEntity(pedidoDTO))));
		} catch (PedidoNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		} catch (PedidoException ae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
		}
	}

	// Borrar un pedido
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		try {
			this.pedidoService.delete(id);
			return ResponseEntity.ok().build();
		} catch (PedidoNotFoundException ae) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ae.getMessage());
		}
	}

}