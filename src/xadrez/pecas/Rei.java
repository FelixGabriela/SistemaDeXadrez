package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R"; //retorna o R de Rei
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//acima
		p.setValores(posicao.getLinha() -1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValores(posicao.getLinha() +1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//diretia
		p.setValores(posicao.getLinha(), posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//noroeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//nordeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//sudoeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//sudeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
