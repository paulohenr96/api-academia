package com.projeto.academia.mapper;

import com.projeto.academia.dto.AlunoDTO;
import com.projeto.academia.model.Aluno;

public class MapperAluno {

	
	
	public static AlunoDTO toDTO(Aluno aluno) {
		
		AlunoDTO alunoDTO=new AlunoDTO();
		
		if (aluno.getName()!=null) {
			alunoDTO.setName(aluno.getName());
		}
		if (aluno.getId()!=null) {
			alunoDTO.setId(aluno.getId());

		}
		if (aluno.getSecondName()!=null) {
			alunoDTO.setSecondName(aluno.getSecondName());

		}
		if (aluno.getDataDeMatricula()!=null) {
			alunoDTO.setDataDeMatricula(aluno.getDataDeMatricula());

		}
		if (aluno.getDataDeNascimento()!=null) {
			alunoDTO.setDataDeNascimento(aluno.getDataDeNascimento());

		}
		
		return alunoDTO;
	}
	
	
	public static Aluno toEntity(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        if (alunoDTO.getName() != null) {
            aluno.setName(alunoDTO.getName());
        }
        if (alunoDTO.getId() != null) {
            aluno.setId(alunoDTO.getId());
        }
        if (alunoDTO.getSecondName() != null) {
            aluno.setSecondName(alunoDTO.getSecondName());
        }
        if (alunoDTO.getDataDeMatricula() != null) {
            aluno.setDataDeMatricula(alunoDTO.getDataDeMatricula());
        }
        if (alunoDTO.getDataDeNascimento() != null) {
            aluno.setDataDeNascimento(alunoDTO.getDataDeNascimento());
        }

        return aluno;
    }
}
