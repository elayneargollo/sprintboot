package br.com.solutis.votacao.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.model.Votacao;
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
	public ResponseEntity<Votacao> Add(@RequestBody @Valid Votacao votacao) {
		return ResponseEntity.ok(votacaoService.Add(votacao));
	}
}
