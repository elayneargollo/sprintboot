package br.com.solutis.votacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.service.IAssociadoService;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {

	@Autowired
	private IAssociadoService associadoService;

	@GetMapping()
	public ResponseEntity<List<Associado>> GetAll() {
		return ResponseEntity.ok(associadoService.GetAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Associado> getById(@PathVariable("id") Integer id) {

		Optional<Associado> associado = associadoService.GetById(id);
		
		if(associado.isEmpty())
			return ResponseEntity.notFound().build();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(responseHeaders).body(associado.get());
	}
}
