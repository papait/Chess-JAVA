package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Instanciado partida
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		// Imprimindo as peças da partida
		// metodo receber a matriz de peças

		while (!chessMatch.getCheckMate()) { // Rodo o programa enquanto não estiver em check (TRUE)
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(input);

				boolean[][] possibleMoves = chessMatch.posibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(input);

				ChessPiece capturePiece = chessMatch.performChessMove(source, target); // Retornar uma possivel peça
																						// captura

				if (capturePiece != null) { // Peça cpturada
					captured.add(capturePiece);
				}

				UI.clearScreen();
				UI.printMatch(chessMatch, captured); // Mostra partida finalizada
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			}
		}
	}

}
