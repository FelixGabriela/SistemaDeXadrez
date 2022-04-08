package xadrez;

import tabuleiro.ExcecaoTabuleiro;

public class ExcecaoXadrez extends ExcecaoTabuleiro{
	private static final long serialVersionUID = 1L;
	
	//definir o construtor que recebe a string
	public ExcecaoXadrez(String msg) {
		super(msg);
	}
}
