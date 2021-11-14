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


		String str1 = "2021-11-13 19:52:00";
		String str2 = "2021-11-13 19:20:00";
		String str3 = "2021-11-13 19:42:00";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datetime1 = LocalDateTime.parse(str1, dtf);
		LocalDateTime datetime2 = LocalDateTime.parse(str2, dtf);
		LocalDateTime datetime3 = LocalDateTime.parse(str3, dtf);

		Carro carro1 = new Carro(Teste, "123456", datetime1);
		Carro carro2 = new Carro(Teste, "123456", datetime2);
		Carro carro3 = new Carro(Teste, "123456", datetime3);
		
		LocalDateTime horarioSaida = LocalDateTime.now();
		
		carro1.setValor((float) 10);
		carro2.setValor((float) 12);
		carro3.setValor((float) 14);

		vagas[0] = carro1;
		vagas[1] = carro2;
		vagas[2] = carro3;


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
		float preco = 0;

		// Carro[] vagas = new Carro[100];

		Modelo Teste = new Modelo("Teste");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String str = "2021-11-13 19:52:00";
		LocalDateTime dateTime = LocalDateTime.parse(str, dtf);

		mostrarOcupacao();
			
		System.out.println("\n==> Escolha uma vaga\n");

		int opcaoVaga = scanner.nextInt() - 1;

		if(opcaoVaga < 0 || opcaoVaga > vagas.length){
			System.out.println("\nOpcao inválida! Escolha um numero entre 0 e 100\n");
		}else{
			System.out.println("O carro que está ocupando a vaga escolhida é: " + vagas[opcaoVaga]);
			System.out.println("\n Digite 1 para confirmar ou 2 para escolher outra vaga\n");
			int confirmarSaida = scanner.nextInt();
			if(confirmarSaida == 1){
				SimpleDateFormat sdf
				= new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");

				LocalDateTime horarioEntrada = vagas[opcaoVaga].getEntrada();
				LocalDateTime horarioSaida = LocalDateTime.now();

				long diff = ChronoUnit.SECONDS.between(horarioEntrada, horarioSaida);

				if(diff/3600 >= 1){
					long tempoCalcular = diff - 3600;
					long valorAdicional = Math.round(tempoCalcular/900);

					preco = 10 + valorAdicional*2;
				
					vagas[opcaoVaga].setValor(preco);
					// System.out.println("valor R$ " + vagas[opcaoVaga].getValor());
					
				}else{
					preco = 10;
					vagas[opcaoVaga].setValor(preco);
					// System.out.println("valor R$ " + vagas[opcaoVaga].getValor());
				}
				System.out.println("diferença: " + diff);
				vagas[opcaoVaga].setSaida(horarioSaida);
				historico.add(vagas[opcaoVaga]);
				vagas[opcaoVaga] = null;
			}
		}
		return preco;
	}
	
	private static void listarMarcas(){
		System.out.println("0 - Cadastrar nova marca");
		
		for(int i=0; i<marcas.size(); i++) { 
			System.out.println( (i + 1) + " - " + marcas.get(i).getNome());
		}

		}

	private static String  findMarcaByModelo(Modelo modelo) {
			for(int j=0; j < marcas.size(); j++){
				for(int i=0; i < marcas.get(j).getModelos().size(); i++){
					
					if(marcas.get(j).getModelos().get(i).getNome().equalsIgnoreCase(modelo.getNome())){
						
						return marcas.get(j).getNome(); 
					}
				}
			}
			return "";
	}
	
	private static void mostrarOcupacao(){
		for(int i=0; i < 100; i++){
			int pos = i + 1;
			if(vagas[i] == null){
				System.out.println("N°" + pos + " Vaga Vazia");
			}
			else{
				System.out.println("N°" + pos + " Vaga ocupada pelo carro -> " + vagas[i]);
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

		Modelo Teste = new Modelo("Testando");
		DateTimeFormatter dataformatteste = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	
	

		//|| e.getSaida().format(dataformatteste) == dataEscolhida.format(dataformatteste)

		Carro[] arr = historico.stream().filter(e ->{
			if ((e.getEntrada().toLocalDate().compareTo(dataEscolhida.toLocalDate())) == 0 || 
			(e.getSaida().toLocalDate().compareTo(dataEscolhida.toLocalDate())) == 0
			){
				return true;
			}else{
				return false;
			}
		} ).toArray(Carro[]::new);
		
		List<Carro> list1 = new ArrayList<Carro>();

		Collections.addAll(list1, arr);

		Collections.sort(list1, (o1, o2) -> o1.getEntrada().compareTo(o2.getEntrada()));
						
		list1.forEach((n) -> {valorTotal = valorTotal + n.getValor();});

		list1.forEach((n) -> {
			System.out.println("\n  Placa: " + n.getPlaca() + " | Modelo: " + n.getModelo() + 
								  " | Marca: " + findMarcaByModelo(n.getModelo()) + " | Horario de entrada: " + n.getEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + 
								  " | Horario de saida " + n.getSaida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " | valor R$ " + n.getValor() );
		});
		System.out.println("\n  Valor total do dia: R$ " + valorTotal );
		System.out.println();
	}

	private static void relatorioGerencial(){

		scanner.nextLine();
		System.out.println("\t Digite a data no seguinte formato dd/MM/aaaa");
		String data = scanner.nextLine();
		System.out.println("\t a data digitada foi  " + data);

		String[] arrData = data.split("/");
		
		int dia = Integer.parseInt(arrData[0]);
		int mes = Integer.parseInt(arrData[1]);
		int ano = Integer.parseInt(arrData[2]);
		
		if(mes>0 && mes<=12){
				if(mes == 4 || mes == 6 || mes == 9 || mes == 11 ){
					if (dia >=1 && dia<=30){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Data inválida! Para o dia digite um valor entre 1 e 30  ");
					}
				}else if (mes  == 2){
					if (dia >=1 && dia<29){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Data inválida! Para o dia digite um valor entre 1 e 29  ");
					}
				}else {
					if (dia >=1 && dia<31){
						 gerarRelatorio( mes, ano, dia);
					}else{
						System.out.println("\t Data inválida! Para o dia digite um valor entre 1 e 31");
					}
				}
		}else {
			System.out.println("\t Data inválida! Digite um valor para o mês entre 1(janeiro) e 12(dezembro)  ");
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
			return cadastrarMarca();
		}
		else{
			Marca marcaEscolhida = marcas.get(opcaoMarca - 1);
			return marcaEscolhida;
		}
		return marcas.get(opcaoMarca - 1);
		}
}