package model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import model.exceptions.DbException;

public class Estoque {

	private Livros livro;
	private Integer quantity;
	
	Map<Livros, Integer> map;
	
	public Estoque() {
		map = new HashMap<>();
	}
	
	public void addEstoque(Livros livro, Integer quantity) {
		map.put(livro, quantity);
	}
	
	public void removeEstoque(String titulo, Integer quantity) {
		
		Livros livroEncontrado = null;
		
		for(Livros livro : map.keySet()) {
			if(livro.getName().equals(titulo)) {
				livroEncontrado = livro;
				break;
			}
		}
		
		int quantidadeAtual = map.get(livroEncontrado);
		
		if(livroEncontrado != null) {
			map.remove(livroEncontrado, quantity);
		}
		else {
			throw new DbException("Livro não encontrado no estoque.");
		}
		
		if(quantidadeAtual < quantity) {
			throw new DbException("Quantidade insuficiente no estoque");
		}
		else if(quantidadeAtual == quantity) {
			map.remove(livroEncontrado, quantity);
		}
		else if(quantidadeAtual > quantity) {
			map.put(livroEncontrado, quantidadeAtual - quantity);
		}
	}
	
	public Livros getLivro() {
		return livro;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Map<Livros, Integer> getMap() {
		return map;
	}

	public List<Livros> filtro(Predicate<Livros> filtro) {
		List<Livros> list = new ArrayList<>();
		for(Livros livro : map.keySet()) {
			if(filtro.test(livro)) {
				list.add(livro);				
			}
		}
		return list;
	}
	
	public void listarEstoque() {
		for(Livros livro: map.keySet()) {
			System.out.println(livro + " - " + map.get(livro) + " em estoque");
		}
	}
	
	
}
