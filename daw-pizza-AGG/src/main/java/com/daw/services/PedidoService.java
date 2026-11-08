package com.daw.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PedidoEntity;
import com.daw.persistence.repositories.ClienteRepository;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.exceptions.pedidos.PedidoException;
import com.daw.services.exceptions.pedidos.PedidoNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;

	public List<PedidoEntity> findAll() {
		return this.pedidoRepository.findAll();
	}

	// Obtener un pedido mediante su id
	public PedidoEntity findById(long id) {
		return this.pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNotFoundException("El pedido con el id " + id + " no existe."));
	}

	// Crear un pedido
	public PedidoEntity create(PedidoEntity pedidoEntity) {
		if (!this.clienteRepository.existsById(pedidoEntity.getIdCliente())) {
			throw new PedidoException("El cliente con el id " + pedidoEntity.getIdCliente() + " no existe.");
		}
		if (pedidoEntity.getMetodo() == null) {
			throw new PedidoException("El método del pedido es obligatorio.");
		}

		pedidoEntity.setId(0L);
		pedidoEntity.setFecha(LocalDateTime.now());
		pedidoEntity.setTotal(0.0);

		return this.pedidoRepository.save(pedidoEntity);
	}

	// Modificar un pedido
	public PedidoEntity update(long id, PedidoEntity pedidoEntity) {
		if (id != pedidoEntity.getId()) {
			throw new PedidoException("El id del path y el id del body no coinciden.");
		}
		if (!this.clienteRepository.existsById(pedidoEntity.getIdCliente())) {
			throw new PedidoException("El cliente con el id " + pedidoEntity.getIdCliente() + " no existe.");
		}
		if (pedidoEntity.getMetodo() == null) {
			throw new PedidoException("El método del pedido es obligatorio.");
		}

		PedidoEntity pedidoBD = this.findById(id);

		pedidoBD.setIdCliente(pedidoEntity.getIdCliente());
		pedidoBD.setMetodo(pedidoEntity.getMetodo());
		pedidoBD.setNotas(pedidoEntity.getNotas());

		return this.pedidoRepository.save(pedidoBD);
	}

	// Borrar un pedido
	public void delete(long id) {
		if (!this.pedidoRepository.existsById(id)) {
			throw new PedidoNotFoundException("El pedido con el id " + id + " no existe.");
		}
		this.pedidoRepository.deleteById(id);
	}

}