package br.com.solutis.votacao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.solutis.votacao.mocks.VotacaoMock;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.repository.IVotacaoRepository;
import br.com.solutis.votacao.service.interfaces.IVotoService;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(controllers = VotacaoService.class)
class VotacaoServiceTest {
	
	@MockBean
	private IVotacaoRepository votacaoRepository;
	
	@Autowired
	private VotacaoService votacaoService;
	
	@MockBean
	private IVotoService votoService;

	@Test
	void GetAdd() throws Exception {

		Votacao votacaoMock = VotacaoMock.ObterVotacao();
		
		when(votoService.add(votacaoMock.getVoto())).thenReturn(votacaoMock.getVoto());
		when(votacaoRepository.save(votacaoMock)).thenReturn(votacaoMock);

		Votacao votacaosReturn = votacaoService.add(votacaoMock);

		assertNotNull(votacaosReturn);
	}
}
