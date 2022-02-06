package chess;

import boardgame.Position;

// conveter a posição matriz para position xdres
public class ChessPosition {

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'a'  || column > 'h' || row < 1 || row >8) {
			throw new ChessException("Error instantiating ChessPostion");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	// Metodo coonverter position de xadres para matrix postion
	protected Position toPosition () {
		return new Position (8 - row, column - 'a'); // a reprsenta 1 unicod então b - a = 1 , c - a = 2
	}
	// Metodo converter matrix postion para position xadres (Primeiro e coluna e depois linha invertido chadres) 
	protected static ChessPosition  fromPosition ( Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
