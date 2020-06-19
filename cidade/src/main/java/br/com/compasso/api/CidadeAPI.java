package br.com.compasso.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
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
import br.com.compasso.entidadescorporativas.dto.CidadeDTO;
import br.com.compasso.entidadescorporativas.dto.Estado;

@RestController
@RequestMapping("/api/v1")
public class CidadeAPI {

	@Autowired
	private CidadeBusiness cidadeService;

	private static final String CONSULTAR_CIDADE_NOME = "consultarCidadeNome";
	private static final String CONSULTAR_CIDADE_ESTADO = "consultarCidadeEstado";
	private static final String CONSULTAR_CIDADES = "consultarCidades";
	private static final String CADASTRAR_CIDADE = "cadastrarCidade";

	/**
	 * Cadastar uma cidade.
	 * 
	 * @param cidade
	 * @return cidade
	 */
	@PostMapping("/cidades")
	public ResponseEntity<CidadeDTO> cadastrarCidade(@RequestBody CidadeDTO cidadeDTO) {

		List<String> listaLinks = Arrays.asList(CONSULTAR_CIDADE_NOME, CONSULTAR_CIDADE_ESTADO, CONSULTAR_CIDADES);

		try {
			CidadeDTO cidadeCadastrado = cidadeService.salvar(cidadeDTO);
			cidadeCadastrado = gerarLink(cidadeCadastrado, listaLinks);

			return new ResponseEntity<>(cidadeCadastrado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Consultar cidades por nome.
	 * 
	 * @param nome
	 * @return listaCidades
	 */
	@GetMapping("/cidades/nome/")
	public ResponseEntity<List<CidadeDTO>> consultarCidadeNome(@RequestParam(value = "nome") String nome) {

		List<CidadeDTO> listaCidadesDTO = cidadeService.consultarPorNome(nome);

		if (listaCidadesDTO.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<String> listaLinks = Arrays.asList(CADASTRAR_CIDADE, CONSULTAR_CIDADE_ESTADO, CONSULTAR_CIDADES);
		listaCidadesDTO = gerarListaLinks(listaCidadesDTO, listaLinks);

		return new ResponseEntity<>(listaCidadesDTO, HttpStatus.OK);
	}

	/**
	 * Consultar cidades por estado.
	 * 
	 * @param uf
	 * @return listaCidades
	 */
	@GetMapping("/cidades/estados/{uf}")
	public ResponseEntity<List<CidadeDTO>> consultarCidadeEstado(@PathVariable(value = "uf") Estado uf) {

		List<CidadeDTO> listaCidadesDTO = cidadeService.consultarPorEstado(uf);

		if (listaCidadesDTO.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<String> listaLinks = Arrays.asList(CADASTRAR_CIDADE, CONSULTAR_CIDADE_NOME, CONSULTAR_CIDADES);
		listaCidadesDTO = gerarListaLinks(listaCidadesDTO, listaLinks);

		return new ResponseEntity<>(listaCidadesDTO, HttpStatus.OK);
	}

	/**
	 * Consultar todas as cidades.
	 * 
	 * @return listaCidades.
	 */
	@GetMapping("/cidades")
	public ResponseEntity<List<CidadeDTO>> consultarCidades() {

		List<CidadeDTO> listaCidadesDTO = cidadeService.consultarTodos();

		if (listaCidadesDTO.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<String> listaLinks = Arrays.asList(CADASTRAR_CIDADE, CONSULTAR_CIDADE_NOME, CONSULTAR_CIDADE_ESTADO);
		listaCidadesDTO = gerarListaLinks(listaCidadesDTO, listaLinks);

		return new ResponseEntity<>(listaCidadesDTO, HttpStatus.OK);
	}

	/**
	 * Rotina responsável por gerar link HATEOAS.
	 * 
	 * @param cidadeDTO
	 * @param listaLinks
	 * @return cidadeDTO
	 */
	private CidadeDTO gerarLink(CidadeDTO cidadeDTO, List<String> listaLinks) {

		for (String link : listaLinks) {

			switch (link) {

			case CADASTRAR_CIDADE:
				cidadeDTO.add(linkTo(methodOn(CidadeAPI.class).cadastrarCidade(new CidadeDTO()))
						      .withRel(CADASTRAR_CIDADE));
				break;
			case CONSULTAR_CIDADE_NOME:
				cidadeDTO.add(linkTo(methodOn(CidadeAPI.class).consultarCidadeNome(cidadeDTO.getNome()))
						      .withRel(CONSULTAR_CIDADE_NOME));
				break;
			case CONSULTAR_CIDADE_ESTADO:
				cidadeDTO.add(linkTo(methodOn(CidadeAPI.class).consultarCidadeEstado(cidadeDTO.getEstado()))
						      .withRel(CONSULTAR_CIDADE_ESTADO));
				break;
			case CONSULTAR_CIDADES:
				cidadeDTO.add(linkTo(methodOn(CidadeAPI.class).consultarCidades())
						      .withRel(CONSULTAR_CIDADES));
				break;

			default:
				break;
			}
		}

		return cidadeDTO;
	}

	/**
	 * Rotina responsável por gerar uma lista de links HATEOAS.
	 * 
	 * @param listaCidadesDTO
	 * @param listaLinks
	 * @return listaCidadesDTO
	 */
	private List<CidadeDTO> gerarListaLinks(List<CidadeDTO> listaCidadesDTO, List<String> listaLinks) {

		for (CidadeDTO cidadeDTO : listaCidadesDTO) {
			cidadeDTO = gerarLink(cidadeDTO, listaLinks);
		}

		return listaCidadesDTO;
	}
}
