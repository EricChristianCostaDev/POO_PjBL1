package Principal;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import Modelagem.Carro;
import Modelagem.Marca;
import Modelagem.Modelo;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.List;

public class Principal {
	
	// atributos static sao atributos de classe
	private static Carro[] vagas = new Carro[100]; // o estacionamento tem 100 vagas numeradas de 0..99
	private static ArrayList<Marca> marcas = new ArrayList<Marca>();
	private static ArrayList<Carro> historico = new ArrayList<Carro>();
	private static float valorTotal = 0;
	
	// eventualmente outros atributos static
	private static Scanner scanner = new Scanner(System.in);
		
	public static void main(String[] args) {
        // Adicionando marcas HardCoded
		Marca Jeep = new Marca("Jeep");
		Marca Ford = new Marca("Ford");
    
		Marca Mustang = new Marca("Mustang");
		Modelo Teste = new Modelo("Teste");
		Modelo Teste2 = new Modelo("Teste2");
    Modelo Ranger = new Modelo("Ranger");
    
		Ford.addModelos(Teste);
    Ford.addModelos(Ranger);
		Jeep.addModelos(Teste2);
		
		marcas.add(Mustang);
		marcas.add(Jeep);
		marcas.add(Ford);

		System.out.println("Mustang " + marcas.get(0).getModelos().get(0).getNome() );
		System.out.println("Jeep " + marcas.get(1).getModelos().get(0).getNome() );
		System.out.println("Ford " + marcas.get(2).getModelos().get(0).getNome() );

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

		// Carro[] vagas = new Carro[100];

		Modelo Teste = new Modelo("Teste");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String str = "2021-11-13 19:52:00";
		LocalDateTime dateTime = LocalDateTime.parse(str, dtf);

		vagas[0] = new Carro(Teste, "123456", dateTime);

		
		for(int i=0; i<vagas.length; i++) { 
			System.out.println( (i + 1) + " - " + vagas[i]);
		}
			
		System.out.println("\n==> Escolha uma vaga\n");

		int opcaoVaga = scanner.nextInt();

		if(opcaoVaga < 0 || opcaoVaga > vagas.length){
			System.out.println("\nOpcao invalida! Escolha um numero entre 0 e 100\n");
		}else{
			System.out.println("O carro que está ocupando a vaga escolhida é: " + vagas[opcaoVaga]);
			System.out.println("\n Digite 1 para confirmar ou 2 para escolher outra vaga\n");
			int confirmarSaida = scanner.nextInt();
			if(confirmarSaida == 1){
				SimpleDateFormat sdf
				= new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");

				
				System.out.println("Confirmei a liberação da vaga \n");
				LocalDateTime horarioEntrada = vagas[opcaoVaga].getEntrada();
				LocalDateTime horarioSaida = LocalDateTime.now();
				
				System.out.println("horarioEntrada " + horarioEntrada);
				System.out.println("horarioSaida " + horarioSaida);

				long diff = ChronoUnit.SECONDS.between(horarioEntrada, horarioSaida);

				if(diff/3600 >= 1){
					long tempoCalcular = diff - 3600;
					long valorAdicional = Math.round(tempoCalcular/900);
					System.out.println("tempoCalcular " + tempoCalcular);
					System.out.println("valorAdicional " + valorAdicional);

					preco = 10 + valorAdicional*2;
				
					vagas[opcaoVaga].setValor(preco);
					System.out.println("valor R$ " + vagas[opcaoVaga].getValor());
					
				}else{
					preco = 10;
					vagas[opcaoVaga].setValor(preco);
					System.out.println("valor R$ " + vagas[opcaoVaga].getValor());
				}


				System.out.println("diferença: " + diff);

				vagas[opcaoVaga].setSaida(horarioSaida);

				historico.add(vagas[opcaoVaga]);
				System.out.println("historico atual " + historico.get(0));
				System.out.println("historico atual valor " + historico.get(0).getValor());
				System.out.println("historico atual data saida " + historico.get(0).getSaida());
				vagas[opcaoVaga] = null;
				System.out.println("vagas atual " + vagas[opcaoVaga]);
			}
		}

		
		
		// for(int i=0; i<vagas.length; i++) { 
		// 	System.out.println( (i + 1) + " - " + vagas[i]);
		// }

		// logica para calcular preco do estacionamento e coloca-lo no historico
		return preco;
	}
	
	private static void listarMarcas(){
		System.out.println("0 - Cadastrar nova marca");
		
		for(int i=0; i<marcas.size(); i++) { 
			System.out.println( (i + 1) + " - " + marcas.get(i).getNome());
		}

		}

	private static Marca  findMarcaByModelo(Modelo modelo) {
			for(int j=0; j < marcas.size(); j++){
				for(int i=0; i < marcas.get(j).getModelos().size(); i++){
					System.out.println("\n Marca " + marcas.get(j) + " Modelo " + marcas.get(j).getModelos().get(i).getNome() );
					if(marcas.get(j).getModelos().get(i).getNome().equalsIgnoreCase(modelo.getNome())){
						System.out.println("Entei no if !!!");
						return marcas.get(j); 
					}
				}
	
			}
			return marcas.get(0);
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
	
	


	private static void gerarRelatorio(int mes,int ano,int dia){
		
		String dataFormatada =  Integer.toString(ano) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia) + " 00:00:00";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime dataEscolhida = LocalDateTime.parse(dataFormatada, dtf);
		System.out.println("\n a data formatada " + dataEscolhida);

		Modelo Teste = new Modelo("Testando");
		DateTimeFormatter dataformatteste = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		
		

		String str1 = "2021-11-13 19:52:00";
		String str2 = "2021-11-13 19:20:00";
		String str3 = "2021-11-13 19:42:00";

		LocalDateTime datetime1 = LocalDateTime.parse(str1, dtf);
		LocalDateTime datetime2 = LocalDateTime.parse(str2, dtf);
		LocalDateTime datetime3 = LocalDateTime.parse(str3, dtf);

		Carro teste1 = new Carro(Teste, "123456", datetime1);
		Carro teste2 = new Carro(Teste, "123456", datetime2);
		Carro teste3 = new Carro(Teste, "123456", datetime3);
		LocalDateTime horarioSaida = LocalDateTime.now();
		
		teste1.setValor((float) 10);
		teste1.setSaida(horarioSaida);

		teste2.setValor((float) 12);
		teste2.setSaida(horarioSaida);
		
		teste3.setValor((float) 14);
		teste3.setSaida(horarioSaida);

		historico.add(teste1);
		historico.add(teste2);
		historico.add(teste3);

		System.out.println("\n Antes ");
		listarMarcas();

		Marca Ferrari = new Marca("Ferrari");
		Ferrari.addModelos(Teste);
		System.out.println("\n Depois ");
		listarMarcas();

		System.out.println("\n return do findMarcaByModelo " + findMarcaByModelo(Teste));
	

		System.out.println("\n historico sem o  filtro " + historico);

		//|| e.getSaida().format(dataformatteste) == dataEscolhida.format(dataformatteste)

		Carro[] arr = historico.stream().filter(e ->{
			if ((e.getEntrada().toLocalDate().compareTo(dataEscolhida.toLocalDate())) == 0){
				return true;
			}else{
				return false;
			}
		} ).toArray(Carro[]::new);

		
		
		List<Carro> list1 = new ArrayList<Carro>();
		Collections.addAll(list1, arr);

		System.out.println("\n historico com o  filtro " + list1);

		Collections.sort(list1, 
                        (o1, o2) -> o1.getEntrada().compareTo(o2.getEntrada()));


		
						
		list1.forEach((n) -> {
			
			
			valorTotal = valorTotal + n.getValor();
			// System.out.println()
		});

		System.out.println("valor total" + valorTotal);

		list1.forEach((n) -> {
			System.out.println("\n  Placa: " + " Modelo: " + n.getModelo() + 
								  " Marca " + n.getModelo() + " Horario de entrada: " + n.getEntrada() + 
								  " Horario de saida " + n.getSaida() + " valor " + n.getValor() );
		});
		System.out.println("\n\n Valor total:  R$ " + valorTotal );
		
	}

	private static void relatorioGerencial(){
		System.out.println("\t Digite o ano ");
		int ano = scanner.nextInt();
		System.out.println("\t Digite o mes (numero) ");
		int mes = scanner.nextInt();
		int dia = 0;
		
		if(mes>0 && mes<=12){
			System.out.println("\t Digite o dia ");
				dia = scanner.nextInt();

				if(mes == 4 || mes == 6 || mes == 9 || mes == 11 ){
					if (dia >=1 && dia<=30){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Digite um valor entre 1 e 30  ");
					}
	
				
				}else if (mes  == 2){
					if (dia >=1 && dia<29){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Digite um valor entre 1 e 29  ");
					}
	
	
				}else {
					if (dia >=1 && dia<31){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Digite um valor entre 1 e 31");
					}
	
				}
		}else {
			System.out.println("\t Digite um valor entre 1(janeiro) e 12(dezembro)  ");
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
    System.out.println("6. Mostrar relatório gerencial consolidado");
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
                
        case 6:
				relatorioGerencial();
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