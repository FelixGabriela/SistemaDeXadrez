package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) { //aqui informo quem � o tabuleiro e a cor da pe�a
		super(tabuleiro, cor); //repassa a chamada para a superclasse
	} 
	
	@Override
	public String toString() {
		return "Q"; //q de queen
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; //matriz instanciada
		
		Posicao p = new Posicao(0, 0);
		
		//acima (posicao na mesma coluna porem a linha deve ser decrementada)
		p.setValores(posicao.getLinha() -1, posicao.getColuna()); //pega  a posicao da propria pe�a soq menos 1 da linha dela, visto que � acima da peca
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { //enquanto a posicao p existir e nao tiver uma pe�a la, pode marcar como verdadeira a posicao
			mat [p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() -1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna() +1); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() +1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		//baixo
		p.setValores(posicao.getLinha() +1, posicao.getColuna()); 
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { 
			mat [p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() +1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
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
