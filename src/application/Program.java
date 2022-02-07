package application;

import java.util.Scanner;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Instanciado partida
		ChessMatch chessMatch = new ChessMatch ();
		// Imprimindo as peças da partida
		// metodo receber a matriz de peças
		
		while (true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(input);
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(input);
			
			ChessPiece capturePiece = chessMatch.performChessMove(source, target);
		}
	}

}
