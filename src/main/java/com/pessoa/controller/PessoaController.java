package com.pessoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pessoa.dto.PessoaDto;
import com.pessoa.repository.PessoaService;

@RestController
@RequestMapping("cadastro")
public class PessoaController {
    @Autowired
	private PessoaService service;
	
    
    @PostMapping
	public ResponseEntity<PessoaDto>cadastrarPessoa(@RequestBody PessoaDto pessoa,UriComponentsBuilder uriBuilder){		
		var uri = uriBuilder.path("cadastro/{id}").buildAndExpand(pessoa.id()).toUri();
		var salvar =  service.cadastrarPessoa(pessoa);
		return ResponseEntity.created(uri).body(new PessoaDto(salvar)); 
				
	}
    
    @GetMapping
    public ResponseEntity<List<PessoaDto>>listar(){
    var  lista = service.listar().stream().map(PessoaDto::new).toList();
    return ResponseEntity.ok(lista);
    }
    @GetMapping("{id}")
    public ResponseEntity<PessoaDto>buscarPorId(@PathVariable Long id){
    	var busca  =service.findbyId(id);
    	return ResponseEntity.ok().body(new PessoaDto(busca));
    			
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>excluir(@PathVariable Long id){
    	service.excluir(id);
    	return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<?>atualizar(@RequestBody PessoaDto atualize){    	
    	var atualizando = service.atualizarPessoa(atualize);    	
    	return ResponseEntity.ok().body(new PessoaDto(atualizando));
    			
    
    }
}
