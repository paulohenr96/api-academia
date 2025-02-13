package com.projeto.academia.beans;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.projeto.academia.model.Role;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;

@Component
public class AdminInitializerBean {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; // Inject a PasswordEncoder

	@PostConstruct
	public void createAdminUser() {
		if (usuarioRepository.loadByUserName("admin").isEmpty()) {
			String sqlCreateRoleAdministrador="INSERT INTO role (id, role) VALUES (1, 'ADMINISTRADOR')";
			jdbcTemplate.update(sqlCreateRoleAdministrador);
			
			String sqlCreateRoleSecretario="INSERT INTO role (id, role) VALUES (2, 'SECRETARIO')";
			jdbcTemplate.update(sqlCreateRoleSecretario);
			
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin")); // Hash the password

			
			Role role = new Role();
			role.setId(1L);
			role.setRole("ADMINISTRADOR");
			admin.setRoles(Set.of(role));
			usuarioRepository.save(admin);
			
			System.out.println("Admin user created.");
		} else {
			System.out.println("Admin user already exists.");
		}
	}
}
