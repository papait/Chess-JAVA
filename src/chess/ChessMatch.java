package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	// Classe que determinar o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board (8,8);
		initialSetup();
	}
	// returna uma matriz de pe�a de xadres relacionadas a essa partida
	// o tabalueiro tem uma matriz de pe�as
	public ChessPiece[][] getPieces (){
		ChessPiece [][] mat = new ChessPiece [board.getRows()][board.getColumns()]; // Matriz de chesspiece devido a camada
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getColumns(); j++) {
				//Donwcasting piece --> chesspiece
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		// Retornar a matriz de pe�as da minha partida xadres
		return mat;
	}
	
	// Opera��o de colocar pe�a passando as oepera��es cordenadas xadres
	private void placeNewPiece (char column, int row, ChessPiece pieces) {
		board.placePiece(pieces, new ChessPosition(column, row).toPosition());
	}
	//Passar as poci��es do xadres
	private void initialSetup () {
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
