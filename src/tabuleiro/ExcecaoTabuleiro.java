package tabuleiro;

public class ExcecaoTabuleiro extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExcecaoTabuleiro (String msg) { //cosntrutor repassa a mensagem para o construtor da super classe (RuntimeException)
		super(msg);
	}

}
