package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//essa classe é o coração do sistema de xadrez, é a classe que contem as regras
public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaXadrez(){ //construtor padrão
		tabuleiro = new Tabuleiro(8, 8); //dimensao do tabuleiro
		iniciaPartida(); //chamando o construtor para inciar a partida
	}
	
	
	public PecaXadrez[][] getPecas() { //metodo que retorna a peça de xadrez, ele retornara uma matriz de peças de xadrez correspondentes a partida, é um metodo projetado
		PecaXadrez[][] mat = new PecaXadrez [tabuleiro.getLinhas()] [tabuleiro.getColunas()]; //variavel auxiliar mat que é inciada com a Peca de Xadrez, com a quantidade de linhas e colunas do tabuleiro
		for (int i=0; i<tabuleiro.getLinhas(); i++) { //percorre as linhas da matriz
			for (int j=0; j<tabuleiro.getColunas(); j++) { //percorre as colunas da matriz
				 mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j); //a matriz mat na linha i coluna j recebe peça, foi feito tambem um downcast para interpretar como peca de xadrez
			}
		}
		return mat;	
	}
	
	public PecaXadrez executarMovimentoXadrez (PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPecaOrigem (origem);
		Peca pecaCapturada = facaMover(origem, destino); //valida se existia uma peca na posicao de origem, se nao lanca excecao
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca facaMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem); //retirei a peca da posicao de origem
		Peca pecaCapturada = tabuleiro.removePeca(destino); //remove a posivel peca da posicao do destino, que sera capturada
		tabuleiro.lugarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validaPecaOrigem (Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)){
			throw new ExcecaoXadrez ("Nao existe nenhuma peca na posicao de origem");
		}
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) { //metodo que contem as coordenadas do xadrez para colocar peca
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void iniciaPartida() { //metodo responsavel por iniciar partida, colocando as peças no tabuleiro, para iniciar tem que chamar o inicarPartida no construto no comeco do codigo
		novoLugarPeca('C', 1, new Torre(tabuleiro, Cor.BRANCO)); 
		novoLugarPeca('C', 2, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('D', 2, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('E', 2, new Torre(tabuleiro, Cor.BRANCO)); 
		novoLugarPeca('E', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('D', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		novoLugarPeca('C', 7, new Torre(tabuleiro, Cor.PRETO)); 
		novoLugarPeca('C', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('D', 7, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('E', 7, new Torre(tabuleiro, Cor.PRETO)); 
		novoLugarPeca('E', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('D', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
