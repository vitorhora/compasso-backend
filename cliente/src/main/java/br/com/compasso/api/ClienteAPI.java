package br.com.compasso.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.business.ClienteBusiness;
import br.com.compasso.model.Cliente;
import br.com.compasso.model.dto.ClienteDTO;

@RestController
@RequestMapping("/api/v1")
public class ClienteAPI {

	@Autowired
	private ClienteBusiness clienteService;
	
	/**
	 * Cadastrar um cliente.
	 * @param cliente
	 * @return cliente
	 */
	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
		
		try {					
			ClienteDTO clienteCadastrado = clienteService.salvar(clienteDTO);				
			return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * Consultar clientes por nome.
	 * @param nome
	 * @return listaClientes
	 */
	@GetMapping("/clientes/nome/")
	public ResponseEntity<List<ClienteDTO>> consultarClienteNome(@RequestParam(value = "nome") String nome){
		
		List<ClienteDTO> listaClientesDTO = clienteService.consultarPorNome(nome);

		if (listaClientesDTO.size() > 0) 
			return ResponseEntity.ok().body(listaClientesDTO);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}

	/**
	 * Consultar cliente por id.
	 * @param id
	 * @return cliente
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> consultarClienteId(@PathVariable(value = "id") Long id){
		
		ClienteDTO clienteDTO = clienteService.consultarPorId(id);
		
		if(clienteDTO != null) 
			return ResponseEntity.ok().body(clienteDTO);
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);				
	}

	/**
	 * Remover cliente por id.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Optional<Cliente>> removerClienteId(@PathVariable(value = "id") Long id){

		try {
			clienteService.deletarPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Atualizar nome do cliente por id.
	 * @param id
	 * @param cliente
	 * @return cliente
	 */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> atualizarClienteNome(@PathVariable("id") long id, @RequestBody Cliente clienteAtualizado){

		ClienteDTO clienteDTO = clienteService.consultarPorId(id);

		if (clienteDTO != null) {
			clienteDTO.setId(id);
			clienteDTO.setNomeCompleto(clienteAtualizado.getNomeCompleto());	
			return new ResponseEntity<>(clienteService.salvar(clienteDTO), HttpStatus.OK);
		} 
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}

	/**
	 * Consultar todos os clientes.
	 * @return listaClientes
	 */
	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDTO>> consultarClientes() {	
		
		List<ClienteDTO> listaClientesDTO = clienteService.consultarTodos();

		if(listaClientesDTO.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		return ResponseEntity.ok().body(listaClientesDTO);			
	}
}
