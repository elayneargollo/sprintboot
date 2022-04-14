package br.com.solutis.votacao.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;

public final class AssociadoMock {

	public static Optional<Associado> GetAssociado() {
		return Optional.of(new Associado(1, "Elayne Natália", "elayne@email.com.br"));
	}
	
	public static AssociadoDto GetAssociadoDto() {
		return new AssociadoDto("Elayne Natália", "elayne@email.com.br");
	}


	public static List<Associado> GetAssociados() {
		List<Associado> associados = new ArrayList<Associado>();
		associados.add(new Associado(1, "Elayne Natália", "elayne@email.com.br"));
		associados.add(new Associado(2, "Natália", "natalia@email.com.br"));
		
        return associados;
    }
}
