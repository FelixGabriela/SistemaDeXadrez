package tabuleiro;

//se fez a implementaçao da peça e do tabuleiro e instansiou-se o tabuleiro
public class Tabuleiro {
 
	private int linhas;
	private int colunas;
	private Peca[][] pecas; //criou-se a matriz de pecas
	
	public Tabuleiro(int linhas, int colunas) { //criou-se o contrutor apenas para linha e coluna
		if (linhas<1 || colunas<1) { //quando for criar o tabuleiro a quantidade de linhas e colunas tem que ser pelo menos um
			throw new ExcecaoTabuleiro("Erro criando tabuleiro: eh necessario que haja pelo menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas]; //instanciou-se a matriz de pecas com linha e colunas
	}

	//não se criou gets e sets das pecas porque terá metodos do tabuleiro uma peca dado uma linha e coluna ou posicao	
	public int getLinhas() { //cria-se o get da linha
		return linhas;
	}

	public int getColunas() { //cria-se o get da coluna
		return colunas;
	}

	public Peca peca(int linha, int coluna) { //metodo para retornar a matriz peça na linha e na coluna
		if (!posicaoExiste(linha, coluna)) { //se a posicao nao existir se lança uma nova excesao no tabuleiro
			throw new ExcecaoTabuleiro("Posicao nao esta no tabuleiro");
		}
		return pecas[linha][coluna];	
	}
	
	public Peca peca(Posicao posicao) { //sobrercarga do metodo de cima, retornando a peca pela posicao
		if (!posicaoExiste(posicao)) { //se a posicao nao existir se lança uma nova excesao no tabuleiro
			throw new ExcecaoTabuleiro("Posicao nao esta no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void lugarPeca(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) { //testa se ja existe uma peça nessa posicao
			throw new ExcecaoTabuleiro("Ja tem uma peca nessa posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca; //o metodo vai na matriz de pecas do tabuleiro e atribui a posicao da peca que veio como argumento
		peca.posicao = posicao; //a posicao nao é mais nula
	}
	
	public Peca removePeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posicao nao esta no tabuleiro");
		}
		if (peca (posicao) == null) {
			return null;
		}
		Peca aux = peca (posicao);
		aux.posicao = null; //a peca foi retirada do tabuleiro e nao existe mais, como mostra o null
		pecas [posicao.getLinha()][posicao.getColuna()] = null; //a posicao que removemos a pessa agora sera nulo
		return aux; //ira retornar a peça que foi retirada
	}
	
	
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha>=0 && linha<linhas && coluna>=0 && coluna<colunas; //condiçao para ver se a posicao existe	
	}
	
	public boolean posicaoExiste(Posicao posicao) { //retorna valor boleano e recebe a posicao como argumento
		return posicaoExiste(posicao.getLinha(), posicao.getColuna()); //reaproveitando o metodo de cima
	}
	
	public boolean temUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) { //testa se a posicao existe, se a posicao nao existir se lança uma nova excesao no tabuleiro
			throw new ExcecaoTabuleiro("Posicao nao esta no tabuleiro"); 
		}
		return peca(posicao) != null;
	}
}
