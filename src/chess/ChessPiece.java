package chess;

import boardgame.Board;
import boardgame.Piece;

// Subclasse da Piece com outros metodos e elementos

public abstract class ChessPiece extends Piece{

	private Color color;

	public ChessPiece(Board board, Color color) {
		// repassa a cahamanda para super class
		super(board);
		this.color = color;
	}

	public Color getColor () {
		return color;
	}
	
	
}
