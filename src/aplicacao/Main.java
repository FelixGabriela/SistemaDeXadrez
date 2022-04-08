package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez(); //instancia a partida de xadrez
		
		while (true) {
			try {
				UI.clearScreen(); //limpa a tela a cada vez que voltar
				UI.printTabuleiro(partidaXadrez.getPecas()); //funçao que imprime as peças
				System.out.println();
				System.out.print("Origem: "); //pedimos para entrar com a posicao de origem
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc); 
				
				System.out.println();
				System.out.print("Destino: "); //pedimos para entrar com a posicao de destino
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc); 
				
				PecaXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(origem, destino); //chamada
			}
			catch (ExcecaoXadrez e) { //se acontecer alguma excecao do xadrez:
				System.out.println(e.getMessage()); //imprime a mensagem na tela
				sc.nextLine(); //para o programa aguardar o enter
			}
			catch (InputMismatchException e) { 
				System.out.println(e.getMessage()); //imprime a mensagem na tela
				sc.nextLine(); //para o programa aguardar o enter
			}
		}
	}
}
