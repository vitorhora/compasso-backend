package br.com.compasso.entidadescorporativas.dto;

import java.sql.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteDTO extends RepresentationModel<ClienteDTO> {	

	private Long id;
	private String nomeCompleto;
	private Sexo sexo; 
	@JsonFormat(pattern="dd/MM/yyy")
	private Date dataNascimento;
	private Integer idade;    
	private CidadeDTO cidadeResidente;
		
}