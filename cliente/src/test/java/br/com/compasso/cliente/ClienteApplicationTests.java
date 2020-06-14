package br.com.compasso.cliente;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.InitBinder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compasso.business.ClienteBusiness;
import br.com.compasso.entidadescorporativas.dto.CidadeDTO;
import br.com.compasso.entidadescorporativas.dto.ClienteDTO;
import br.com.compasso.entidadescorporativas.dto.Estado;
import br.com.compasso.entidadescorporativas.dto.Sexo;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteApplicationTests{   
		
		private static final String API_V1_CLIENTES = "/api/v1/clientes/";

		@Autowired
	    private MockMvc mockMvc;
		
		@Autowired
		ClienteBusiness clienteBusiness;

	    @Autowired
	    private ObjectMapper objectMapper;	    
	    
	    public ClienteApplicationTests() {
	    	// Mudança da URL de BD, pelo fato da URL do executada no container docker ser um IP.
	    	System.setProperty("spring.datasource.url", "jdbc:h2:tcp://localhost:1521/fswdev");	    	
		}

	    
	    @Test	  
	    void cadastrarClienteSucesso() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	mockMvc.perform(post(API_V1_CLIENTES)
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(clienteDTO)))
	    	        .andExpect(status().isCreated());   	
	    	
	    }   
	    
	    @Test	 
	    void cadastrarClienteFalha() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();	    
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	mockMvc.perform(post(API_V1_CLIENTES)
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(clienteDTO)))
	    	        .andExpect(status().is4xxClientError()); 	    	
	    } 
	    

	    @Test	
	    void consultarClienteNomeSucesso() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	clienteBusiness.salvar(clienteDTO);
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES + "/nome/")
	    			.queryParam("nomeCompleto", "Fulano de Tal")
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());    	
	    } 
	    
	    @Test	
	    void consultarClienteNomeNaoEncontrado() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Florianopolis");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	clienteBusiness.salvar(clienteDTO);
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES + "/nome/")
	    			.queryParam("nomeCompleto", "XPTO")
	    	        .contentType("application/json"))
	    	        .andExpect(status().is4xxClientError());    	
	    } 
	    
	    @Test	
	    void consultarClienteIdSucesso() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	ClienteDTO clienteRetorno = clienteBusiness.salvar(clienteDTO);
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES + clienteRetorno.getId())	    			
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());    	
	    } 
	    
	    @Test	
	    void consultarClienteIdNaoEncontrado() throws Exception {	    	
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES + 12345)	    			
	    	        .contentType("application/json"))
	    	        .andExpect(status().is4xxClientError());    	
	    }
	    
	    @Test	
	    void deletarClienteSucesso() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	ClienteDTO clienteRetorno = clienteBusiness.salvar(clienteDTO);
	    	
	    	mockMvc.perform(delete(API_V1_CLIENTES + clienteRetorno.getId())	    			
	    	        .contentType("application/json"))
	    	        .andExpect(status().is2xxSuccessful());    	
	    } 
	    
	    @Test	
	    void consultarClienteNaoEncontrado() throws Exception {	    	
	    	
	    	mockMvc.perform(delete(API_V1_CLIENTES + 12345)	    			
	    	        .contentType("application/json"))
	    	        .andExpect(status().isNotFound());    	
	    }
	    
	    @Test	
	    void atualizarClienteNomeSucesso() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	ClienteDTO clienteRetorno = clienteBusiness.salvar(clienteDTO);
	    	
	    	clienteRetorno.setNomeCompleto("Fulano de Tal Atualizado");
	    	
	    	mockMvc.perform(put(API_V1_CLIENTES + clienteRetorno.getId())	    			
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(clienteRetorno)))
	    	        .andExpect(status().is2xxSuccessful());    	
	    } 
	    
	    @Test	
	    void atualizarClienteNomeNaoEncontrado() throws Exception {
	    	
	    	ClienteDTO clienteDTO = new ClienteDTO();
	    	clienteDTO.setNomeCompleto("Fulano de Tal");
	    	clienteDTO.setSexo(Sexo.M);	  
	    	Calendar calendar = Calendar.getInstance();
	    	java.util.Date currentDate = calendar.getTime();
	    	java.sql.Date date = new java.sql.Date(currentDate.getTime());
	    	clienteDTO.setDataNascimento(date);
	    	clienteDTO.setIdade(10);
	    	
	    	CidadeDTO cidadeResidente = new CidadeDTO();
	    	cidadeResidente.setNome("Florianopolis");
	    	cidadeResidente.setEstado(Estado.SC);
	    	clienteDTO.setCidadeResidente(cidadeResidente);
	    	
	    	ClienteDTO clienteRetorno = clienteBusiness.salvar(clienteDTO);
	    	
	    	clienteRetorno.setNomeCompleto("Fulano de Tal Atualizado");
	    	
	    	mockMvc.perform(put(API_V1_CLIENTES + 12345)	    			
	    	        .contentType("application/json")
	    	        .content(objectMapper.writeValueAsString(clienteRetorno)))
	    	        .andExpect(status().is4xxClientError());    	
	    } 
	    
	    
	    @Test	    
	    void consultarClientesSucesso() throws Exception {    	 	
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES)	    	
	    	        .contentType("application/json"))
	    	        .andExpect(status().isOk());	    	
	    }
	    
	    @Test	    
	    void consultarClientesSemRetorno() throws Exception {    	 	
	    	
	    	mockMvc.perform(get(API_V1_CLIENTES)	    	
	    	        .contentType("application/json"))
	    	        .andExpect(status().is2xxSuccessful());	    	
	    }
	   
	}