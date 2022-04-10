package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez; //roque
	
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez; //construtor para o roque
	}
	
	private boolean  testaTorreRoque (Posicao posicao) { //metodo (que recebe posicao) auxiliar que testa a condicao de roque
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao); //pega a peca que ta na posicao (la no metodo)
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0; //se a peca for diferente de nulo e se ela é uma torre, se a cor é igual a cor do rei e se a contagem de movimento é 0
	}
	
	@Override
	public String toString() {
		return "K"; //retorna o R de Rei
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
		
		//movimento especial 
		if (getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			//movimento especial roque do lado direito
			Posicao posicaoTorreUm = new Posicao(posicao.getLinha(), posicao.getColuna() +3);
			if (testaTorreRoque(posicaoTorreUm)) { //testa se as posicoes ao lado direito estao vazias
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() +2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null){ //se a posicao p1 e p2 for nulo (sem peca) pode fazer o roque
					mat [posicao.getLinha()][posicao.getColuna() +2] = true;
				}
			}
			
			// especial movimento roque do lado esquedo
			Posicao posicaoTorreDois = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if (testaTorreRoque(posicaoTorreDois)) { //testa se as posicoes ao lado esquedo estao vazias
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() -2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() -3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){ //se a posicao p1, p2 e p3 for nulo (sem peca) pode fazer o roque
					mat [posicao.getLinha()][posicao.getColuna() -2] = true; //o rei pode mover duas casas para a esquerda
				}
			}
		}
		
		return mat;
	}
}
