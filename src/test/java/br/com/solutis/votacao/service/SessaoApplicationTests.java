package br.com.solutis.votacao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.solutis.votacao.exception.NotFoundException;
import br.com.solutis.votacao.mocks.SessaoMock;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(controllers = SessaoService.class)
class SessaoServiceTest {

	@MockBean
	private ISessaoRepository sessaoRepository;
	
	@MockBean
	private IPautaRepository pautaRepository;
	
	@Autowired
	private SessaoService sessaoService;

	@Test
	void GetAll() throws Exception {

		List<Sessao> sessaosMock = SessaoMock.ObterSessoes();

		when(sessaoRepository.findAll()).thenReturn(sessaosMock);

		List<Sessao> sessaosReturn = sessaoService.getAll();

		assertNotNull(sessaosReturn);
		assertThat(sessaosReturn).isEqualTo(sessaosMock);
		assertThat(sessaosReturn.size()).hasSameClassAs(sessaosMock.size());
	}
	
	@Test
	void GetById() throws Exception {

		Sessao sessaosMock = SessaoMock.ObterSessao();

		when(sessaoRepository.findById(sessaosMock.getId())).thenReturn(Optional.of(sessaosMock));

		assertThrows(NotFoundException.class, () -> sessaoService.getById(sessaosMock.getId()));
		
	}
	
	@Test
	void Add() throws Exception {

		Sessao sessaosMock = SessaoMock.ObterSessao();

		when(sessaoRepository.save(sessaosMock)).thenReturn(sessaosMock);

		var sessaosReturn = sessaoService.add(sessaosMock);

		assertNotNull(sessaosReturn);
	}
}
