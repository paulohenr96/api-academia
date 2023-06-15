package com.projeto.academia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.projeto.academia.dto.LoginDTO;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.repository.AlunoRepository;
import com.projeto.academia.security.Sessao;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class AlunoTest {
	
	@Mock
	private  RestTemplate restTemplate;
	
	@Mock 
    private  AlunoRepository alunoRepository;
	private final String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODY3OTYxMDcsImlhdCI6MTY4NjcwOTcwNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTklTVFJBRE9SIl19.y96ySmSuEXsCVL8Je2PtYmiYWT2SykirBWwhMN2_BLY";
	
	
	@Test
	public void testDevedores() {
		int mes = 8;

		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", token);

	    HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		
		String url = "aluno/devedores/" + mes;
		List<Aluno> simulatedLista = Arrays.asList(new Aluno(), new Aluno());
        Mockito.when(restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Aluno>>() {}
        )).thenReturn(ResponseEntity.ok(simulatedLista));

        List<Aluno> lista = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Aluno>>() {}
        ).getBody();		
//	    HttpRequest request = new HttpGet( "https://api.github.com/users/" + name );

	    assertEquals(alunoRepository.findAll().size(),lista.size());
		
	}
	
	@Test
	public void login() {
		 LoginDTO loginDTO = new LoginDTO();
	        loginDTO.setUsername("paulo");
	        loginDTO.setPassword("123");

	        HttpEntity<?> requestEntity = new HttpEntity<>(loginDTO);

	        String url = "/login";

	        // Simulate the response from restTemplate.postForEntity(...)
	        Sessao simulatedBody = new Sessao();
	        Mockito.when(restTemplate.postForEntity(url, requestEntity, Sessao.class))
	                .thenReturn(ResponseEntity.ok(simulatedBody));

	        Sessao body = restTemplate.postForEntity(url, requestEntity, Sessao.class).getBody();

	        assertNotNull(body.getToken());
	}
}
