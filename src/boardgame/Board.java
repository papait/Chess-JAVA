package boardgame;

public class Board {

	
	private int rows;
	private int columns; // Quantidade linhas e colunas do tabuleiro 
	private Piece [][]  pieces; // Tabuleiro vai ter uma matriz de pe�as
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException ("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		// Matriz de pe�as vai ser instanciada na quantidade de linhas e colunas informadas
		pieces = new Piece [rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	// Metodo retornar uma pe�a (matriz) dado uma linha e coluna
	public Piece piece (int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces [row][column];
	}
	// Metodo retornar uma pe�a (matriz) dado uma posi��o
	public Piece piece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	} 
	// Esse metodo faz com que a matrix pieces(na posi��o talz) receba uma pe�a por parametro
	public void placePiece (Piece piece, Position position) {
		
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; // Falo que que essa pe�a n�o esta mais na posi��o null
	}
	
	//Testa posi��o existe
	public boolean positionExists (int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists (Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}
