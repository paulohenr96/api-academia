package com.projeto.academia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.exception.MensalidadePagaException;
import com.projeto.academia.exception.UserNotFoundException;
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

	public List<Aluno> findAllAluno() {
		// TODO Auto-generated method stub
		return alunoRepository.findAll();
	}

	public Aluno findAlunoById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

	}

	public void update(Usuario novo, Long id) {
		// TODO Auto-generated method stub
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

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
				.map(m -> new MensalidadeDTO(m.getId(), m.getDataPagamento())).collect(Collectors.toList());
	}

	public String deleteMensalidadeAluno(Long idMensalidade) {
		// TODO Auto-generated method stub
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

	public List<Aluno> findAllAlunosDevedores(int mes) {
		// TODO Auto-generated method stub
		
		return alunoRepository.findAllAlunosDevedores(mes);
	}
}
