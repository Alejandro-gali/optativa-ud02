package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PizzaEntity;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.services.exceptions.pizzas.PizzaException;
import com.daw.services.exceptions.pizzas.PizzaNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {

	private final PizzaRepository pizzaRepository;

	// Obtener todas las pizzas
	public List<PizzaEntity> findAll() {
		return this.pizzaRepository.findAll();
	}

	// Obtener una pizza mediante su id
	public PizzaEntity findById(long id) {
		if (!this.pizzaRepository.existsById(id)) {
			throw new PizzaNotFoundException("La pizza con el id " + id + " no existe.");
		}
		return this.pizzaRepository.findById(id).get();
	}

	// Crear una pizza
	public PizzaEntity create(PizzaEntity pizzaEntity) {
		if (pizzaEntity.isDisponible() != true) {
			throw new PizzaException("Al crear una pizza esta tiene que estar disponible.");
		}

		pizzaEntity.setId(0L);
		pizzaEntity.setDisponible(true);

		return this.pizzaRepository.save(pizzaEntity);
	}

	// Modificar una pizza
	public PizzaEntity update(long id, PizzaEntity pizzaEntity) {
		if (id != pizzaEntity.getId()) {
			throw new PizzaException("El id del path y el id del body no coinciden.");
		}
		
		PizzaEntity pizzaBD = this.findById(id);
		
		pizzaBD.setNombre(pizzaEntity.getNombre());
		pizzaBD.setDescripcion(pizzaEntity.getDescripcion());
		pizzaBD.setPrecio(pizzaEntity.getPrecio());
		pizzaBD.setVegetariana(pizzaEntity.isVegetariana());
		pizzaBD.setVegana(pizzaEntity.isVegana());
		pizzaBD.setDisponible(pizzaEntity.isDisponible());
		
		return this.pizzaRepository.save(pizzaBD);
	}

	// Borrar una pizza
	public void delete(long id) {
		if(!this.pizzaRepository.existsById(id)) {
			throw new PizzaNotFoundException("La pizza con el id " + id + "no existe.");
		}
		
		this.pizzaRepository.deleteById(id);
	}
}