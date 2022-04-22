package br.com.solutis.votacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.controller.SessaoController;
import br.com.solutis.votacao.mocks.SessaoMock;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.service.interfaces.ISessaoService;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
	void GetById() throws Exception {

		Optional<Sessao> sessaoMock = Optional.of(SessaoMock.ObterSessao());

		when(sessaoService.getById(sessaoMock.get().getId())).thenReturn(sessaoMock);

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaoMock)));

		Optional<Sessao> sessaoReturn = sessaoService.getById(sessaoMock.get().getId());

		assertNotNull(sessaoReturn.toString());
	}

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

	@Test
	void GetById_404() throws Exception {
		mock.perform(get(BASE_URL + "v1.0" + "/2")).andExpect(status().isNotFound());
	}

	@Test
	void GetAdd() throws Exception {

		Sessao sessaoMock = SessaoMock.ObterSessao();
		
		when(sessaoService.add(sessaoMock)).thenReturn(sessaoMock);

		mock.perform(post(BASE_URL + "/v1.1/").content(objectMapper.writeValueAsString(sessaoMock))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		Sessao sessaosReturn = sessaoService.add(sessaoMock);

		assertNotNull(sessaosReturn);
	}
}
