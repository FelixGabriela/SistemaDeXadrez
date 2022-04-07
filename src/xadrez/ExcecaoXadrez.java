package xadrez;

public class ExcecaoXadrez extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//definir o construtor que recebe a string
	public ExcecaoXadrez(String msg) {
		super(msg);
	}
}
