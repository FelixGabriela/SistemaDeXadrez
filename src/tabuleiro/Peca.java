package tabuleiro;

public class Peca {

	protected Posicao posicao; //a posicao é protect porque ela ainda nao é a posicao do xadrez e sim de matriz e nao queremos que ela seja visivel na camada de xadrez
	private Tabuleiro tabuleiro; //essa peca sabe onde o tabuleiro esta
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() { //criou-se o get do tabuleiro, nao se criou o set pois nao irei permitir que o tabuleiro seja alterado
		return tabuleiro;
	}
	
}
