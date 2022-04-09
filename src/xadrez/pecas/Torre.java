package xadrez.pecas;

import tabuleiro.Posicao;
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
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; //matriz instanciada
		
		Posicao p = new Posicao(0, 0);
		
		//acima (posicao na mesma coluna porem a linha deve ser decrementada)
		p.setValores(posicao.getLinha() -1, posicao.getColuna()); //pega  a posicao da propria peça soq menos 1 da linha dela, visto que é acima da peca
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { //enquanto a posicao p existir e nao tiver uma peça la, pode marcar como verdadeira a posicao
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
		
		return mat;
	}

}
