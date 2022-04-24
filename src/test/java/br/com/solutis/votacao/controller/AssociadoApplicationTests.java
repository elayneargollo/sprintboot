package br.com.solutis.votacao.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.config.mapper.AssociadoMapper;
import br.com.solutis.votacao.mocks.AssociadoMock;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.repository.ServiceCpf;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AssociadoController.class)
class AssociadoApplicationTests {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IAssociadoService associadoService;

	@MockBean
	private ServiceCpf serviceCpf;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/associado/";

	@Test
	void GetById() throws Exception {

		Associado associadoMock = AssociadoMock.GetAssociado().get();

		when(associadoService.getById(associadoMock.getId())).thenReturn(AssociadoMock.GetAssociado());

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadoMock)));

		Optional<Associado> associadoReturn = associadoService.getById(associadoMock.getId());

		assertNotNull(associadoReturn.toString());
		assertThat(associadoReturn.get().getNome()).isEqualTo(associadoMock.getNome());
		assertThat(associadoReturn.get().getEmail()).isEqualTo(associadoMock.getEmail());
		assertThat(associadoReturn.get().getId()).isEqualTo(associadoMock.getId());
	}

	@Test
	void GetAll() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoService.getAll()).thenReturn(associadosMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadosMock)));

		List<Associado> associadosReturn = associadoService.getAll();

		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).hasSameClassAs(associadosMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoService.getAll()).thenReturn(associadosMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadosMock)));

		List<Associado> associadosReturn = associadoService.getAll();

		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).hasSameClassAs(associadosMock.size());
	}

	@Test
	void GetById_404() throws Exception {
		mock.perform(get(BASE_URL + "v1.0" + "/2")).andExpect(status().isNotFound());
	}

	@Test
	void GetAdd() throws Exception {

		AssociadoDto associadoDtoMock = AssociadoMock.GetAssociadoDto();
		Associado associadoMock = AssociadoMapper.converterByAssociado(associadoDtoMock);

		when(associadoService.add(associadoMock)).thenReturn(associadoMock);

		mock.perform(MockMvcRequestBuilders.post(BASE_URL + "/v1.1/").content(asJsonString(associadoDtoMock))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Associado associadosReturn = associadoService.add(associadoMock);
		var associadoViewModel = AssociadoMapper.converterByAssociadoViewModel(associadosReturn);

		assertNotNull(associadoViewModel);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
