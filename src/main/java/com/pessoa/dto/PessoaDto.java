package com.pessoa.dto;

import com.pessoa.entity.Pessoa;

public record PessoaDto(
		Long id,
		String nome,
		String telefone, 
		String email) {

	public PessoaDto(Pessoa salvar) {
		this(
			salvar.getId(),
			salvar.getNome(),
			salvar.getTelefone(),
			salvar.getEmail());
	}

	
	  
}
