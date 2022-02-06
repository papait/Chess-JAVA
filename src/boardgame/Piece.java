package boardgame;

// Classe generica Peça
public class Piece {

	// Associação da peça com tabuleiro realação 1 para n
	protected Position position;
	private Board board;
	
	// POSITION null pq a preça e recem crianda não está no tabuleiro ainda
	public Piece(Board board) {
		this.board = board;
		position = null;
	}
	// Classes no mesmo pacote e sub classe pode acessar (Camadas) 
	protected Board getBoard() {
		return board;
	}

	
	
}
