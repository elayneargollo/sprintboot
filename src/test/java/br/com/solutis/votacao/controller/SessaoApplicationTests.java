package br.com.solutis.votacao.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.mocks.SessaoMock;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.service.interfaces.ISessaoService;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = SessaoController.class)
class SessaoControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private ISessaoService sessaoService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/sessao/";

	@Test
	void GetAll() throws Exception {

		List<Sessao> sessaosMock = SessaoMock.ObterSessoes();

		when(sessaoService.getAll()).thenReturn(sessaosMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaosMock)));

		List<Sessao> sessaosReturn = sessaoService.getAll();

		assertNotNull(sessaosReturn);
		assertThat(sessaosReturn).isEqualTo(sessaosMock);
		assertThat(sessaosReturn.size()).hasSameClassAs(sessaosMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Sessao> sessaosMock = SessaoMock.ObterSessoes();

		when(sessaoService.getAll()).thenReturn(sessaosMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaosMock)));

		List<Sessao> sessaosReturn = sessaoService.getAll();

		assertNotNull(sessaosReturn);
		assertThat(sessaosReturn).isEqualTo(sessaosMock);
		assertThat(sessaosReturn.size()).hasSameClassAs(sessaosMock.size());
	}

}
