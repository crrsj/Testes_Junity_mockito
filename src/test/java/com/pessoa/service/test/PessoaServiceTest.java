package com.pessoa.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.pessoa.dto.PessoaDto;
import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.service.PessoaService;

@SpringBootTest
public class PessoaServiceTest {
	
	private static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada";

	private static final int INDEX = 0;

	private static final String EMAIL = "carlos@gmail.com";

	private static final String TELEFONE = "123";

	private static final String NOME = "Carlos";

	private static final long ID = 1L;

	@Mock
	private PessoaRepository repository;
	
	@InjectMocks
	private PessoaService service;
	
	private PessoaDto pessoaDto;
	
	private Pessoa pessoa;
	
	private Optional<Pessoa>optionalPessoa;

	@BeforeEach
	void setup() {
	MockitoAnnotations.openMocks(this);
	start();
	}
	@Test
	void findByIdSucesso() {
		when(repository.findById(anyLong())).thenReturn(optionalPessoa);
		Pessoa  response = service.findbyId(ID);
		assertNotNull(response);
		assertEquals(Pessoa.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(TELEFONE, response.getTelefone());
		assertEquals(EMAIL, response.getEmail());
	}
	 void findByIdFalha() {
		 when(repository.findById(anyLong())).thenThrow(new IllegalArgumentException(PESSOA_NAO_ENCONTRADA));
		 try {
			service.findbyId(ID);
		 }catch(Exception ex){
			 assertEquals(IllegalArgumentException.class, ex.getClass());
			 assertEquals(PESSOA_NAO_ENCONTRADA, ex.getMessage());
		 }
	 }
	
	@Test
	void listar() {
		when(repository.findAll()).thenReturn(List.of(pessoa));
		List<Pessoa> response = service.listar();
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Pessoa.class, response.get(INDEX).getClass());
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NOME, response.get(INDEX).getNome());
		assertEquals(TELEFONE, response.get(INDEX).getTelefone());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
	}
	@Test
	void cadastrarPessoaSucesso() {
		 when(repository.save(any())).thenReturn(pessoa);
		 Pessoa response = service.cadastrarPessoa(pessoaDto);
		 assertNotNull(response);
		 assertEquals(Pessoa.class, response.getClass());
		 assertEquals(ID, response.getId());
		 assertEquals(NOME, response.getNome());
		 assertEquals(TELEFONE, response.getTelefone());
		 assertEquals(EMAIL, response.getEmail());
	}
	void atualizarPessoaSucesso() {
		when(repository.save(any())).thenReturn(pessoa);
		 Pessoa response = service.atualizarPessoa(pessoaDto);
		 assertNotNull(response);
		 assertEquals(Pessoa.class, response.getClass());
		 assertEquals(ID, response.getId());
		 assertEquals(NOME, response.getNome());
		 assertEquals(TELEFONE, response.getTelefone());
		 assertEquals(EMAIL, response.getEmail());
	}
	  @Test
	  void excluir() {
	  when(repository.findById(anyLong())).thenReturn(optionalPessoa);
	  doNothing().when(repository).deleteById(anyLong());
	  service.excluir(ID);
	  verify(repository,times(1)).deleteById(anyLong());
	}
	void excluirCasoFalhe() {
		when(repository.findById(anyLong())).thenThrow(new IllegalArgumentException(PESSOA_NAO_ENCONTRADA));
		try {
			service.excluir(ID);
		} catch (Exception e) {
			assertEquals(PESSOA_NAO_ENCONTRADA, e.getMessage());
		}
		
	}
	
	
	private void start() {
    pessoa = new Pessoa(ID,NOME,TELEFONE,EMAIL);
    pessoaDto = new PessoaDto(1L,NOME,TELEFONE,EMAIL);
    optionalPessoa = Optional.of(new Pessoa(1L,NOME,TELEFONE,EMAIL));
	}
}
