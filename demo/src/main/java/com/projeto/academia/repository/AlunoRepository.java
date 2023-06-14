package com.projeto.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.academia.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value="select * from aluno "
    		+ "LEFT JOIN  mensalidade "
    		+ "ON aluno.id = mensalidade.aluno_id "
    		+ "WHERE "
    		+ "NOT(EXTRACT(MONTH FROM(mensalidade.data_pagamento))=:mes)",nativeQuery=true)
	List<Aluno> findAllAlunosDevedores(@Param("mes")int mes);
}
