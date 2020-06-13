package br.com.compasso.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
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
import br.com.compasso.entidadescorporativas.dto.ClienteDTO;
import br.com.compasso.entidadescorporativas.modelo.Cliente;

@RestController
@RequestMapping("/api/v1")
public class ClienteAPI {

	@Autowired
	private ClienteBusiness clienteService;
	
	private static final String CADASTRAR_CLIENTE = "cadastrarCliente";
	private static final String CONSULTAR_CLIENTE_NOME = "consultarClienteNome";
	private static final String CONSULTAR_CLIENTE_ID = "consultarClienteId";
	private static final String REMOVER_CLIENTE_ID = "removerClienteId";
	private static final String ATUALIZAR_CLIENTE_NOME = "atualizarClienteNome";
	private static final String CONSULTAR_CLIENTES = "consultarClientes";
	
	/**
	 * Cadastrar um cliente.
	 * @param cliente
	 * @return cliente
	 */
	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
		
		List<String> listaLinks = Arrays.asList(CONSULTAR_CLIENTE_NOME, CONSULTAR_CLIENTE_ID, REMOVER_CLIENTE_ID, ATUALIZAR_CLIENTE_NOME, CONSULTAR_CLIENTES);
		
		try {					
			ClienteDTO clienteCadastrado = clienteService.salvar(clienteDTO);
			clienteCadastrado = gerarLink(clienteCadastrado, listaLinks);
			
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
	public ResponseEntity<List<ClienteDTO>> consultarClienteNome(@RequestParam(value = "nomeCompleto") String nome){
		
		List<ClienteDTO> listaClientesDTO = clienteService.consultarPorNome(nome);

		if (listaClientesDTO.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<String> listaLinks = Arrays.asList(CADASTRAR_CLIENTE, CONSULTAR_CLIENTE_ID, REMOVER_CLIENTE_ID, ATUALIZAR_CLIENTE_NOME, CONSULTAR_CLIENTES);
		listaClientesDTO = gerarListaLinks(listaClientesDTO, listaLinks);
		
		return new ResponseEntity<>(listaClientesDTO, HttpStatus.OK);				
	}

	/**
	 * Consultar cliente por id.
	 * @param id
	 * @return cliente
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> consultarClienteId(@PathVariable(value = "id") Long id){
		
		ClienteDTO clienteDTO = clienteService.consultarPorId(id);
		
		if(clienteDTO == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<String> listaLinks = Arrays.asList(CADASTRAR_CLIENTE, CONSULTAR_CLIENTE_NOME, REMOVER_CLIENTE_ID, ATUALIZAR_CLIENTE_NOME, CONSULTAR_CLIENTES);
		clienteDTO = gerarLink(clienteDTO, listaLinks);	
	
		return new ResponseEntity<>(clienteDTO, HttpStatus.OK);						
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
	public ResponseEntity<ClienteDTO> atualizarClienteNome(@PathVariable("id") long id, @RequestBody ClienteDTO clienteAtualizado){

		ClienteDTO clienteDTO = clienteService.consultarPorId(id);

		if (clienteDTO != null) {
			clienteDTO.setId(id);
			clienteDTO.setNomeCompleto(clienteAtualizado.getNomeCompleto());			
			clienteService.salvar(clienteDTO);
			
			List<String> listaLinks = Arrays.asList(CADASTRAR_CLIENTE, CONSULTAR_CLIENTE_NOME, CONSULTAR_CLIENTE_ID, REMOVER_CLIENTE_ID, CONSULTAR_CLIENTES);
			clienteDTO = gerarLink(clienteDTO, listaLinks);	
			
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);		
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
		
		
		List<String> listaLinks = Arrays.asList(CADASTRAR_CLIENTE, CONSULTAR_CLIENTE_NOME, CONSULTAR_CLIENTE_ID, REMOVER_CLIENTE_ID, ATUALIZAR_CLIENTE_NOME);
		listaClientesDTO = gerarListaLinks(listaClientesDTO, listaLinks);
		
		return ResponseEntity.ok().body(listaClientesDTO);			
	}
	
	/**
	 * Rotina responsável por gerar link HATEOAS.
	 * @param clienteDTO
	 * @param listaLinks
	 * @return clienteDTO
	 */
	private ClienteDTO gerarLink(ClienteDTO clienteDTO, List<String> listaLinks) {
		
		for (String link : listaLinks) {
			
			switch (link) {
			
			case CADASTRAR_CLIENTE:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).cadastrarCliente(new ClienteDTO())).withRel(CADASTRAR_CLIENTE));
				break;
			case CONSULTAR_CLIENTE_NOME:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).consultarClienteNome(clienteDTO.getNomeCompleto())).withRel(CONSULTAR_CLIENTE_NOME));
				break;
			case CONSULTAR_CLIENTE_ID:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).consultarClienteId(clienteDTO.getId())).withRel(CONSULTAR_CLIENTE_ID));
				break;
			case REMOVER_CLIENTE_ID:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).removerClienteId(clienteDTO.getId())).withRel(REMOVER_CLIENTE_ID));
				break;
			case ATUALIZAR_CLIENTE_NOME:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).atualizarClienteNome(clienteDTO.getId(), clienteDTO)).withRel(ATUALIZAR_CLIENTE_NOME));
				break;
			case CONSULTAR_CLIENTES:
				clienteDTO.add(linkTo(methodOn(ClienteAPI.class).consultarClientes()).withRel(CONSULTAR_CLIENTES));
				break;

			default:
				break;
			}			
		}
		
		return clienteDTO;
	}
	
	/**
	 * Rotina responsável por gerar uma lista de links HATEOAS.
	 * @param listaClientesDTO
	 * @param listaLinks
	 * @return listaClientesDTO
	 */
	private List<ClienteDTO> gerarListaLinks(List<ClienteDTO> listaClientesDTO, List<String> listaLinks) {
		
		for (ClienteDTO clienteDTO : listaClientesDTO) {
			clienteDTO = gerarLink(clienteDTO, listaLinks);			
		}
		
		return listaClientesDTO;
	}
}
