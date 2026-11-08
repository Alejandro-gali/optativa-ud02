package com.daw.services.mappers;

import com.daw.persistence.entities.PedidoEntity;
import com.daw.services.dtos.PedidoDTO;

public class PedidoMapper {

	public static PedidoDTO toDTO(PedidoEntity entity) {
		PedidoDTO dto = new PedidoDTO();
		dto.setId(entity.getId());
		dto.setIdCliente(entity.getIdCliente());
		dto.setFecha(entity.getFecha());
		dto.setTotal(entity.getTotal());
		dto.setMetodo(entity.getMetodo());
		dto.setNotas(entity.getNotas());
		return dto;
	}

	public static PedidoEntity toEntity(PedidoDTO dto) {
		PedidoEntity entity = new PedidoEntity();
		entity.setId(dto.getId());
		entity.setIdCliente(dto.getIdCliente());
		entity.setFecha(dto.getFecha());
		entity.setTotal(dto.getTotal());
		entity.setMetodo(dto.getMetodo());
		entity.setNotas(dto.getNotas());
		return entity;
	}
}