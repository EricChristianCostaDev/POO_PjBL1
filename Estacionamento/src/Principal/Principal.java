package Principal;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import java.util.ArrayList;

import Modelagem.Carro;
import Modelagem.Marca;
import Modelagem.Modelo;

public class Principal {
	
	// atributos static sao atributos de classe
	private static Carro[] vagas = new Carro[100]; // o estacionamento tem 100 vagas numeradas de 0..99
	private static ArrayList<Marca> marcas = new ArrayList<Marca>();
	private static ArrayList<Carro> historico = new ArrayList<Carro>();
	
	// eventualmente outros atributos static
	private static Scanner scanner = new Scanner(System.in);
		
	public static void main(String[] args) {
        	// Adicionando marcas HardCoded
			marcas.add(new Marca("Jeep") );
			menu();
        
    }
	
	private static void entradaCarro() {
		// Listar as marcas
		System.out.println("\n==> Escolha a marca do carro\n");
		listar("Marcas");
		// Opcoes para marcas
		opcaoMarca;
		opcaoMarca = marcas.get(scanner.nextInt()-1);

		// Adicionar uma marca se não houver

		// Listar os modelos
		// Adicionar um modelo se não houver

		// Receber a Placa do carro

		// Cadastrar o horário de entrada
	}
		// criar o carro e cadastra-lo no vetor na posicao correta
	
	
	private static float saidaCarro() {
		System.out.println("Voce entrou no metodo saidaCarro().");
		float preco = 0;
		// logica para calcular preco do estacionamento e coloca-lo no historico
		return preco;
	}
	
	private static void listar(String objeto){
		
		if(objeto == "Marcas"){
			for(int i=0; i<marcas.size(); i++) { 
				System.out.println( (i + 1) + " - " + marcas.get(i).getNome());
			}
		}

		if(objeto == "Modelo"){

			}
		}
		
	public static void menu(){
		int opcao;

		do{
		System.out.println("\tMenu estacionamento");
	    System.out.println("0. Fim");
	    System.out.println("1. Entrada");
	    System.out.println("2. Saida");
        opcao = scanner.nextInt();
            
            switch(opcao){
				case 0:
                System.out.println("Programa encerrado");	   
				scanner.close();

				break;

            	case 1:
				entradaCarro();
				
				break;
                
            	case 2:
                
				saidaCarro();
				break;
                
            default:
                System.out.println("Opcao invalida.");
            }
        } while(opcao != 0);

	    }
	// outros m�todos static conforme especificacao do trabalho e necessidades de implementacao
}