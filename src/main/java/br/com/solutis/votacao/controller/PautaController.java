package br.com.solutis.votacao.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.config.mapper.Mapper;
import br.com.solutis.votacao.model.Pauta;
import br.com.solutis.votacao.model.ResultadoVotacao;
import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.service.interfaces.IPautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/pauta")
@Api(value="API PAUTA")
public class PautaController {

	@Autowired
	private IPautaService pautaService;
	
	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de pautas")
	public ResponseEntity<Page<Pauta>> GetAllPage(@PageableDefault(sort="id", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return ResponseEntity.ok(pautaService.GetAll(paginacao));
	}
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Persiste pauta no sistema")
	public ResponseEntity<Pauta> Add(@RequestBody @Valid PautaDto pautaDto) {
		Pauta pauta = Mapper.converterByPauta(pautaDto);
		
		try {
			return ResponseEntity.ok(pautaService.Add(pauta));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PutMapping("/v1.0/")
	@ApiOperation(value="Abertura para votação de uma pauta")
	public ResponseEntity<String> IniciarPauta(Integer idPauta) {
		return ResponseEntity.ok(pautaService.IniciarPauta(idPauta));
	}
	
	@GetMapping("/v1.0/ObterResultadoPauta")
	@ApiOperation(value="Obter resultado de pauta")
	public ResponseEntity<ResultadoVotacao> Obter(Integer idPauta) {
		return ResponseEntity.ok(pautaService.ObterResultadoPorPauta(idPauta));
	}
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de pautas")
	public ResponseEntity<List<Pauta>> GetAll() {
		return ResponseEntity.ok(pautaService.GetAll());
	}
}
