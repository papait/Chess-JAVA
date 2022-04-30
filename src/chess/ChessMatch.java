package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	private boolean check; // padrão começa com false
	private boolean checkMate;
	
	private List<Piece> pieceOntheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	// Classe chessmatch que determinar o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
		check = false;
	}
	
	public int getTurn() {
		return turn;
	}


	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheckMate () {
		return checkMate;
	}
	
	public boolean getCheck () {
		return check;
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
		
		//Testa movimento do jogador deixou ele em check
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturesPiece);
			throw new ChessException("You cant put yourself in check");
		}
		
		check = (testCheck (opponent(currentPlayer))) ? true : false;
		// Testa se a jogada deixou o jogo em chechMate
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true; // ACaba a partida
		} else {
			
		nextTurn(); //Proximo
		}
		return (ChessPiece) capturesPiece;
	}
	
	// Metodo desfazer movimento// Check
	private void undoMove(Position source, Position target, Piece capturePiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);  //aqui já e feito um upcasting natural com p Piece
		
		if (capturePiece != null) {
			board.placePiece(capturePiece, target);
			capturedPieces.remove(capturePiece);
			pieceOntheBoard.add(capturePiece);
		}
	}

	// Logica de realizar movimento
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target); // aqui já e feito um upcasting natural com p Piece

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
	// Retornar devolver o opnente de uma cor
	
	private Color opponent (Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	// Metodo vai localizar o rei de uma determinar cor varrendo as peças do jogo
	
	private ChessPiece king (Color color) {
		List<Piece> list = pieceOntheBoard.stream().filter(x-> ((ChessPiece)x).getColor()==color).collect(Collectors.toList()); // metodo filtra uma lista com mesma cor
		for (Piece p : list) {
			if (p instanceof King) { // Se a peçã for uma instancia do King
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is not "+ color + "King on the Board");
	}
	// Test do check da cor
	private boolean testCheck (Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List <Piece> opponentPieces = pieceOntheBoard.stream().filter(x-> ((ChessPiece)x).getColor()==opponent(color)).collect(Collectors.toList()); // Lista peças do oponente recebe a cor do OPPNENT
		for (Piece p: opponentPieces) { // Verifico que as peças oponenter estão com o destino na posição da peça do rei
			boolean [][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {// for verdadeido retornar true
				return true; // test check verdadeida
			}
		}
		return false;
	
	}
	
	private boolean testCheckMate (Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = pieceOntheBoard.stream().filter(x-> ((ChessPiece)x).getColor()== color).collect(Collectors.toList()); // Pega todas peças tabu igual a cor
		for ( Piece p: list) {
			boolean[][] mat = p.possibleMoves();
			for (int i= 0 ; i<board.getRows(); i++) {
				for (int j =0; j < board.getColumns(); j++) {
					if (mat[i][j]) {// esse movimento é possivel true
						Position source = ((ChessPiece)p).getChessPosition().toPosition(); // Possição de origem donwcastin
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target); //fiz movimento da peça pra testa da origem e do destino, com testCheck
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece); // desfazer o movimento
						if (!testCheck) {  // sE NÃO ESTAVA EM CHECk, significa que o makeMove tirou o rei  do check
							return false; // Reio não está em check mate
						}
						
					}
				}
			}
		}
		return true; // está em checkMate
	}
	// Operação de colocar peça passando as oeperações cordenadas xadres
	private void placeNewPiece(char column, int row, ChessPiece pieces) {
		board.placePiece(pieces, new ChessPosition(column, row).toPosition());
		pieceOntheBoard.add(pieces);
	}

	// Passar as pocições do xadres
	private void initialSetup() {
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}

}
