package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez(); //instancia a partida de xadrez
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while (!partidaXadrez.getCheckMate()) {
			try {
				UI.clearScreen(); //limpa a tela a cada vez que voltar
				UI.printPartida(partidaXadrez, capturada); //sempre que se imprimir a partida passa a lista de pecas capturadas
				System.out.println();
				System.out.print("Origem: "); //pedimos para entrar com a posicao de origem
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc); 
				
				boolean [][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(origem); //imprime possiveis movimentos na tela
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecas(), possiveisMovimentos); //criou-se uma sobrecarga do print do tabuleiro, é responsavel pro imprimir o tabuleiro com as cores das possiveis posicoes
				System.out.println();
				System.out.print("Destino: "); //pedimos para entrar com a posicao de destino
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc); 
				
				PecaXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(origem, destino); //chamada
				
				if(pecaCapturada != null) { //sempre que se executar um movimento e esse capturar uma peca adiciona na lista de pecas capturadas
					capturada.add(pecaCapturada);
				}
				
				if (partidaXadrez.getPromocao() != null) { //se for diferente de nulo uma peca sera promovida
					System.out.println("Digite a letra que gostaria que a peca fosse promovida (B/C/T/Q): ");
					String type = sc.nextLine().toUpperCase(); //.toUpperCase converte a letra para maiuscula
					while (!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) { //enquanto o usuario nao digitar corretamente ira repetir
						System.out.println("Letra invalida! Digite a letra que gostaria que a peca fosse promovida (B/C/T/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituiPecaPromocao(type); //manda trocar a peca promovida
				}
				
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
		UI.clearScreen();
		UI.printPartida(partidaXadrez, capturada);
	}
}
