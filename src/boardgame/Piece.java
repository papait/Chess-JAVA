package boardgame;

// Classe generica Pe�a
public abstract class Piece {

	// Associa��o da pe�a com tabuleiro reala��o 1 para n
	// Associa��o de Tabuleiro e possi��o pe�a
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

	public abstract boolean[][] possibleMoves();
	// Implementando metodo concreto em um abstrato. S� vai existir se o abstrato existir
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
