package com.projeto.academia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.academia.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
	
	
	@Query("from Usuario where username= :username")
	public Usuario loadByUserName(String username);
	
	
	@Query("Select u from Usuario u JOIN u.roles r WHERE r.role='ROLE_ALUNO'")
	public List<Usuario> findAllAlunos();

	@Query("Select u from Usuario u JOIN u.roles r WHERE r.role='ROLE_ALUNO' AND u.id= :id")
	public Optional<Usuario> findAlunoById(Long id);
}
