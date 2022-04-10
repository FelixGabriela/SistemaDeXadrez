package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; //matriz instanciada
		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao acima dele existir e estiver vazia ele pode mover
			}
			p.setValores(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimentos() == 0) {
				mat [p.getLinha()][p.getColuna()] = true; //se as duas posicao acima dele existir e estiver vazia ele pode mover
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() -1); //diagonal esquerda
			if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao ao lado dele existir e estiver com uma peça adversaria, ele pode mover
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() +1); //diagonal direita
			if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao ao lado dele existir e estiver com uma peça adversaria, ele pode mover
			}	
		}
		else {
			p.setValores(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao acima dele existir e estiver vazia ele pode mover
			}
			p.setValores(posicao.getLinha() +2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimentos() == 0) {
				mat [p.getLinha()][p.getColuna()] = true; //se as duas posicao acima dele existir e estiver vazia ele pode mover
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() -1); //diagonal esquerda
			if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao ao lado dele existir e estiver com uma peça adversaria, ele pode mover
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() +1); //diagonal direita
			if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
				mat [p.getLinha()][p.getColuna()] = true; //se a posicao ao lado dele existir e estiver com uma peça adversaria, ele pode mover
			}	
		}
		return mat;
	}
	
	@Override
	public String toString(){
		return "P";
	}

}
