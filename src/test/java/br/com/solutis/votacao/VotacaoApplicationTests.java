package br.com.solutis.votacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.controller.VotacaoController;
import br.com.solutis.votacao.mocks.VotacaoMock;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.service.interfaces.IVotacaoService;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = VotacaoController.class)
class VotacaoControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IVotacaoService votacaoService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/votacao/";

	@Test
	public void GetAdd() throws Exception {

		Votacao votacaoMock = VotacaoMock.ObterVotacao();
		
		when(votacaoService.Add(votacaoMock)).thenReturn(votacaoMock);

		mock.perform(post(BASE_URL + "/v1.1/").content(objectMapper.writeValueAsString(votacaoMock))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		Votacao votacaosReturn = votacaoService.Add(votacaoMock);

		assertNotNull(votacaosReturn);
	}
}
