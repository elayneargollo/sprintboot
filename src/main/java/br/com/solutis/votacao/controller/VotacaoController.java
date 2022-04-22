package br.com.solutis.votacao.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.config.mapper.VotacaoMapper;
import br.com.solutis.votacao.model.dto.VotacaoDto;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.viewModel.VotacaoViewModel;
import br.com.solutis.votacao.service.interfaces.IVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/votacao")
@Api(value="API VOTACAO")
public class VotacaoController {

	@Autowired
	private IVotacaoService votacaoService;
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Permite votação no sistema")
	public ResponseEntity<VotacaoViewModel> add(@RequestBody @Valid VotacaoDto votacaoDto) {
		
		Votacao votacao = VotacaoMapper.ConverteParaVotacao(votacaoDto);
		votacao = votacaoService.add(votacao);
		
		return ResponseEntity.ok(VotacaoMapper.converterByVotacaoViewModel(votacao));
	}
}
