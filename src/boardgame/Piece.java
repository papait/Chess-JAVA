package boardgame;

// Classe generica Pe�a
public class Piece {

	// Associa��o da pe�a com tabuleiro reala��o 1 para n
	protected Position position;
	private Board board;
	
	// POSITION null pq a pre�a e recem crianda n�o est� no tabuleiro ainda
	public Piece(Board board) {
		this.board = board;
		position = null;
	}
	// Classes no mesmo pacote e sub classe pode acessar (Camadas) 
	protected Board getBoard() {
		return board;
	}

	
	
}
