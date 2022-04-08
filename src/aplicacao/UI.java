package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cor;
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
	
	//logica para imprimir o tabuleiro do jeito preferivel
	public static void printTabuleiro(PecaXadrez[][] pecas) {
		for (int i=0; i<pecas.length; i++) { //o .lenght é pois consideramos que a matriz é quadrada
			System.out.print((8-i) + " "); //imprimi-se primeiro o numero da linha
			
			for (int j=0; j<pecas.length; j++) {
				printPeça(pecas[i][j]); //manda imprimir a peca	
			}
			System.out.println(); //quebra de linha para imprimir a proxima linha
		}
		System.out.println("  A B C D E F G H"); //linha para imprimir os dois espaços em branco do canto e depois de a ate h
	}
	
	//metodo auxiliar para imprimir uma peça
	private static void printPeça(PecaXadrez peca){ 
		if (peca == null) {
			System.out.print("-");
		} else {
			if (peca.getCor() == Cor.BRANCO) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
}
