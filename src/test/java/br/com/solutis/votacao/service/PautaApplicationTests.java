package br.com.solutis.votacao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.solutis.votacao.exception.PautaNaoAbertaException;
import br.com.solutis.votacao.exception.PautaNaoExisteException;
import br.com.solutis.votacao.mocks.PautaMock;
import br.com.solutis.votacao.mocks.VotoMock;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@WebMvcTest(controllers = PautaService.class)
class PautaServiceTest {
	
	@Autowired
	private PautaService pautaService;
	@MockBean
	IPautaRepository pautaRepository;
	@MockBean
	ISessaoRepository sessaoRepository;
	@MockBean
	IVotoRepository votoRepository;


	@Test
	void GetById() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaRepository.existsById(pautaMock.get().getId())).thenReturn(true);	
		when(pautaRepository.findById(pautaMock.get().getId())).thenReturn(pautaMock);

		Optional<Pauta> pautaReturn = pautaService.getById(pautaMock.get().getId());

		assertNotNull(pautaReturn.toString());
	}
	
	@Test
	void GetById_PautaNaoEncontrada() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaRepository.existsById(pautaMock.get().getId())).thenReturn(false);	
		when(pautaRepository.findById(pautaMock.get().getId())).thenReturn(pautaMock);

		assertThrows(PautaNaoExisteException.class, () -> pautaService.getById(pautaMock.get().getId()));
	}

	@Test
	void GetAll() throws Exception {

		List<Pauta> pautasMock = PautaMock.ObterPautas();

		when(pautaRepository.findAll()).thenReturn(pautasMock);

		List<Pauta> pautasReturn = pautaService.getAll();

		assertNotNull(pautasReturn);
	}

	@Test
	void GetAdd() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();

		when(sessaoRepository.save(pautaMock.getSessao())).thenReturn(pautaMock.getSessao());
		when(pautaRepository.save(pautaMock)).thenReturn(pautaMock);	

		Pauta pautaReturn = pautaService.add(pautaMock);

		assertNotNull(pautaReturn);
	}

	@Test
	void IniciarPauta_StatusAberta() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(true);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		
		assertThrows(PautaNaoAbertaException.class, () -> pautaService.iniciarPauta(pautaMock.getId()));
	}
	
	@Test
	void IniciarPauta() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();
		pautaMock.setStatus(Status.CRIADO);

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(true);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		
		var mensagem = pautaService.iniciarPauta(pautaMock.getId());
		
		assertNotNull(mensagem);
	}
	
	@Test
	void IniciarPauta_PautaNaoEncontrada() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();
		pautaMock.setStatus(Status.CRIADO);

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(false);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		
		assertThrows(PautaNaoExisteException.class, () -> pautaService.iniciarPauta(pautaMock.getId()));
	}
	
	@Test
	void obterResultadoPorPauta() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(true);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		when(votoRepository.obterVotosPorPauta(pautaMock.getId())).thenReturn(VotoMock.ObterVotos());	
		
		var resultado = pautaService.obterResultadoPorPauta(pautaMock.getId());
		
		assertNotNull(resultado);
	}
	
	@Test
	void obterResultadoPorPauta_PautaNaoEncontrada() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(false);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		when(votoRepository.obterVotosPorPauta(pautaMock.getId())).thenReturn(VotoMock.ObterVotos());	
		
		assertThrows(PautaNaoExisteException.class, () -> pautaService.obterResultadoPorPauta(pautaMock.getId()));
	}
	
	@Test
	void obterResultadoPorPauta_PautaCriada() throws Exception {

		Pauta pautaMock = PautaMock.ObterPauta().get();
		pautaMock.setStatus(Status.CRIADO);

		when(pautaRepository.existsById(pautaMock.getId())).thenReturn(true);	
		when(pautaRepository.getById(pautaMock.getId())).thenReturn(pautaMock);	
		when(votoRepository.obterVotosPorPauta(pautaMock.getId())).thenReturn(VotoMock.ObterVotos());	
		
		var resultado = pautaService.obterResultadoPorPauta(pautaMock.getId());
		
		assertNotNull(resultado);
	}

}
