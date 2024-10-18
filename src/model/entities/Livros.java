package model.entities;

public class Livros {

	private String name;
	private String author;
	private Double price;
	
	public Livros(String name, String author, Double price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public Double getPrice() {
		return price;
	}
	
	public String toString() {
		return getName() + " - " + getAuthor() + " - US$" + String.format("%.2f", getPrice());
	}
	
}
