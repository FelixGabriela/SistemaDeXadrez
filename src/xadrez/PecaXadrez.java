package xadrez;
import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca{ //essa classe é uma subclasse de peca
	
	private Cor cor;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) { //construtor recebe tabuleiro e cor
		super(tabuleiro); //o tabuleiro passa a chamada para o construtor da super classe
		this.cor = cor;
	}

	public Cor getCor() { //so se tem o get pois o set modificaria a cor de uma peca, e o get apenas a acessa
		return cor;
	}


	
		
}
