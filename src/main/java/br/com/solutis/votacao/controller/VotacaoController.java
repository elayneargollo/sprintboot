package br.com.solutis.votacao.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.model.dto.VotacaoDto;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.viewModel.VotacaoViewModel;
import br.com.solutis.votacao.service.interfaces.IVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/votacao")
@Api(value="API VOTACAO")
public class VotacaoController {

	@Autowired
	private IVotacaoService votacaoService;
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Permite votação no sistema")
	public ResponseEntity<VotacaoViewModel> add(@RequestBody @Valid VotacaoDto votacaoDto) {
		
		Votacao votacao = modelMapper.map(votacaoDto, Votacao.class);
		votacao = votacaoService.add(votacao);
		
		return ResponseEntity.ok(modelMapper.map(votacao, VotacaoViewModel.class));
	}
}
