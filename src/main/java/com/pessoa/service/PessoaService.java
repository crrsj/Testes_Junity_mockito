package com.pessoa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoa.dto.PessoaDto;
import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {
    @Autowired
	private PessoaRepository repository;
	
	public Pessoa cadastrarPessoa(PessoaDto pessoa) {
		var cadastre = new Pessoa(pessoa);
		return repository.save(cadastre);
	    
		
	}
	
	public List<Pessoa>listar(){
		return repository.findAll();
	}
	
	public Pessoa  findbyId(Long id) {
		Optional<Pessoa> busca = repository.findById(id);
		return busca.orElseThrow(()-> new IllegalArgumentException("Pessoa não encontrada."));
	}
	public void excluir(Long id) {
		 findbyId(id);
		repository.deleteById(id);
	}
	
    public Pessoa atualizarPessoa(PessoaDto pessoas) {
	   var atualiza = new Pessoa(pessoas);	 
	   if(atualiza.getId() != null) {
	    return repository.save(atualiza);
	   
	   } else {
		   throw new EntityNotFoundException("ID não encontrado");
	   }
   }
	   
   }
	   



