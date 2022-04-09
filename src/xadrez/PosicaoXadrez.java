package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	//construtor
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna<'A' || coluna>'H' || linha<1 || linha>8) { //acrescenta-se programacao defensiva
			throw new ExcecaoXadrez("Erro instanciando a PosicaoXadrez. Valores validos sao de A1 ate H8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao toPosicao() { //esse metodo retorna uma nova posicao, a qual a linha é o 8- a linha da posicao do xadrez
		return new Posicao(8 - linha, coluna - 'A');
	}
	
	protected static PosicaoXadrez fromPosicao(Posicao posicao) {
		return new PosicaoXadrez((char) ('A' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; //a string vazia '""' é um macete para concatenar automatica, visto que se tirar o compilador nao aceita
	}
	
}
