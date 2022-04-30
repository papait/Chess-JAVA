package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

// Subclasse da Piece com outros metodos e elementos

public abstract class ChessPiece extends Piece{

	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		// repassa a chamada para contrututor da super class
		super(board);
		this.color = color;
	}
	
	//Metodo pegar uma position da classe genérica e converter para posição de chess
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	public int getMoveCount () {
		return moveCount;
	}
	
	public Color getColor () {
		return color;
	}
	
	public void increaseMoveCount () {
		moveCount ++;
	}
	
	public void decreaseMoveCount () {
		moveCount --;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		// donwcasting
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color; // cor diferente da minha peça
	}
}
