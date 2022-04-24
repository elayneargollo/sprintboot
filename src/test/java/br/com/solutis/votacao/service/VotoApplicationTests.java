package br.com.solutis.votacao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.solutis.votacao.exception.AssociadoNaoExiste;
import br.com.solutis.votacao.exception.PautaNaoAbertaException;
import br.com.solutis.votacao.exception.VotoNaoUnicoExcepiton;
import br.com.solutis.votacao.mocks.PautaMock;
import br.com.solutis.votacao.mocks.VotoMock;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(controllers = VotoService.class)
class VotoServiceTest {

	@Autowired
	private VotoService votoService;
	
	@MockBean
	IVotoRepository votoRepository;

	@MockBean
	private IAssociadoRepository associadoRepository;
	
	@MockBean
	private IPautaRepository pautaRepository;
	
	@Test
	void Add() throws Exception {

		var voto = VotoMock.ObterVotos().get(0);
		var pauta = PautaMock.ObterPauta().get();
		
		when(associadoRepository.existsById(voto.getAssociadoId())).thenReturn(true);
		when(pautaRepository.getById(voto.getPautaId())).thenReturn(pauta);
		when(votoRepository.findAll()).thenReturn(new ArrayList<Voto>());

		when(votoService.add(voto)).thenReturn(voto);

		Voto votosReturn = votoService.add(voto);

		assertNotNull(votosReturn);
	}
	
	@Test
	void Add_VotosUnicos() throws Exception {

		var voto = VotoMock.ObterVotos().get(0);
		var pauta = PautaMock.ObterPauta().get();
		
		when(associadoRepository.existsById(voto.getAssociadoId())).thenReturn(true);
		when(pautaRepository.getById(voto.getPautaId())).thenReturn(pauta);
		when(votoRepository.findAll()).thenReturn(VotoMock.ObterVotos());
		
		assertThrows(VotoNaoUnicoExcepiton.class, () -> votoService.add(voto));
	}
	
	@Test
	void Add_PautaFechada() throws Exception {

		var voto = VotoMock.ObterVotos().get(0);
		var pauta = PautaMock.ObterPauta().get();
		pauta.setStatus(Status.FECHADO);
		
		when(associadoRepository.existsById(voto.getAssociadoId())).thenReturn(true);
		when(pautaRepository.getById(voto.getPautaId())).thenReturn(pauta);
		when(votoRepository.findAll()).thenReturn( VotoMock.ObterVotos());
		
		assertThrows(PautaNaoAbertaException.class, () -> votoService.add(voto));
	}
	
	@Test
	void Add_AssociadoNaoEncontrado() throws Exception {

		var voto = VotoMock.ObterVotos().get(0);
		var pauta = PautaMock.ObterPauta().get();
		
		when(associadoRepository.existsById(voto.getAssociadoId())).thenReturn(false);
		when(pautaRepository.getById(voto.getPautaId())).thenReturn(pauta);
		when(votoRepository.findAll()).thenReturn( VotoMock.ObterVotos());
		
		assertThrows(AssociadoNaoExiste.class, () -> votoService.add(voto));
	}
	
	@Test
	void GetById() throws Exception {

		var voto = VotoMock.ObterVotos().get(0);

		when(votoRepository.findById(voto.getId())).thenReturn(Optional.of(voto));

		var votoReturn = votoService.getById(voto.getId());

		assertNotNull(votoReturn);
	}
	
	@Test
	void GetAll() throws Exception {

		var votos =  VotoMock.ObterVotos();

		when(votoRepository.findAll()).thenReturn(votos);

		List<Voto> votosReturn = votoService.getAll();

		assertNotNull(votosReturn);
	}
}
