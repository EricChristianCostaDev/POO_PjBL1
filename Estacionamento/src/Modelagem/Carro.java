package Modelagem;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class Carro {

	private Modelo modelo;
	private String placa;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private float valor;
	
	public Carro(Modelo modelo, String placa, LocalDateTime entrada) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.entrada = entrada;
	}
	
	public Modelo getModelo() {
		return modelo;
	}


	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getValor() {
		return valor;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}


	@Override
	public String toString() {
		return "Carro [modelo=" + modelo + ", placa=" + placa + ", entrada=" + entrada + "]";
	}
	
}
