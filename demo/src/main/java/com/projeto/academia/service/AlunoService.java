package com.projeto.academia.service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.dto.PaginacaoDTO;
import com.projeto.academia.exception.MensalidadePagaException;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.mapper.MapperMensalidade;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.model.Mensalidade;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.AlunoRepository;
import com.projeto.academia.repository.MensalidadeRepository;

@Controller
public class AlunoService {

	private final AlunoRepository alunoRepository;
	private final MensalidadeRepository mensalidadeRepository;

	public AlunoService(AlunoRepository alunoRepository, MensalidadeRepository mensalidadeRepository) {
		// TODO Auto-generated constructor stub
		this.alunoRepository = alunoRepository;
		this.mensalidadeRepository = mensalidadeRepository;
	}

	public void salvarAluno(Aluno aluno) {
		// TODO Auto-generated method stub

		alunoRepository.save(aluno);
	}

	public Page<Aluno> findAllAluno(int page,int size) {
		// TODO Auto-generated method stub
		PageRequest of = PageRequest.of(page, size);
	
		return 	alunoRepository.findAll(of) ;
	}

	public Aluno findAlunoById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

	}

	

	public void pagarMensalidade(Long id, MensalidadeDTO mensalidadeReq) {
		// TODO Auto-generated method stub

		boolean anyMatch = mensalidadeRepository.mensalidadesAluno(id).stream()
				.anyMatch(e -> e.getDataPagamento().getMonth() == mensalidadeReq.getData().getMonth());
		if (anyMatch)
			throw new MensalidadePagaException(mensalidadeReq.getData().getMonth() + 1);
		mensalidadeRepository.save(alunoRepository.findById(id).map(a -> new Mensalidade(mensalidadeReq.getData(), a))
				.orElseThrow(() -> new UserNotFoundException(id)));

	}

	public List<MensalidadeDTO> findAllMensalidadeAluno(Long id) {
		// TODO Auto-generated method stub
		return mensalidadeRepository.mensalidadesAluno(id).stream()
				.map(MapperMensalidade::toDTO).collect(Collectors.toList());
	}

	public String deleteMensalidadeAluno(Long idMensalidade) {
		// TODO Auto-generated method stub
		if (!mensalidadeRepository.existsById(idMensalidade)) {
			throw new RuntimeException("Esta mensalidade nao existe.");
		}
		mensalidadeRepository.deleteById(idMensalidade);
		return "{}";
	}

	public String removerAluno(Long id) {
		// TODO Auto-generated method stub
		boolean exist = alunoRepository.existsById(id);
		if (!exist ) {
			throw new UserNotFoundException(id);
		}
		mensalidadeRepository.deletarMensalidadesAluno(id);
		alunoRepository.deleteById(id);

		return "{}";
	}

	
	@Transactional
	public String atualizarAluno(Aluno alunoNovo) {
		// TODO Auto-generated method stub
		
		alunoRepository.findById(alunoNovo.getId())
						.orElseThrow(()->new UserNotFoundException(alunoNovo.getId()));
		alunoRepository.save(alunoNovo);
		return "ok";
	}

	public Page<Aluno> findAllAlunosDevedores(int mes, int page, int size) {
		// TODO Auto-generated method stub
		Page<Aluno> findAllAlunosDevedores = alunoRepository.findAllAlunosDevedores(mes,PageRequest.of(page, size));
		
		return findAllAlunosDevedores;
	}
}
