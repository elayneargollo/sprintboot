package br.com.solutis.votacao.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.config.mapper.SessaoMapper;
import br.com.solutis.votacao.mocks.SessaoMock;
import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.viewModel.SessaoViewModel;
import br.com.solutis.votacao.service.interfaces.ISessaoService;
import org.springframework.http.MediaType;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


	@Test
	void GetAdd() throws Exception {

		SessaoDto sessaoDtoMock = SessaoMock.ObterSessaoDto();
		Sessao sessaoMock = SessaoMapper.converterBySessao(sessaoDtoMock);
		
		when(sessaoService.add(sessaoMock)).thenReturn(sessaoMock);

		mock.perform(MockMvcRequestBuilders.post(BASE_URL + "/v1.0/").content(asJsonString(sessaoMock))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Sessao sessaosReturn = sessaoService.add(sessaoMock);
		SessaoViewModel sessaoViewModel = SessaoMapper.converterBySessaoViewModel(sessaosReturn);

		assertNotNull(sessaoViewModel);
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
