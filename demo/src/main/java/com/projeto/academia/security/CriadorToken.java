package com.projeto.academia.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CriadorToken {

	
	public static String criarToken(String prefixo,String key,ObjectToken objeto) {
		ObjectToken objectToken=new ObjectToken();
		
		JwtBuilder setExpiration = Jwts.builder().setExpiration(objeto.getDataFinal());
		JwtBuilder setIssuedAt = setExpiration.setIssuedAt(objeto.getDataInicial());
		
		JwtBuilder setSubject = setIssuedAt.setSubject(objeto.getSubject());
		
		
		JwtBuilder claim = setSubject.claim(Constantes.ROLES_AUTHORITIES, objeto.getRoles());
		
		String compact = claim.signWith(SignatureAlgorithm.HS256, key).compact();
		
		
		
		return prefixo.concat(" ").concat(compact);
	}

	public static ObjectToken criarToken(String chave) {
		// TODO Auto-generated method stub
		
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(Constantes.PALAVRA_SECRETA).parseClaimsJws(chave);
		
		Claims body = parseClaimsJws.getBody();
		
		List<String> roles =(List<String>) body.get(Constantes.ROLES_AUTHORITIES);
		
		ObjectToken objectToken=new ObjectToken();
		objectToken.setRoles(roles);
		objectToken.setSubject(body.getSubject());
		
		return objectToken;
	}
}
