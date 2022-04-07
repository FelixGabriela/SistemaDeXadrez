package aplicacao;
import xadrez.PartidaXadrez;

public class Main {

	public static void main(String[] args) {

		PartidaXadrez partidaXadrez = new PartidaXadrez(); //instancia a partida de xadrez
		UI.printTabuleiro(partidaXadrez.getPecas()); //funçao que imprime as peças
		
	}

}
