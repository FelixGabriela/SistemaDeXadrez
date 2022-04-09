package tabuleiro;

public abstract class Peca {

	protected Posicao posicao; //a posicao é protect porque ela ainda nao é a posicao do xadrez e sim de matriz e nao queremos que ela seja visivel na camada de xadrez
	private Tabuleiro tabuleiro; //essa peca sabe onde o tabuleiro esta
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() { //criou-se o get do tabuleiro, nao se criou o set pois nao irei permitir que o tabuleiro seja alterado
		return tabuleiro;
	}
	
	//movimentos possiveis
	public abstract boolean [][] possiveisMovimentos();
	
	public boolean possivelMovimento(Posicao posicao) { //testa se a peça pode mover para dada posicao
		return possiveisMovimentos() [posicao.getLinha()][posicao.getColuna()]; //metodo que faz um gancho  com a sub classe
	}
	
	public boolean temAlgumMovimentoPossivel() {
		boolean [][] mat = possiveisMovimentos();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) { //lenght pq a matriz do xadrez é quadrada
				if (mat[i][j]) { //se a matriz for verdadeira existe um movimento possivel
					return true;
				}
			}
		}
		return false; //se a varredura da matriz esgotar e n retornar true significa que nenhuma posicao é verdadeira
			
	}
}
