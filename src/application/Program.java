package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		// Instanciado partida
		ChessMatch chessMatch = new ChessMatch ();
		// Imprimindo as pe�as da partida
		// metodo receber a matriz de pe�as
		UI.printBoard(chessMatch.getPieces());
	}

}
