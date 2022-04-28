package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
// Nessa class que tem as regras do jogo xafres
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<Piece> pieceOntheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	// Classe chessmatch que determinar o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}


	public Color getCurrentPlayer() {
		return currentPlayer;
	}


	// returna uma matriz de peça de xadres relacionadas a essa partida
	// o tabalueiro tem uma matriz de peças
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // Matriz de chesspiece devido a
																					// camada
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// Donwcasting piece --> chesspiece
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		// Retornar a matriz de peças da minha partida xadres
		return mat;
	}
	
	public boolean [][] posibleMoves (ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePostion(position);
		return board.piece(position).possibleMoves();
	}
	
	// Metodo mover peça

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// conveter para posiçã matrix

		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		// Operação valiar se tem alguma peça na posição
		validateSourcePostion(source);
		validateTargetPositon(source, target);
		Piece capturesPiece = makeMove(source, target);// Validar se tem uma peça nessa posição
		nextTurn();
		return (ChessPiece) capturesPiece;
	}

	// Logica de realizar movimento
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			pieceOntheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;

	}

	private void validateSourcePostion(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != (((ChessPiece) board.piece(position)).getColor())) {
			throw new ChessException("tHE CHOSEN PIECE IS NOT YOURS");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	private void validateTargetPositon (Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) { // reveber um false
			throw new ChessException("The chosen piece can-t movre to target position");
		}
	}

	private void nextTurn () {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	// Operação de colocar peça passando as oeperações cordenadas xadres
	private void placeNewPiece(char column, int row, ChessPiece pieces) {
		board.placePiece(pieces, new ChessPosition(column, row).toPosition());
		pieceOntheBoard.add(pieces);
	}

	// Passar as pocições do xadres
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
