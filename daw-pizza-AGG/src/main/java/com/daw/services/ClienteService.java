package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.ClienteEntity;
import com.daw.persistence.repositories.ClienteRepository;
import com.daw.services.exceptions.clientes.ClienteException;
import com.daw.services.exceptions.clientes.ClienteNotFoundException;
import com.daw.services.exceptions.pizzas.PizzaNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public List<ClienteEntity> findAll() {
		return this.clienteRepository.findAll();
	}

	// Obtener una pizza mediante su id
	public ClienteEntity findById(long id) {
		if (!this.clienteRepository.existsById(id)) {
			throw new PizzaNotFoundException("El cliente con el id " + id + " no existe.");
		}
		return this.clienteRepository.findById(id).get();
	}

	// Crear un cliente
	public ClienteEntity create(ClienteEntity clienteEntity) {
		clienteEntity.setId(0L);

		return this.clienteRepository.save(clienteEntity);
	}

	// Modificar un cliente
	public ClienteEntity update(long id, ClienteEntity clienteEntity) {
		if (id != clienteEntity.getId()) {
			throw new ClienteException("El id del path y el id del body no coinciden.");
		}

		// Lanza ClienteNotFoundException si no existe
		ClienteEntity clienteBD = this.findById(id);

		clienteBD.setNombre(clienteEntity.getNombre());
		clienteBD.setDireccion(clienteEntity.getDireccion());
		clienteBD.setEmail(clienteEntity.getEmail());
		clienteBD.setTelefono(clienteEntity.getTelefono());

		return this.clienteRepository.save(clienteBD);
	}

	// Borrar un cliente
	public void delete(long id) {
		if (!this.clienteRepository.existsById(id)) {
			throw new ClienteNotFoundException("El cliente con el id " + id + " no existe.");
		}
		this.clienteRepository.deleteById(id);
	}
}