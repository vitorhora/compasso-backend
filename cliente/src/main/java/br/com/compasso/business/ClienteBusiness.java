package br.com.compasso.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.business.repository.ClienteRepository;
import br.com.compasso.entidadescorporativas.dto.CidadeDTO;
import br.com.compasso.entidadescorporativas.dto.ClienteDTO;
import br.com.compasso.entidadescorporativas.modelo.Cidade;
import br.com.compasso.entidadescorporativas.modelo.Cliente;


@Service
public class ClienteBusiness {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteDTO salvar(ClienteDTO clienteDTO) {
		
		Cidade cidade = new Cidade(clienteDTO.getCidadeResidente().getId(),clienteDTO.getCidadeResidente().getNome(),clienteDTO.getCidadeResidente().getEstado());
						
		Cliente cliente = clienteRepository.save(new Cliente(clienteDTO.getId(), clienteDTO.getNomeCompleto(), clienteDTO.getSexo(), clienteDTO.getDataNascimento(), clienteDTO.getIdade(), cidade));				
		
		return transformarDTO(cliente);
	}
	
	
	public List<ClienteDTO> consultarPorNome(String nome){
		
		List<Cliente> listaClientes = clienteRepository.findByNomeCompleto(nome);		
		List<ClienteDTO> listaClientesDTO = new ArrayList<>();		
		criarListaClientes(listaClientes, listaClientesDTO);
		
		return listaClientesDTO;		
	}	
	
	public ClienteDTO consultarPorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		ClienteDTO clienteDTO = null;
		
		if(cliente.isPresent()) {	
			clienteDTO = transformarDTO(cliente.get());
		}
		
		return clienteDTO;		
	}	
	
	public void deletarPorId(Long id) {
		
		clienteRepository.deleteById(id);
	}


	public List<ClienteDTO> consultarTodos() {		
		
		List<Cliente> listaClientes = clienteRepository.findAll();
		List<ClienteDTO> listaClientesDTO = new ArrayList<>();		
		criarListaClientes(listaClientes, listaClientesDTO);
		
		return listaClientesDTO;		
	}


	private void criarListaClientes(List<Cliente> listaClientes, List<ClienteDTO> listaClientesDTO) {
		
		for (Cliente cliente : listaClientes) {
			ClienteDTO clienteDTO = transformarDTO(cliente);			
			listaClientesDTO.add(clienteDTO);			
		}
	}

	private ClienteDTO transformarDTO(Cliente cliente) {
		
		ClienteDTO clienteDTO = new ClienteDTO();		
		CidadeDTO cidadeDTO = new CidadeDTO();
		
		cidadeDTO.setId(cliente.getCidadeResidente().getId());
		cidadeDTO.setNome(cliente.getCidadeResidente().getNome());
		cidadeDTO.setEstado(cliente.getCidadeResidente().getEstado());
		
		clienteDTO.setCidadeResidente(cidadeDTO);
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setId(cliente.getId());
		clienteDTO.setIdade(cliente.getIdade());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setSexo(cliente.getSexo());
		return clienteDTO;
	}	
}
