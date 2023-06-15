package com.projeto.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.academia.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value="select aluno.id,aluno.data_de_matricula,aluno.data_de_nascimento,aluno.name,aluno.second_name "
    		+ "from aluno LEFT JOIN  mensalidade "
    		+ "ON aluno.id = mensalidade.aluno_id "
    		+ "WHERE NOT(EXTRACT(MONTH FROM(mensalidade.data_pagamento))=:mes) "
    		+ "union "
    		+ "select a.id,a.data_de_matricula,a.data_de_nascimento,a.name,a.second_name from aluno a "
    		+ "where not exists (select m from mensalidade m where m.aluno_id=a.id)"
    		+ " offset :offset limit :size",nativeQuery=true)
	
//	@Query("SELECT a FROM Aluno a LEFT JOIN a.mensalidades m " +
//              "WHERE NOT(EXTRACT(MONTH FROM m.dataPagamento) = :mes) " +
//              "AND NOT EXISTS (SELECT m2 FROM Mensalidade m2 WHERE m2.aluno = a)")
	List<Aluno> findAllAlunosDevedores(int mes, int offset, int size);
    
    
    
    @Query(value="select count(*) as total_linhas from (select aluno.id,aluno.data_de_matricula,aluno.data_de_nascimento,aluno.name,aluno.second_name "
    		+ "from aluno LEFT JOIN  mensalidade "
    		+ "ON aluno.id = mensalidade.aluno_id "
    		+ "WHERE NOT(EXTRACT(MONTH FROM(mensalidade.data_pagamento))=:mes) "
    		+ "union "
    		+ "select a.id,a.data_de_matricula,a.data_de_nascimento,a.name,a.second_name from aluno a "
    		+ "where not exists (select m from mensalidade m where m.aluno_id=a.id))"
    		+ " as sub_consulta",nativeQuery=true)
    Long contarTotalAlunosDevedores(int mes);
}
