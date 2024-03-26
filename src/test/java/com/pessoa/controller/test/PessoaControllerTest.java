package com.pessoa.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.pessoa.controller.PessoaController;
import com.pessoa.dto.PessoaDto;
import com.pessoa.entity.Pessoa;
import com.pessoa.service.PessoaService;






@SpringBootTest
public class PessoaControllerTest {
	
	private static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada";

	private static final int INDEX = 0;

	private static final String EMAIL = "carlos@gmail.com";

	private static final String TELEFONE = "123";

	private static final String NOME = "Carlos";

	private static final long ID = 1L;
	
private PessoaDto pessoaDto;
	
	private Pessoa pessoa;
	
	@InjectMocks
	private PessoaController controller;
	
	@Mock
	private PessoaService service;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	void findByIdSucesso() {
		when(service.findbyId(anyLong())).thenReturn(pessoa);
		
		ResponseEntity<PessoaDto>response = controller.buscarPorId(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(PessoaDto.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().id());
		assertEquals(NOME, response.getBody().nome());
		assertEquals(TELEFONE, response.getBody().telefone());
		assertEquals(EMAIL, response.getBody().email());
		
	}
	
	@Test
	void listar() {
		
	}
	
	@Test
	void cadastrarPessoaSucesso() {
		
	}
	
	void atualizarPessoaSucesso() {
		
	}
	
	void excluir() {
		
	}
	
	private void start() {
	    pessoa = new Pessoa(ID,NOME,TELEFONE,EMAIL);
	    pessoaDto = new PessoaDto(1L,NOME,TELEFONE,EMAIL);
	    
		}
}
