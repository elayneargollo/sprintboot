package br.com.solutis.votacao.controller;

import java.util.ArrayList;
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
import br.com.solutis.votacao.config.mapper.AssociadoMapper;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.model.viewModel.AssociadoViewModel;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/associado")
@Api(value="API ASSOCIADO")
public class AssociadoController {

	@Autowired
	private IAssociadoService associadoService;

	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de associados")
	public ResponseEntity<Page<Associado>> getAllPage(@PageableDefault(sort="nome", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return ResponseEntity.ok(associadoService.getAll(paginacao));
	}
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	@ApiOperation(value="Retorna um associado por id")
	public ResponseEntity<AssociadoViewModel> getById(@PathVariable("id") Integer id) {

		Optional<Associado> associado = associadoService.getById(id);
		
		if(associado.isPresent())
			return ResponseEntity.ok().body(AssociadoMapper.converterByAssociadoViewModel(associado.get()));
			
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de associados")
	public ResponseEntity<List<AssociadoViewModel>> getAll() {
		
		var associados = associadoService.getAll();
		
		List<AssociadoViewModel> associadoViewModel = new ArrayList<>();
		associados.forEach(associado -> associadoViewModel.add(AssociadoMapper.converterByAssociadoViewModel(associado)));
	    
		return ResponseEntity.ok(associadoViewModel);
	}
	
	@PostMapping("/v1.1/")
	@ApiOperation(value="Cria um associado")
	public ResponseEntity<AssociadoViewModel> add(@RequestBody @Valid AssociadoDto associadoDto) {
		
		Associado associado = AssociadoMapper.converterByAssociado(associadoDto);
		associado = associadoService.add(associado);

		return ResponseEntity.ok(AssociadoMapper.converterByAssociadoViewModel(associado));
	}
}
