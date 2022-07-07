package br.com.solutis.votacao.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.viewModel.SessaoViewModel;
import br.com.solutis.votacao.service.interfaces.ISessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sessao")
@Api(value="API SESSAO")
public class SessaoController {
	
	@Autowired
	private ISessaoService sessaoService;
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de sessão")
	public ResponseEntity<List<SessaoViewModel>> getAll() {
		
		var sessoes = sessaoService.getAll();
		
		List<SessaoViewModel> sessaoViewModel = new ArrayList<>();
	    sessoes.forEach(sessao -> sessaoViewModel.add(modelMapper.map(sessao, SessaoViewModel.class)));

		return ResponseEntity.ok(sessaoViewModel);
	}
	
	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de sessões")
	public ResponseEntity<Page<Sessao>> getAllPage(@PageableDefault(sort="tipo", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {	
		return ResponseEntity.ok(sessaoService.getAll(paginacao));
	}
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Persiste sessão no sistema")
	public ResponseEntity<SessaoViewModel> add(@RequestBody @Valid SessaoDto sessaoDto) throws Exception {
	
		Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);
		
		try {
			sessao = sessaoService.add(sessao);
			return ResponseEntity.ok(modelMapper.map(sessao, SessaoViewModel.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
