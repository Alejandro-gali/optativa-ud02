package com.daw.services.dtos;

import java.time.LocalDateTime;

import com.daw.persistence.entities.enums.MetodoPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {

	private long id;
	private long idCliente;
	private LocalDateTime fecha;
	private double total;
	private MetodoPedido metodo;
	private String notas;
}