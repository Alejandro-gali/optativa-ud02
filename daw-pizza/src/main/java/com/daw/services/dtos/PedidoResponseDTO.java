package com.daw.services.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.daw.persistence.entities.enums.MetodoPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResponseDTO {

	private Long id;
	private Long clienteId;
	private String nombreCliente;
	private String telefono;
	private LocalDateTime fecha;
	private Double total;
	private MetodoPedido metodo;
	private String notas;

	private List<PizzaPedidoResponseDTO> pizzas;

	public PedidoResponseDTO() {
		super();
		this.pizzas = new ArrayList<PizzaPedidoResponseDTO>();
	}

}
