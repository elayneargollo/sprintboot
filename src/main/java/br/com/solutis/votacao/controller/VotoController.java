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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.solutis.votacao.model.dto.VotoDto;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.viewModel.VotoViewModel;
import br.com.solutis.votacao.service.interfaces.IVotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/voto")
@Api(value="API VOTO")
public class VotoController {
	
	@Autowired
	private IVotoService votoService;
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("/v1.1/")
	@ApiOperation(value="Retorna uma lista de votos")
	public ResponseEntity<List<VotoViewModel>> getAll() {
		
		var votos = votoService.getAll();
		
		List<VotoViewModel> votoViewModel = new ArrayList<>();
		votos.forEach(voto -> votoViewModel.add(modelMapper.map(voto, VotoViewModel.class)));
		
		return new ResponseEntity<>(votoViewModel, HttpStatus.OK);
	}
	
	@GetMapping("/v1.0/")
	@ApiOperation(value="Retorna uma paginação de votos")
	public ResponseEntity<Page<Voto>> getAllPage(@PageableDefault(sort="descricao", direction = Direction.ASC, page=0, size=10) Pageable paginacao) {
		return new ResponseEntity<>(votoService.getAll(paginacao), HttpStatus.OK);
	}
	
	@PostMapping("/v1.0/")
	@ApiOperation(value="Persiste voto no sistema")
	public ResponseEntity<VotoViewModel> add(@RequestBody @Valid VotoDto votoDto) {
		
		Voto voto = modelMapper.map(votoDto, Voto.class);
		voto = votoService.add(voto);
		
		return new ResponseEntity<>(modelMapper.map(voto, VotoViewModel.class), HttpStatus.CREATED);
	}
	
	@GetMapping("/v1.0/{id}")
	@ApiOperation(value="Obter voto")
	public ResponseEntity<VotoViewModel> obter(@PathVariable("id") Integer id) {
		
		var votoEncontrado = votoService.getById(id);		
		return new ResponseEntity<>(modelMapper.map(votoEncontrado.orElse(new Voto()), VotoViewModel.class), HttpStatus.OK);
	}
}
