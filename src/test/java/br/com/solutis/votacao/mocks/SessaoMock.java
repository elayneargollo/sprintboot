package br.com.solutis.votacao.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.solutis.votacao.model.entity.Sessao;

public class SessaoMock {
	
	public static List<Sessao> ObterSessoes()
	{
		List<Sessao> sessoes = new ArrayList<Sessao>(); 
		
		sessoes.add(new Sessao("Teste unitário de sessao com 0s", 0, "Teste unitário de sessao tipo"));
		sessoes.add(new Sessao("Teste unitário de sessao com 10s", 10, "Teste unitário de sessao tipo"));
		sessoes.add(new Sessao("Teste unitário de sessao com 20s", 20, "Teste unitário de sessao tipo"));
		
		return sessoes;
	}
	
	public static Sessao ObterSessao()
	{		
		return new Sessao("Teste unitário de sessao com 20s", 20, "Teste unitário de sessao tipo");
	}
}
