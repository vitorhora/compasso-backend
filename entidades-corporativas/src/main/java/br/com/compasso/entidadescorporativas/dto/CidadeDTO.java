package br.com.compasso.entidadescorporativas.dto;


import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class CidadeDTO extends RepresentationModel<CidadeDTO> {	
    
	private Long id;   
	private String nome;    
	private Estado estado;  	
}
