package br.com.compasso.cliente;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compasso.business.CidadeBusiness;
import br.com.compasso.entidadescorporativas.dto.CidadeDTO;
import br.com.compasso.entidadescorporativas.dto.Estado;

@SpringBootTest
@AutoConfigureMockMvc
class CidadeApplicationTests{   
		
		private static final String API_V1_CIDADES = "/api/v1/cidades";

		@Autowired
	    private MockMvc mockMvc;
		
		@Autowired
		CidadeBusiness cidadadeBusiness;

	    @Autowired
	    private ObjectMapper objectMapper;

	    
	    @Test	  
	    void cadastrarCidadeSucesso() throws Exception {
	    	
	    	CidadeDTO cidadeDTO = new CidadeDTO();
	    	cidadeDTO.setNome("Florianopolis");
	    	cidadeDTO.setEstado(Estado.SC);
	    	
	    	mockMvc.perform(post(API_V1_CIDADES)
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(cidadeDTO)))
	    	        .andExpect(status().isCreated());   	
	    	
	    }   
	    
	    @Test	 
	    void cadastrarCidadeFalha() throws Exception {
	    	
	    	CidadeDTO cidadeDTO = new CidadeDTO();	    	
	    	cidadeDTO.setEstado(Estado.SC);
	    	
	    	mockMvc.perform(post(API_V1_CIDADES)
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(cidadeDTO)))
	    	        .andExpect(status().is4xxClientError()); 	    	
	    } 	    
	     
	    @Test	
	    void consultarCidadeNomeSucesso() throws Exception {
	    	
	    	CidadeDTO cidadeDTO = new CidadeDTO();
	    	cidadeDTO.setNome("Florianopolis");	
	    	cidadeDTO.setEstado(Estado.SC);
	    	
	    	cidadadeBusiness.salvar(cidadeDTO);
	    	
	    	mockMvc.perform(get(API_V1_CIDADES + "/nome/")
	    			.queryParam("nome", "Florianopolis")
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());    	
	    } 
	    
	    @Test	
	    void consultarCidadeNomeNaoEncontrado() throws Exception {
	    	
	    	CidadeDTO cidadeDTO = new CidadeDTO();
	    	cidadeDTO.setNome("XPTO");	
	    		    	
	    	mockMvc.perform(get(API_V1_CIDADES + "/nome/")
	    			.queryParam("nome", "XPTO")
	    	        .contentType("application/json"))
	    	        .andExpect(status().is4xxClientError());    	
	    }
	    
	    @Test	
	    void consultarCidadeEstadoSucesso() throws Exception {
	    	
	    	CidadeDTO cidadeDTO = new CidadeDTO();
	    	cidadeDTO.setNome("Florianopolis");	
	    	cidadeDTO.setEstado(Estado.SC);
	    	
	    	cidadadeBusiness.salvar(cidadeDTO);
	    	
	    	mockMvc.perform(get(API_V1_CIDADES + "/estados/" + Estado.SC)		    
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());    	
	    } 
	    
	    @Test	
	    void consultarCidadeEstadoNaoEncontrado() throws Exception {
		    	
	    	mockMvc.perform(get(API_V1_CIDADES + "/estados/" + "XP")		    
	    	        .contentType("application/json"))
	    	        .andExpect(status().is4xxClientError());    	  	
	    }	    
	     
	    @Test	    
	    void consultarCidadesSucesso() throws Exception {    	 	
	    	
	    	mockMvc.perform(get(API_V1_CIDADES)	    	
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());	    	
	    }
	    
	    @Test	    
	    void consultarCidadesSemRetorno() throws Exception {    	 	
	    	
	    	mockMvc.perform(get(API_V1_CIDADES)	    	
	    	        .contentType("application/json"))
	    	        .andExpect(status().is2xxSuccessful());	    	
	    }
	}