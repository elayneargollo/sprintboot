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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.entity.ResultadoVotacao;
import br.com.solutis.votacao.model.viewModel.PautaViewModel;
import br.com.solutis.votacao.service.interfaces.IPautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pauta")
@Api(value="API PAUTA")
public class PautaController {

	@Autowired
	private IPautaService pautaService;
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de pautas")
	public ResponseEntity<Page<Pauta>> getAllPage(@PageableDefault(sort="id", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return new ResponseEntity<>(pautaService.getAll(paginacao), HttpStatus.OK);
	}
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Persiste pauta no sistema")
	public ResponseEntity<PautaViewModel> add(@RequestBody @Valid PautaDto pautaDto){
		
		Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
		pauta = pautaService.add(pauta);
		
		return new ResponseEntity<>(modelMapper.map(pauta, PautaViewModel.class), HttpStatus.CREATED);
	}
	
	@PutMapping("/v1.0/")
	@ApiOperation(value="Abertura para votação de uma pauta")
	public ResponseEntity<String> iniciarPauta(Integer idPauta) {
		return new ResponseEntity<>(pautaService.iniciarPauta(idPauta), HttpStatus.OK);
	}
	
	@GetMapping("/v1.0/ObterResultadoPauta")
	@ApiOperation(value="Obter resultado de pauta")
	public ResponseEntity<ResultadoVotacao> obter(Integer idPauta) {
		return new ResponseEntity<>(pautaService.obterResultadoPorPauta(idPauta), HttpStatus.OK);
	}
		
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de pautas")
	public ResponseEntity<List<PautaViewModel>> getAll() {
		
		var pautas = pautaService.getAll();
		
		List<PautaViewModel> pautaViewModel = new ArrayList<>();
		pautas.forEach(pauta -> pautaViewModel.add(modelMapper.map(pauta, PautaViewModel.class)));
		
		return new ResponseEntity<>(pautaViewModel, HttpStatus.OK);
	}
}
