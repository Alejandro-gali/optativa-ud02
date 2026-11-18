package com.daw.services.mappers;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.ClienteEntity;
import com.daw.services.dtos.ClienteDTO;

@Component
public class ClienteMapper {

	public ClienteDTO toDto(ClienteEntity clienteEntity) {
		if(clienteEntity == null) {
			return null;
		}
		
		ClienteDTO dto = new ClienteDTO();
		dto.setId(clienteEntity.getId());
		dto.setNombre(clienteEntity.getNombre());
		dto.setEmail(clienteEntity.getEmail());
		dto.setDireccion(clienteEntity.getDireccion());
		dto.setTelefono(clienteEntity.getTelefono());
		
		return dto;
	}
	
	public ClienteEntity toEntity(ClienteDTO dto) {
		if(ClienteDTO == null) {
			return null;
		}
		
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setId(dto.getId());
		clienteEntity.setNombre(dto.getNombre());
		clienteEntity.setEmail(dto.getEmail());
		clienteEntity.setDireccion(dto.getDireccion());
		clienteEntity.setTelefono(dto.getTelefono());
		
		return clienteEntity;
	}

}
