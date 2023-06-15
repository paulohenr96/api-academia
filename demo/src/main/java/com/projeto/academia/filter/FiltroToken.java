package com.projeto.academia.filter;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projeto.academia.exception.TratarErros;
import com.projeto.academia.security.Constantes;
import com.projeto.academia.security.CriadorToken;
import com.projeto.academia.security.ObjectToken;

public class FiltroToken extends OncePerRequestFilter{

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token = request.getHeader(Constantes.HEADER_AUTHORIZATION);
			
			if (token!=null) {
				String[] split = token.split((" "));
				
				String chave = split[1];
				ObjectToken objetoToken= CriadorToken.criarToken(chave);
				
				UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(objetoToken.getSubject(),null,objetoToken.getRoles()
						.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()));
			
				SecurityContextHolder.getContext().setAuthentication(user);
			}else {
				SecurityContextHolder.clearContext();
			}		
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			TratarErros tratarErros = new TratarErros(HttpStatus.NOT_ACCEPTABLE,"Problema no Token => "+e.getMessage());
            StringBuilder sb=new StringBuilder();
            
            sb.append("{\"status\":\"");
			sb.append(tratarErros.getStatus());
			sb.append("\",");
            sb.append("\"erro\":\"");
        	sb.append(tratarErros.getErros());
			sb.append("\"}");
			response.getWriter().write(sb.toString());	
            response.getWriter().flush();
            e.printStackTrace();
		
		}
		

	}

}
