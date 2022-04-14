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
	public void GetById() throws Exception {

		Optional<Sessao> sessaoMock = Optional.of(SessaoMock.ObterSessao());

		when(sessaoService.GetById(sessaoMock.get().getId())).thenReturn(sessaoMock);

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaoMock)));

		Optional<Sessao> sessaoReturn = sessaoService.GetById(sessaoMock.get().getId());

		assertNotNull(sessaoReturn.toString());
	}

	@Test
	public void GetAll() throws Exception {

		List<Sessao> sessaosMock = SessaoMock.ObterSessoes();

		when(sessaoService.GetAll()).thenReturn(sessaosMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaosMock)));

		List<Sessao> sessaosReturn = sessaoService.GetAll();

		assertNotNull(sessaosReturn);
		assertThat(sessaosReturn).isEqualTo(sessaosMock);
		assertThat(sessaosReturn.size()).isEqualTo(sessaosMock.size());
	}

	@Test
	public void GetAllv11() throws Exception {

		List<Sessao> sessaosMock = SessaoMock.ObterSessoes();

		when(sessaoService.GetAll()).thenReturn(sessaosMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(sessaosMock)));

		List<Sessao> sessaosReturn = sessaoService.GetAll();

		assertNotNull(sessaosReturn);
		assertThat(sessaosReturn).isEqualTo(sessaosMock);
		assertThat(sessaosReturn.size()).isEqualTo(sessaosMock.size());
	}

	@Test
	public void GetById_404() throws Exception {
		mock.perform(get(BASE_URL + "v1.0" + "/2")).andExpect(status().isNotFound());
	}

	@Test
	public void GetAdd() throws Exception {

		Sessao sessaoMock = SessaoMock.ObterSessao();
		
		when(sessaoService.Add(sessaoMock)).thenReturn(sessaoMock);

		mock.perform(post(BASE_URL + "/v1.1/").content(objectMapper.writeValueAsString(sessaoMock))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		Sessao sessaosReturn = sessaoService.Add(sessaoMock);

		assertNotNull(sessaosReturn);
	}
}
