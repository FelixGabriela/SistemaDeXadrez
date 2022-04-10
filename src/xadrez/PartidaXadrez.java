package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//essa classe � o cora��o do sistema de xadrez, � a classe que contem as regras
public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassant;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez(){ //construtor padr�o
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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PecaXadrez getEnPassant(){
		return enPassant;
	}

	public PecaXadrez[][] getPecas() { //metodo que retorna a pe�a de xadrez, ele retornara uma matriz de pe�as de xadrez correspondentes a partida, � um metodo projetado
		PecaXadrez[][] mat = new PecaXadrez [tabuleiro.getLinhas()] [tabuleiro.getColunas()]; //variavel auxiliar mat que � inciada com a Peca de Xadrez, com a quantidade de linhas e colunas do tabuleiro
		for (int i=0; i<tabuleiro.getLinhas(); i++) { //percorre as linhas da matriz
			for (int j=0; j<tabuleiro.getColunas(); j++) { //percorre as colunas da matriz
				 mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j); //a matriz mat na linha i coluna j recebe pe�a, foi feito tambem um downcast para interpretar como peca de xadrez
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
		
		if (testaCheck(jogadorAtual)) {
			desfazMover(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Voce nao pode se colocar em Check");
		}
		
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino); //peca que se moveu foi a colocada no destino
		
		check = (testaCheck(oponente(jogadorAtual))) ? true:false;
		
		if (testaCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} 
		else {
			proximoTurno();
		}
		
		//movimento especial, en passant
		if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() -2 || destino.getLinha() == origem.getLinha() +2)) {//se a pe�a movida foi um peao e a diferefen�a de linha foi 2
			enPassant = pecaMovida; //ta vuneravel a tomar en passant
		}
		else {
			enPassant = null;
		}
		
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca facaMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem); //retirei a peca da posicao de origem
		p.aumentaContagemMovimento();
		Peca pecaCapturada = tabuleiro.removePeca(destino); //remove a posivel peca da posicao do destino, que sera capturada
		tabuleiro.lugarPeca(p, destino);
		
		if (pecaCapturada != null) { //sempre que se fizer um movimento e esse movimento capturada uma peca:
			pecasNoTabuleiro.remove(pecaCapturada); //retira a peca da lista de peca
			pecasCapturadas.add(pecaCapturada); //e adiciona na lista de pecas capturada
		}
		
		//movimento especial, roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() +2) { //duas casas para a direita
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() +3); //posicao de origem
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() +1); //posicao de destino
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemTorre); //pega a torre  e retira onde ela esta
			tabuleiro.lugarPeca(torre, destinoTorre); //coloca a torre na posicao de destino dela
			torre.aumentaContagemMovimento(); //incrementa movimento visto que se moveu
		}
		//movimento especial, roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() -2) { //duas casas para a esqueda
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() -4); //posicao de origem
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() -1); //posicao de destino
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemTorre); //pega a torre  e retira onde ela esta
			tabuleiro.lugarPeca(torre, destinoTorre); //coloca a torre na posicao de destino dela
			torre.aumentaContagemMovimento(); //incrementa movimento visto que se moveu
		}
		
		//movimento especial, enPassant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) { //se o peao andar na diagonal e nao capturar pe�a, significa que � en passant
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() +1, destino.getColuna());
				}
				else {
					posicaoPeao = new Posicao(destino.getLinha() -1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removePeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
	
	private void desfazMover(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(destino);
		tabuleiro.lugarPeca(p, origem);
		p.diminuiContagemMovimento();
		if (pecaCapturada != null) {
			tabuleiro.lugarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		//movimento especial, roque pequeno desfazendo
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() +2) { //duas casas para a direita
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() +3); //posicao de origem
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() +1); //posicao de destino
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoTorre); //remove da posicao de destino
			tabuleiro.lugarPeca(torre, origemTorre); //devolve a torre na posicao de origem
			torre.diminuiContagemMovimento(); //decrementa
		}	
		//movimento especial, roque grande desfazendo
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() -2) { //duas casas para a esqueda
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() -4); //posicao de origem
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() -1); //posicao de destino
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoTorre); //remove da posicao de destino
			tabuleiro.lugarPeca(torre, origemTorre); //devolve a torre na posicao de origem
			torre.diminuiContagemMovimento(); //decrementa
		}
		
		//movimento especial, enPassant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassant) { //se for a peca vulneravel en passant
				PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(destino); //fazendo devolucao manualmente
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) { //se a peca capturada for a branca
					posicaoPeao = new Posicao(3, destino.getColuna()); //devolve para a linha 3
				}
				else {
					posicaoPeao = new Posicao(4, destino.getColuna()); //se for a preta devolve na linha 4
				}
				tabuleiro.lugarPeca(peao, posicaoPeao);
				//nao � necessario fazer a troca de lista igual no fazMover pq essa troca ja foi feita na linha 162
			}
		}
	}
	
	private void validaPecaOrigem (Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)){
			throw new ExcecaoXadrez ("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) { //pega a peca do tabuleiro nessa posicao faz o downquest para pe�a xadrez e testa a cor dela, que se for diferente do jogador atual � do adversaio
			throw new ExcecaoXadrez("A peca escolhida nao eh sua");
		}
		if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) { //outra validacao da peca de origem, SE NAO (!) EXISTIR MOVIMENTO POSSIVEL
			throw new ExcecaoXadrez ("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validaPecaDestino (Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) { //se para pe�a de origem a posicao de destino nao � um movimento possivel, signifca que nao pode se mover para la
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO; //troca a cor do jgoador atual
	}
	
	private Cor oponente(Cor cor){ //se a cor for igual a branca, retorna a preta, caso contraio retorna a branca
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) { //para cada peca p da lista
			if (p instanceof Rei) { //se a peca for uma instancia de rei
				return (PecaXadrez) p; //significa que encontrou o rei entao retorna ele
			}
		}
		throw new IllegalStateException("Nao existe o " + cor + "Rei no tabuleiro");
	}
	
	private boolean testaCheck(Cor cor) { //testa se o rei dessa cor esta em check
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List <Peca> pecasOponentes =  pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOponentes) {
			boolean [][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testaCheckMate(Cor cor) {
		if (!testaCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca capturedPiece = facaMover(origem, destino);
						boolean testaCheck = testaCheck(cor);
						desfazMover(origem, destino, capturedPiece);
						if (!testaCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) { //metodo que contem as coordenadas do xadrez para colocar peca
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca); //coloca a peca na lista de peca no tabuleiro tambem
	}
	
	private void iniciaPartida() { //metodo responsavel por iniciar partida, colocando as pe�as no tabuleiro, para iniciar tem que chamar o inicarPartida no construto no comeco do codigo
		novoLugarPeca('A', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('B', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('C', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('D', 1, new Rainha(tabuleiro, Cor.BRANCO));
		novoLugarPeca('E', 1, new Rei(tabuleiro, Cor.BRANCO, this)); //Ao inves de instanciar o rei passamos a propria partida que se esta, fazemos isso usando a auto referencia this
		novoLugarPeca('F', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('G', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('H', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('A', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('B', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('C', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('D', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('E', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('F', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('G', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novoLugarPeca('H', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		
		novoLugarPeca('A', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('B', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novoLugarPeca('C', 8, new Bispo(tabuleiro, Cor.PRETO));
		novoLugarPeca('D', 8, new Rainha(tabuleiro, Cor.PRETO));
		novoLugarPeca('E', 8, new Rei(tabuleiro, Cor.PRETO, this)); //Ao inves de instanciar o rei passamos a propria partida que se esta, fazemos isso usando a auto referencia this
		novoLugarPeca('F', 8, new Bispo(tabuleiro, Cor.PRETO));
		novoLugarPeca('G', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novoLugarPeca('H', 8, new Torre(tabuleiro, Cor.PRETO)); 
		novoLugarPeca('A', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novoLugarPeca('B', 7, new Peao(tabuleiro, Cor.PRETO, this)); 
		novoLugarPeca('C', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novoLugarPeca('D', 7, new Peao(tabuleiro, Cor.PRETO, this)); 
		novoLugarPeca('E', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novoLugarPeca('F', 7, new Peao(tabuleiro, Cor.PRETO, this)); 
		novoLugarPeca('G', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novoLugarPeca('H', 7, new Peao(tabuleiro, Cor.PRETO, this)); 

	}
}
