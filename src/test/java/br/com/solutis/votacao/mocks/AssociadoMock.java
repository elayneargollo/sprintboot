package br.com.solutis.votacao.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;

public final class AssociadoMock {

	public static Optional<Associado> GetAssociado() {
		return Optional.of(new Associado(1, "Elayne Natália", "elayne_natalia@email.com.br", "03715620103"));
	}
	
	public static AssociadoDto GetAssociadoDto() {
		return new AssociadoDto("03715620103", "Elayne Natália", "elayne_natalia@email.com.br");
	}

	public static List<Associado> GetAssociados() {
		List<Associado> associados = new ArrayList<Associado>();
		associados.add(new Associado(1, "Elayne Natália", "elayne_natalia@email.com.br", "03715620103"));
		associados.add(new Associado(2, "Natália", "natalia@email.com.br", "03715620104"));
		
        return associados;
    }
}
