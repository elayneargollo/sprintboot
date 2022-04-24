package br.com.solutis.votacao.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.config.mapper.PautaMapper;
import br.com.solutis.votacao.mocks.PautaMock;
import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.viewModel.PautaViewModel;
import br.com.solutis.votacao.service.interfaces.IPautaService;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PautaController.class)
class PautaControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IPautaService pautaService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/pauta/";

	@Test
	void GetById() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaService.getById(pautaMock.get().getId())).thenReturn(pautaMock);

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautaMock)));

		Optional<Pauta> pautaReturn = pautaService.getById(pautaMock.get().getId());

		assertNotNull(pautaReturn.toString());
	}

	@Test
	void GetAll() throws Exception {

		List<Pauta> pautasMock = PautaMock.ObterPautas();

		when(pautaService.getAll()).thenReturn(pautasMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautasMock)));

		List<Pauta> pautasReturn = pautaService.getAll();

		assertNotNull(pautasReturn);
		assertThat(pautasReturn).isEqualTo(pautasMock);
		assertThat(pautasReturn.size()).hasSameClassAs(pautasMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Pauta> pautasMock = PautaMock.ObterPautas();

		when(pautaService.getAll()).thenReturn(pautasMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautasMock)));

		List<Pauta> pautasReturn = pautaService.getAll();

		assertNotNull(pautasReturn);
		assertThat(pautasReturn).isEqualTo(pautasMock);
		assertThat(pautasReturn.size()).hasSameClassAs(pautasMock.size());
	}

	@Test
	void GetById_404() throws Exception {
		mock.perform(get(BASE_URL + "v1.0" + "/2")).andExpect(status().isNotFound());
	}

	@Test
	void GetAdd() throws Exception {

		PautaDto pautaDto = PautaMock.ObterPautaDto();
		Pauta pautaMock = PautaMapper.converterByPauta(pautaDto);

		when(pautaService.add(pautaMock)).thenReturn(pautaMock);

		mock.perform(MockMvcRequestBuilders.post(BASE_URL + "/v1.0/").content(asJsonString(pautaDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Pauta pautaReturn = pautaService.add(pautaMock);
		PautaViewModel pautaViewModel = PautaMapper.converterByPautaViewModel(pautaReturn);

		assertNotNull(pautaViewModel);
	}

	@Test
	void IniciarPauta() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaService.iniciarPauta(pautaMock.get().getId())).thenReturn("Pauta aberta !");

		mock.perform(put(BASE_URL + "/v1.0/").content(objectMapper.writeValueAsString(pautaMock.get()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		String pautasReturn = pautaService.iniciarPauta(1);

		assertNotNull(pautasReturn);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
