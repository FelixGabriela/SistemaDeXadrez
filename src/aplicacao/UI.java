package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
				
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) { //esse scanner é instanciado la do programa principal a qual se recebe como argurmento aq
		try {//evita problema de formato
			String s = sc.nextLine();
			char coluna = s.charAt(0); //cria-se a veriavel char coluna recebendo 0, o primeiro caracter do string
			int linha = Integer.parseInt(s.substring(1)); //recorta o string a partir da posicao 1 e converte o resultado apra inteiro, tendo assim a linha
			return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posicao do xadrez. Valido apenas valores de A1 ate H8");
		}
	}
	
	public static void printPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
		printTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		printPecasCapturadas(capturada);
		System.out.println();
		System.out.println("Turno: " + partidaXadrez.getTurno());
		System.out.println("Esperando o jogador: " + partidaXadrez.getJogadorAtual());
	}
	
	//logica para imprimir o tabuleiro do jeito preferivel
	public static void printTabuleiro(PecaXadrez[][] pecas) {
		for (int i=0; i<pecas.length; i++) { //o .lenght é pois consideramos que a matriz é quadrada
			System.out.print((8-i) + " "); //imprimi-se primeiro o numero da linha
			for (int j=0; j<pecas.length; j++) {
				printPeça(pecas[i][j], false); //manda imprimir a peca, o falso é para nenhuma peca ter o fundo colorido
			}
			System.out.println(); //quebra de linha para imprimir a proxima linha
		}
		System.out.println("  A B C D E F G H"); //linha para imprimir os dois espaços em branco do canto e depois de a ate h
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean [][] possiveisMovimentos) {
		for (int i=0; i<pecas.length; i++) { 
			System.out.print((8-i) + " "); 
			for (int j=0; j<pecas.length; j++) {
				printPeça(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println(); 
		}
		System.out.println("  A B C D E F G H"); 
	}
	
	//metodo auxiliar para imprimir uma peça
	private static void printPeça(PecaXadrez peca, boolean fundo){ 
		if (fundo) { //se a veriavel for verdadeira muda o fundo da tela
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.BRANCO) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_BLUE + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printPecasCapturadas (List<PecaXadrez> capturada) { //gerou-se duas listas, uma de pecas brancas e outra de pretas
		List<PecaXadrez> BRANCO = capturada.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList()); //filtra na lista todos que sao brancos
		List<PecaXadrez> PRETO = capturada.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		System.out.println("Pecas capturadas:"); //imprime lista de peças capturadas
		
		System.out.print("Brancas:");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(BRANCO.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Pretas:");
		System.out.print(ANSI_BLUE);
		System.out.println(Arrays.toString(PRETO.toArray()));
		System.out.print(ANSI_RESET);
	}
}
