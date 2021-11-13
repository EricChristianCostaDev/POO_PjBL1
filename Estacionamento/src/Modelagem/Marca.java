package Modelagem;

import java.util.ArrayList;
import Modelagem.Modelo;

public class Marca {

	private String nome;
	private static ArrayList<Modelo> modelos;
	private Modelo teste ;

	private void testando() {
		this.teste  = new Modelo ("teste");
		Marca.modelos.add(teste);
	}
	

	public Marca(String nome) {
		this.nome = nome;
		// testando();
		// ArrayList<Modelo> nomeModelo = new ArrayList<Modelo>() 
	}

	public void addModelos(Modelo modelo) {
		System.out.println( "dentro do addmodelos " + modelo.getNome());
		modelos.add(modelo);
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

	// public void setModelos(ArrayList<Modelo> modelos) {
	// 	this.modelos = modelos;
	// }

	@Override
	public String toString() {
		return "Marca = " + nome ;
	}
	
}
