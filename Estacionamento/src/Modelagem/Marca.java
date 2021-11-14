package Modelagem;

import java.util.ArrayList;
import Modelagem.Modelo;

public class Marca {

	private String nome;
	private static ArrayList<Modelo> modelos;
	

	public Marca(String nome) {
		this.nome = nome;
		modelos = new ArrayList<Modelo>();
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Modelo> getModelos() {
		return modelos;
	}

	public void addModelos(Modelo modelo) {
		modelos.add(modelo);
	}

	@Override
	public String toString() {
		return "Marca = " + nome ;
	}
	
}
