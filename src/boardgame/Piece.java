package boardgame;

// Classe generica Peça
public abstract class Piece {

	// Associação da peça com tabuleiro realação 1 para n
	// Associação de Tabuleiro e possição peça
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

	public abstract boolean[][] possibleMoves();
	// Implementando metodo concreto em um abstrato. Só vai existir se o abstrato existir
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
