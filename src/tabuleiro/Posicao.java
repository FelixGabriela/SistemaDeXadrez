package tabuleiro;

//nessa parte fizemos o encapsulamento dos dados, o construtor e o toString (que aplica o conceito de Object e sobreposiçao)

public class Posicao {

	private int linha; //atributos
	private int coluna; //atributos
	
	public Posicao(int linha, int coluna) { //criou-se o construtor com os argumentos
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {  //criou-se o get da linha 
		return linha;
	}

	public void setLinha(int linha) { //criou-se o set da linha 
		this.linha = linha;
	}

	public int getColuna() { //criou-se o get da coluna 
		return coluna;
	}

	public void setColuna(int coluna) { //criou-se o set da da coluna
		this.coluna = coluna;
	}
	
	@Override //criou-se o toString para imprimir a posicao na tela
	public String toString() {
		return linha + ", " + coluna; //colocar para imprimir a linha virgula e a coluna
		
	}
}
