package Principal;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import java.time.LocalDateTime;
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
		Marca Jeep = new Marca("Jeep");
		Marca Ford = new Marca("Ford");
		Modelo Ranger = new Modelo("Ranger");
		Ford.addModelos(Ranger);

		marcas.add(Jeep);
		marcas.add(Ford);

		menu();
        
    }
	
	private static void entradaCarro() {

			Marca marcaEscolhida = escolherMarca();
			System.out.println("\n==> Escolha o modelo do carro\n");
			// Listar os modelos
			listarModelos(marcaEscolhida);
			int opcaoModelo = scanner.nextInt();

			if(opcaoModelo < 0 || opcaoModelo > marcaEscolhida.getModelos().size()){
				System.out.println("\nOpcao invalida.\n");
			}
			else if(opcaoModelo == 0){
				// Adicionar um modelo se não houver
				Modelo modeloEscolhido = cadastrarModelo(marcaEscolhida);
				estacionarCarro(modeloEscolhido);
			}
			else{
				Modelo modeloEscolhido = marcaEscolhida.getModelos().get(opcaoModelo - 1);

				estacionarCarro(modeloEscolhido);
			}

		

	}
	
	private static float saidaCarro() {
		System.out.println("Voce entrou no metodo saidaCarro().");
		float preco = 0;
		// logica para calcular preco do estacionamento e coloca-lo no historico
		return preco;
	}
	
	private static void listarMarcas(){
		System.out.println("0 - Cadastrar nova marca");
		
		for(int i=0; i<marcas.size(); i++) { 
			System.out.println( (i + 1) + " - " + marcas.get(i).getNome());
		}

		}

	private static void mostrarOcupacao(){
		for(int i=0; i < 100; i++){
			if(vagas[i] == null){
				System.out.println("Vaga Vazia");
			}
			else{
				System.out.println("Ocupada pelo carro -> " + vagas[i]);
			}
		}
		System.out.println();

	}

	private static void listarModelos(Marca marcaEscolhida){
		System.out.println("0 - Cadastrar novo modelo");

		for(int i=0; i < marcaEscolhida.getModelos().size(); i++) { 
			System.out.println( (i + 1) + " - " + marcaEscolhida.getModelos().get(i).getNome());
		}

		}

	private static void menu(){

		int opcao;

		do{
		System.out.println("\tMenu estacionamento");
	    System.out.println("0. Finalizar o programa");
	    System.out.println("1. Cadastrar entrada de um carro");
	    System.out.println("2. Efetuar a saida de um carro");
		System.out.println("3. Mostrar ocupacao do estacionamento");
		System.out.println("4. Cadastrar Marca de carro");
		System.out.println("5. Cadastrar Modelo de carro");
		System.out.print("Opcao: ");
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

				case 3:
                
				mostrarOcupacao();
				break;

				case 4:
                
				cadastrarMarca();
				break;

				case 5:
				Marca marcaEscolhida = escolherMarca();
				cadastrarModelo(marcaEscolhida);
				break;
                
            default:
                System.out.println("Opcao invalida.");
            }
        } while(opcao != 0);

	    }
	
	private static Marca cadastrarMarca() {
			System.out.println("\n==> Cadastro de marca de carro\n");
			String nome;
			
			scanner.nextLine();
			System.out.print("   Marca a ser cadastrada: ");
			nome = scanner.nextLine();

			for(int j=0; j < marcas.size(); j++){
				if(marcas.get(j).getNome().equalsIgnoreCase(nome)){
					Marca marca = marcas.get(j);
					System.out.println("   \nMarca ja registrada!\n");
					return marca;
				}
			}

			Marca marca = new Marca(nome);
			marcas.add(marca);
			return marca;
		}
	
	private static Modelo cadastrarModelo(Marca marcaEscolhida) {
			System.out.println("\n==> Cadastro do modelo da marca\n");
			String nome;
			
			scanner.nextLine();
			System.out.print("   Modelo a ser cadastrado: ");
			nome = scanner.nextLine();
			System.out.print("\n");


			for(int j=0; j < marcaEscolhida.getModelos().size(); j++){

				if(marcaEscolhida.getModelos().get(j).getNome().equalsIgnoreCase(nome)){
					Modelo modelo = marcaEscolhida.getModelos().get(j);
					System.out.println("   \nModelo ja registrado!\n");
					return modelo;
				}
			}


			Modelo modelo = new Modelo(nome);
			marcaEscolhida.addModelos(modelo);

			return modelo;
		}
		
	private static void estacionarCarro(Modelo modeloEscolhido){
				// Receber a Placa do carro com horario de entrada
				System.out.println("\n==> Digite a placa do carro\n");
				System.out.print("   Placa: ");
				String placa = scanner.next();
				Carro carro = new Carro(modeloEscolhido, placa, LocalDateTime.now());

				// Alocar carro na vaga correta
			for(int i=0; i < 100; i++ ){
				if(vagas[i] == null){
					vagas[i] = carro;
					System.out.println("\nCarro estacionado na vaga " + (i+1) +"\n");
					return;
				}
				else{
					System.out.println("Nao ha vaga disponivel.");
				}
			}

	
			}
	
	private static Marca escolherMarca(){
		System.out.println("\n==> Escolha a marca do carro\n");
		listarMarcas();

		int opcaoMarca = scanner.nextInt();
		if(opcaoMarca < 0 || opcaoMarca > marcas.size()){
			System.out.println("\nOpcao invalida.\n");
		}
		else if(opcaoMarca == 0){
			// Adicionar uma marca se não houver
			
			return cadastrarMarca();
		}
		else{
			Marca marcaEscolhida = marcas.get(opcaoMarca - 1);
			return marcaEscolhida;
		}
		return marcas.get(opcaoMarca - 1);
		}
		// outros m�todos static conforme especificacao do trabalho e necessidades de implementacao
}