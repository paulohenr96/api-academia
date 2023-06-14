package com.projeto.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.model.Mensalidade;
@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade,Long>{

	@Query("From Mensalidade m where m.aluno.id=:id")
	List<Mensalidade> mensalidadesAluno(Long id);

	@Transactional
	@Modifying
	@Query("DELETE from Mensalidade m where m.aluno.id=:id")
	void deletarMensalidadesAluno(Long id);

}
