package Modelagem;

import java.util.ArrayList;

public class Marca {

	private String nome;
	private ArrayList<Modelo> modelos;

	public Marca(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Marca = " + nome ;
	}
	
}
