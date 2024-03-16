package com.pessoa.entity;

import java.io.Serializable;

import com.pessoa.dto.PessoaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
public class Pessoa implements Serializable {
  
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String telefone ;
    private String email;
    
    public Pessoa(PessoaDto pessoa) {
    	this.id = pessoa.id();
  		this.nome = pessoa.nome();
  		this.telefone = pessoa.telefone();
  		this.email = pessoa.email();
  	}

	public Pessoa(Long id, String nome, String telefone, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	


	
	

	
	
}
