package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.daw.persistence.entities.PizzaEntity;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.services.dtos.PizzaDTO;
import com.daw.services.exceptions.ResourceNotFoundException;
import com.daw.services.mappers.PizzaMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class PizzaService {

	private final PizzaRepository pizzaRepository;
	private final PizzaMapper pizzaMapper;

	public List<PizzaDTO> findAll() {
		return this.pizzaMapper.toDtos(this.pizzaRepository.findAll());
	}

	public PizzaDTO findById(long idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new ResourceNotFoundException("Pizza no encontrada con ID: " + idPizza);
		}

		return this.pizzaMapper.toDto(this.pizzaRepository.findById(idPizza).get());
	}

	public PizzaDTO create(@Valid PizzaDTO pizzaDto) {
		if (pizzaDto.getDisponible() == null) {
			pizzaDto.setDisponible(true);
		}
		if (pizzaDto.getVegana() == null) {
			pizzaDto.setVegana(false);
		}
		if (pizzaDto.getVegetariana() == null) {
			pizzaDto.setVegetariana(false);
		}

		PizzaEntity pizzaEnt = this.pizzaMapper.toEntity(pizzaDto);

		pizzaDto.setId(null);

		return this.pizzaMapper.toDto(this.pizzaRepository.save(pizzaEnt));
	}

	public PizzaDTO update(long idPizza, @Valid PizzaDTO pizzaDto) {
		PizzaEntity pizzaBD = this.pizzaRepository.findById(idPizza)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + idPizza));

		pizzaBD.setDescripcion(pizzaDto.getDescripcion());
		pizzaBD.setDisponible(pizzaDto.getDisponible());
		pizzaBD.setNombre(pizzaDto.getNombre());
		pizzaBD.setPrecio(pizzaDto.getPrecio());
		pizzaBD.setVegana(pizzaDto.getVegana());
		pizzaBD.setVegetariana(pizzaDto.getVegetariana());

		return this.pizzaMapper.toDto(this.pizzaRepository.save(pizzaBD));
	}

	public void deleteById(long idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new ResourceNotFoundException("No se puede eliminar. Pizza no encontrada con ID: " + idPizza);
		}

		this.pizzaRepository.deleteById(idPizza);
	}

}
