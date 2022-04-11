package br.com.solutis.votacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.solutis.votacao.config.mapper.Mapper;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/associado")
@Api(value="API ASSOCIADO")
public class AssociadoController {

	@Autowired
	private IAssociadoService associadoService;

	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de associados")
	public ResponseEntity<Page<Associado>> GetAllPage(@PageableDefault(sort="nome", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return ResponseEntity.ok(associadoService.GetAll(paginacao));
	}
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	@ApiOperation(value="Retorna um associado por id")
	public ResponseEntity<Associado> getById(@PathVariable("id") Integer id) {

		Optional<Associado> associado = associadoService.GetById(id);
		
		if(associado.isPresent())
			return ResponseEntity.ok().body(associado.get());
			
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de associados")
	public ResponseEntity<List<Associado>> GetAll() {
		return ResponseEntity.ok(associadoService.GetAll());
	}
	
	@PostMapping("/v1.1/")
	@ApiOperation(value="Cria um associado")
	public ResponseEntity<Associado> Add(@RequestBody @Valid AssociadoDto associadoDto) {
		Associado associado = Mapper.converterByAssociado(associadoDto);
		return ResponseEntity.ok(associadoService.Add(associado));
	}
}
