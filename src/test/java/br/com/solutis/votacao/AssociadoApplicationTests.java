package br.com.solutis.votacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.config.mapper.AssociadoMapper;
import br.com.solutis.votacao.controller.AssociadoController;
import br.com.solutis.votacao.mocks.AssociadoMock;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AssociadoController.class)
class AssociadorControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IAssociadoService associadoService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/associado/";

	@Test
	void GetById() throws Exception {

		Associado associadoMock = AssociadoMock.GetAssociado().get();

		when(associadoService.GetById(associadoMock.getId())).thenReturn(AssociadoMock.GetAssociado());

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadoMock)));

		Optional<Associado> associadoReturn = associadoService.GetById(associadoMock.getId());

		assertNotNull(associadoReturn.toString());
		assertThat(associadoReturn.get().getNome()).isEqualTo(associadoMock.getNome());
		assertThat(associadoReturn.get().getEmail()).isEqualTo(associadoMock.getEmail());
		assertThat(associadoReturn.get().getId()).isEqualTo(associadoMock.getId());
	}

	@Test
	void GetAll() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoService.GetAll()).thenReturn(associadosMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadosMock)));

		List<Associado> associadosReturn = associadoService.GetAll();

		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).hasSameClassAs(associadosMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoService.GetAll()).thenReturn(associadosMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(associadosMock)));

		List<Associado> associadosReturn = associadoService.GetAll();

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
		
		when(associadoService.Add(associadoMock)).thenReturn(associadoMock);

		mock.perform(post(BASE_URL + "/v1.1/").content(objectMapper.writeValueAsString(associadoMock))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		Associado associadosReturn = associadoService.Add(associadoMock);

		assertNotNull(associadosReturn);
	}
}
