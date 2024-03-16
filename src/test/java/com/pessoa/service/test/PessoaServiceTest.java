package com.pessoa.service.test;

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

import com.pessoa.dto.PessoaDto;
import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.repository.PessoaService;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@SpringBootTest
public class PessoaServiceTest {
	
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
	@Test
	void listar() {
		
	}
	@Test
	void cadastrarPessoa() {
		
	}
	void atualizarPessoa() {
		
	}
	void excluir() {
		
	}
	private void start() {
    pessoa = new Pessoa(ID,NOME,TELEFONE,EMAIL);
    pessoaDto = new PessoaDto(1L,NOME,TELEFONE,EMAIL);
    optionalPessoa = Optional.of(new Pessoa(1L,NOME,TELEFONE,EMAIL));
	}
}
