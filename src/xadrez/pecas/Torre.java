package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) { //aqui informo quem é o tabuleiro e a cor da peça
		super(tabuleiro, cor); //repassa a chamada para a superclasse
	} 
	
	@Override
	public String toString() {
		return "T"; //convertendo torre para string, retorna o T de torre
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}

}
