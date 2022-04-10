package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	private  PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) { //linha 11 ate a 16 foi a associação dos objetos
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
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
			
			//movimento especial, en passant branco
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().posicaoExiste(esquerda) && temPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
					mat [esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().posicaoExiste(direita) && temPecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
					mat [direita.getLinha() -1][direita.getColuna()] = true;
				}
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
			
			//movimento especial, en passant preto
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().posicaoExiste(esquerda) && temPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
					mat [esquerda.getLinha() +1][esquerda.getColuna()] = true; //+1 pq andar para baixo na matriz é sempre positivo
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().posicaoExiste(direita) && temPecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
					mat [direita.getLinha() +1][direita.getColuna()] = true;
				}
			}
			
		}
		return mat;
	}
	
	@Override
	public String toString(){
		return "P";
	}

}
