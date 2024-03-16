package com.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoa.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
