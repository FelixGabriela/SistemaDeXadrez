package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//essa classe é o coração do sistema de xadrez, é a classe que contem as regras
public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez(){ //construtor padrão
		tabuleiro = new Tabuleiro(8, 8); //dimensao do tabuleiro
		turno = 1; //primeiro turno
		jogadorAtual = Cor.BRANCO; //primeiro turno incio da partida
		iniciaPartida(); //chamando o construtor para inciar a partida
	}
	
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean [][] possiveisMovimentos(PosicaoXadrez origemPosicao){ //imprime posicoes possiveis a partir de uma posicao de origem
		Posicao posicao = origemPosicao.toPosicao();
		validaPecaOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez executarMovimentoXadrez (PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPecaOrigem (origem);
		validaPecaDestino (origem, destino);
		Peca pecaCapturada = facaMover(origem, destino); //valida se existia uma peca na posicao de origem, se nao lanca excecao
		proximoTurno();
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca facaMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem); //retirei a peca da posicao de origem
		Peca pecaCapturada = tabuleiro.removePeca(destino); //remove a posivel peca da posicao do destino, que sera capturada
		tabuleiro.lugarPeca(p, destino);
		
		if (pecaCapturada != null) { //sempre que se fizer um movimento e esse movimento capturada uma peca:
			pecasNoTabuleiro.remove(pecaCapturada); //retira a peca da lista de peca
			pecasCapturadas.add(pecaCapturada); //e adiciona na lista de pecas capturada
		}
		
		return pecaCapturada;
	}
	
	private void validaPecaOrigem (Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)){
			throw new ExcecaoXadrez ("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) { //pega a peca do tabuleiro nessa posicao faz o downquest para peça xadrez e testa a cor dela, que se for diferente do jogador atual é do adversaio
			throw new ExcecaoXadrez("A peca escolhida nao eh sua");
		}
		if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) { //outra validacao da peca de origem, SE NAO (!) EXISTIR MOVIMENTO POSSIVEL
			throw new ExcecaoXadrez ("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validaPecaDestino (Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) { //se para peça de origem a posicao de destino nao é um movimento possivel, signifca que nao pode se mover para la
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO; //troca a cor do jgoador atual
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) { //metodo que contem as coordenadas do xadrez para colocar peca
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca); //coloca a peca na lista de peca no tabuleiro tambem
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
