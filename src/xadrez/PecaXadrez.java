package xadrez;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{ //essa classe é uma subclasse de peca
	
	private Cor cor;
	private int contagemMovimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) { //construtor recebe tabuleiro e cor
		super(tabuleiro); //o tabuleiro passa a chamada para o construtor da super classe
		this.cor = cor;
	}

	public Cor getCor() { //so se tem o get pois o set modificaria a cor de uma peca, e o get apenas a acessa
		return cor;
	}
	
	public int getContagemMovimentos() {
		return contagemMovimentos;
	}
	
	public void aumentaContagemMovimento() {
		contagemMovimentos ++;
	}
	
	public void diminuiContagemMovimento() {
		contagemMovimentos --;
	}
	
	public PosicaoXadrez getPosicaoXadrez (){
		return PosicaoXadrez.fromPosicao(posicao);
	}

	protected boolean temPecaOponente(Posicao posicao) { //verifica se existe peca adversaria na posicao
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao); //peguei a peça da posicao
		return p != null && p.getCor() != cor; //testa se é peca do oponente, testando se a cor da peca da posicao é diferente da minha peça
	}
	
		
}
