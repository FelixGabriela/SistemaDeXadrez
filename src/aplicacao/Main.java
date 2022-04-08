package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez(); //instancia a partida de xadrez
		
		while (true) {
			UI.printTabuleiro(partidaXadrez.getPecas()); //funçao que imprime as peças
			System.out.println();
			System.out.print("Origem: "); //pedimos para entrar com a posicao de origem
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc); 
			
			System.out.println();
			System.out.print("Destino: "); //pedimos para entrar com a posicao de destino
			PosicaoXadrez destino = UI.lerPosicaoXadrez(sc); 
			
			PecaXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(origem, destino); //chamada
			
		}
	}

}
