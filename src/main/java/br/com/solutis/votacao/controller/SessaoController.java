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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.config.mapper.Mapper;
import br.com.solutis.votacao.model.Sessao;
import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.service.interfaces.ISessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/sessao")
@Api(value="API SESSAO")
public class SessaoController {
	
	@Autowired
	private ISessaoService sessaoService;
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de sessão")
	public ResponseEntity<List<Sessao>> GetAll() {
		return ResponseEntity.ok(sessaoService.GetAll());
	}
	
	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de sessões")
	public ResponseEntity<Page<Sessao>> GetAllPage(@PageableDefault(sort="tipo", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return ResponseEntity.ok(sessaoService.GetAll(paginacao));
	}
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Persiste sessão no sistema")
	public ResponseEntity<Sessao> Add(@RequestBody @Valid SessaoDto sessaoDto) {
		Sessao sessao = Mapper.converterBySessao(sessaoDto);
		
		try {
			return ResponseEntity.ok(sessaoService.Add(sessao));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
