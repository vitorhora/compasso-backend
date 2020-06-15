package br.com.compasso.entidadescorporativas.dto;


import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class CidadeDTO extends RepresentationModel<CidadeDTO> implements Serializable {	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;   
	private String nome;    
	private Estado estado;  	
}
