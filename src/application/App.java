package application;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Estoque;
import model.entities.Livros;
import model.exceptions.DbException;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
		Estoque estoque = new Estoque();
		
		for(int i = 0; i < 3; i++) {
			System.out.println("CADASTRO DE LIVRO #" + (i + 1));
			System.out.print("Título: ");
			String titulo = sc.nextLine();
			System.out.print("Author: ");
			String author = sc.nextLine();
			System.out.print("Price: ");
			Double price = sc.nextDouble();
			
			Livros livro = new Livros(titulo, author, price);
			
			System.out.print("Quantity in stock: ");
			Integer quantity = sc.nextInt();
			sc.nextLine();
			
			estoque.addEstoque(livro, quantity);
			
		}
		
		int x = 1;
		
		do {
			System.out.println("1 - Listar livros");
			System.out.println("2 - Remover livro");
			System.out.println("3 - Filtrar livro");
			System.out.println("4 - Sair");
			x = sc.nextInt();
			sc.nextLine();
			switch(x) {
				case 1:
					estoque.listarEstoque();
					break;
				case 2:
					System.out.print("Titulo do livro: ");
					String tituloRemover = sc.nextLine();
					System.out.print("Quantidade a remover: ");
					Integer quantityRemove = sc.nextInt();
					sc.nextLine();
					try {
						estoque.removeEstoque(tituloRemover, quantityRemove);
					}
					catch(DbException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;
				case 3:
					System.out.print("Remover por qual critério (autor - a | preço - p): ");
					char ch = sc.next().toLowerCase().charAt(0);
					sc.nextLine();
					switch(ch) {
						case 'a':
							System.out.print("Author: ");
							String authorFilter = sc.nextLine();
							List<Livros> livrosFiltradosAuthor = estoque.filtro(p -> p.getAuthor().equals(authorFilter));
							for(Livros lv : livrosFiltradosAuthor) {
								System.out.println(lv);
							}
							
							break;
						case 'p':
							System.out.print("Price: ");
							Double priceFilter = sc.nextDouble();
							List<Livros> livrosFiltradosPreco = estoque.filtro(p -> p.getPrice() <= priceFilter);
							for(Livros lv : livrosFiltradosPreco) {
								System.out.println(lv);
							}
							
							break;
					}
					break;
				case 4:
					System.out.println("Programa encerrado...");
					System.exit(0);
					break;
				default:
					System.out.println("Opção invalida, tente novamente");
					break;
			}
			
		} while(x != 4);
		
	}
	
}
