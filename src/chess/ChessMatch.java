package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	// Classe que determinar o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
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
	// Metodo mover peça

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// conveter para posiçã matrix

		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		// Operação valiar se tem alguma peça na posição
		validateSourcePostion(source);
		validateTargetPositon(source, target);
		// Validar se tem uma peça nessa posição
		Piece capturesPiece = makeMove(source, target);
		return (ChessPiece) capturesPiece;
	}

	// Logica de realizar movimento
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		return capturedPiece;

	}

	private void validateSourcePostion(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	private void validateTargetPositon (Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can-t movre to target position");
		}
	}

	// Operação de colocar peça passando as oeperações cordenadas xadres
	private void placeNewPiece(char column, int row, ChessPiece pieces) {
		board.placePiece(pieces, new ChessPosition(column, row).toPosition());
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
