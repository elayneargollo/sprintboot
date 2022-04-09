package br.com.solutis.votacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.service.IAssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/associado")
@Api(value="API ASSOCIADO")
@CrossOrigin(origins="*")
public class AssociadoController {

	@Autowired
	private IAssociadoService associadoService;

	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma lista de associados")
	public ResponseEntity<List<Associado>> GetAll() {
		return ResponseEntity.ok(associadoService.GetAll());
	}
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	@ApiOperation(value="Retorna um associado por id")
	public ResponseEntity<Associado> getById(@PathVariable("id") Integer id) {

		Optional<Associado> associado = associadoService.GetById(id);
		
		if(associado.isEmpty())
			return ResponseEntity.notFound().build();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(responseHeaders).body(associado.get());
	}
}
