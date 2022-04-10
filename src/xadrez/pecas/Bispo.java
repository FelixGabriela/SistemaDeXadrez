package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) { //aqui informo quem é o tabuleiro e a cor da peça
		super(tabuleiro, cor); //repassa a chamada para a superclasse
	} 
	
	@Override
	public String toString() {
		return "B"; //convertendo torre para string, retorna o B de bispo
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; //matriz instanciada
		
		Posicao p = new Posicao(0, 0);
		
		//noroeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() -1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//nordeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() +1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//sudeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() +1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() +1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//sudoeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() -1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}

}
