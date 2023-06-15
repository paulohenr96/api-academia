package com.projeto.academia.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.projeto.academia.model.Aluno;

public class PaginacaoDTO<T> {

	
	
	
	private List<T> content;
	private int number;
	private int size;
	private Long totalElements;
	private int totalPages;
	public PaginacaoDTO(List<T> content, int number, int size, Long totalElements, int totalPages) {
		super();
		this.content = content;
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
	
	public void setContent(List<T> content) {
		this.content = content;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	

	public List<T> getContent() {
		return content;
	}

	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	
	

}
