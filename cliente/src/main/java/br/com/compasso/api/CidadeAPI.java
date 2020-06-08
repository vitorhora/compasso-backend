package br.com.compasso.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.business.CidadeBusiness;
import br.com.compasso.model.dto.CidadeDTO;
import br.com.compasso.model.dto.Estado;

@RestController
@RequestMapping("/api/v1")
public class CidadeAPI {

	@Autowired
	private CidadeBusiness cidadeService;

	/**
	 * Cadastar uma cidade.
	 * @param cidade
	 * @return cidade
	 */
	@PostMapping("/cidades")
	public ResponseEntity<CidadeDTO> cadastrarCidade(@RequestBody CidadeDTO cidadeDTO) {		
			
		try {					
			CidadeDTO cidadeCadastrado = cidadeService.salvar(cidadeDTO);				
			return new ResponseEntity<>(cidadeCadastrado, HttpStatus.CREATED);
	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	
	/**
	 * Consultar cidades por nome.
	 * @param nome
	 * @return listaCidades
	 */
	@GetMapping("/cidades/nome/")
	public ResponseEntity<List<CidadeDTO>> consultarCidadeNome(@RequestParam(value = "nome") String nome){
		
		List<CidadeDTO> listaCidadesDTO = cidadeService.consultarPorNome(nome);

		if (listaCidadesDTO.size() > 0) {
			return ResponseEntity.ok().body(listaCidadesDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Consultar cidades por estado.
	 * @param uf
	 * @return listaCidades
	 */
	@GetMapping("/cidades/estados/{uf}")
	public ResponseEntity<List<CidadeDTO>> consultarCidadeEstado(@PathVariable(value = "uf") Estado uf){
	
		List<CidadeDTO> listaCidadesDTO = cidadeService.consultarPorEstado(uf);
		
		if (listaCidadesDTO.size() > 0) {
			return ResponseEntity.ok().body(listaCidadesDTO);
		
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}

	/**
	 * Consultar todas as cidades.
	 * @return listaCidades.
	 */
	@GetMapping("/cidades")
	public ResponseEntity<List<CidadeDTO>> consultarCidades() {	
		
		List<CidadeDTO> listaCidadeDTO = cidadeService.consultarTodos();

		if(listaCidadeDTO.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {			
			return ResponseEntity.ok().body(listaCidadeDTO);
		}	
	}

}
