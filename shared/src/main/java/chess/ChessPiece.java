package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor color;
    private final PieceType type;
    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
    this.color = pieceColor;
    this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    private ChessPosition ReturnValidPosition(ChessPosition newPosition, ChessPiece foundPiece, ChessGame.TeamColor color){
        if(foundPiece != null && foundPiece.getTeamColor() != color){
            System.out.println("FOUND PIECE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
            return  newPosition;
        } else if (foundPiece == null && newPosition.checkInBound()) {
            System.out.println("EMPTY SPACE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());

            return newPosition;
        }
        else return null;
    }

    private Collection<ChessMove> PawnMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color){
        Collection<ChessMove> pawnMoves = new ArrayList<>();
        ChessPosition forwardOne = null;
        ChessPosition forwardTwo = null;
        ChessPosition leftDiagonal = null;
        ChessPosition rightDiagonal = null;
        ChessPiece foundPieceForwardOne;
        ChessPiece foundPieceForwardTwo;
        ChessPiece leftDiagonalFoundPiece;
        ChessPiece rightDiagonalFoundPiece;


        if (color == ChessGame.TeamColor.WHITE){
            forwardOne = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn());
            forwardTwo = new ChessPosition(myPosition.getRow()+2, myPosition.getColumn());
            leftDiagonal = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1);
            rightDiagonal = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1);
            if(myPosition.getRow()==2){
                //check forward two
                foundPieceForwardTwo = board.getPiece(forwardTwo);
                foundPieceForwardOne = board.getPiece(forwardOne);
                if(foundPieceForwardTwo == null && foundPieceForwardOne == null && forwardTwo.checkInBound()){
                    pawnMoves.add(new ChessMove(myPosition, forwardTwo, null));
                    System.out.println("adding forward 2");
                }
            }
            if(forwardOne.getRow() == 8){
                //forward null
                foundPieceForwardOne = board.getPiece(forwardOne);
                leftDiagonalFoundPiece = board.getPiece(leftDiagonal);
                rightDiagonalFoundPiece = board.getPiece(rightDiagonal);
                if(foundPieceForwardOne == null && forwardOne.checkInBound()){
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                if(leftDiagonalFoundPiece != null && leftDiagonal.checkInBound() && leftDiagonalFoundPiece.getTeamColor() != color){
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                if(rightDiagonalFoundPiece != null && rightDiagonal.checkInBound() && rightDiagonalFoundPiece.getTeamColor() != color){
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                return pawnMoves;
                //diagonal valid
            }
        }

        if (color == ChessGame.TeamColor.BLACK){
            forwardOne = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            forwardTwo = new ChessPosition(myPosition.getRow()-2, myPosition.getColumn());
            leftDiagonal = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1);
            rightDiagonal = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);
            if(myPosition.getRow()==7) {
                //check forward two
                foundPieceForwardTwo = board.getPiece(forwardTwo);
                foundPieceForwardOne = board.getPiece(forwardOne);
                if (foundPieceForwardTwo == null && foundPieceForwardOne == null && forwardTwo.checkInBound()) {
                    pawnMoves.add(new ChessMove(myPosition, forwardTwo, null));
                    System.out.println("adding forward two");

                }
            }
            if(forwardOne.getRow() == 1) {
                //forward null
                foundPieceForwardOne = board.getPiece(forwardOne);
                leftDiagonalFoundPiece = board.getPiece(leftDiagonal);
                rightDiagonalFoundPiece = board.getPiece(rightDiagonal);
                if (foundPieceForwardOne == null && forwardOne.checkInBound()) {
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, forwardOne, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                if (leftDiagonalFoundPiece != null && leftDiagonal.checkInBound() && leftDiagonalFoundPiece.getTeamColor() != color) {
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, leftDiagonal, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                if (rightDiagonalFoundPiece != null && rightDiagonal.checkInBound() && rightDiagonalFoundPiece.getTeamColor() != color) {
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.QUEEN));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.ROOK));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.KNIGHT));
                    pawnMoves.add(new ChessMove(myPosition, rightDiagonal, PieceType.BISHOP));
                    System.out.println("adding forward one promotion");
                }
                return pawnMoves;
            }
        }

        foundPieceForwardOne = board.getPiece(forwardOne);
        leftDiagonalFoundPiece = board.getPiece(leftDiagonal);
        rightDiagonalFoundPiece = board.getPiece(rightDiagonal);

        if(foundPieceForwardOne == null && forwardOne.checkInBound()){
            pawnMoves.add(new ChessMove(myPosition, forwardOne, null));
            System.out.println("adding forward one hehe");

        }
        if(leftDiagonalFoundPiece != null && leftDiagonal.checkInBound() && leftDiagonalFoundPiece.color != color){
            pawnMoves.add(new ChessMove(myPosition, leftDiagonal, null));
            System.out.println("adding left diagonal");

        }
        if(rightDiagonalFoundPiece != null && rightDiagonal.checkInBound() && rightDiagonalFoundPiece.color != color){
            pawnMoves.add(new ChessMove(myPosition, rightDiagonal, null));
            System.out.println("adding right diagonal");

        }

        return pawnMoves;
    }

    private Collection<ChessMove> RookMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color){
        Collection<ChessMove> rookMoves = new ArrayList<>();

        //up
        for (int r = myPosition.getRow(); r <= 8; r++) {
            if(r == myPosition.getRow()){
                continue;
            }

                ChessPosition upOne = new ChessPosition(r, myPosition.getColumn());
            ChessPiece foundPieceUpOne = board.getPiece(upOne);
            if(foundPieceUpOne != null && foundPieceUpOne.getTeamColor() != color){
                rookMoves.add(new ChessMove(myPosition, upOne, null));
                System.out.println( upOne.getRow() + ", " + upOne.getColumn());
                break;
            } else if (foundPieceUpOne != null && foundPieceUpOne.getTeamColor() == color) {
                break;
            }
            if(foundPieceUpOne == null && upOne.checkInBound()){
                rookMoves.add(new ChessMove(myPosition, upOne, null));
                System.out.println( upOne.getRow() + ", " + upOne.getColumn());
            }
        }
            //down
        for (int r = myPosition.getRow(); r >=1; r--) {
            if(r == myPosition.getRow()){
                continue;
            }

            ChessPosition downOne = new ChessPosition(r, myPosition.getColumn());
            ChessPiece foundPieceDownOne = board.getPiece(downOne);
            if(foundPieceDownOne != null && foundPieceDownOne.getTeamColor() != color){
                rookMoves.add(new ChessMove(myPosition, downOne, null));
                System.out.println( downOne.getRow() + ", " + downOne.getColumn());

                break;
            } else if (foundPieceDownOne != null && foundPieceDownOne.getTeamColor() == color) {
                break;
            }
            if(foundPieceDownOne == null && downOne.checkInBound()){
                rookMoves.add(new ChessMove(myPosition, downOne, null));
                System.out.println( downOne.getRow() + ", " + downOne.getColumn());

            }
        }
        //left
        for (int r = myPosition.getColumn(); r >=1; r--) {
            if(r == myPosition.getColumn()){
                continue;
            }

            ChessPosition leftOne = new ChessPosition(myPosition.getRow(), r);
            ChessPiece foundPieceLeftOne = board.getPiece(leftOne);
            if(foundPieceLeftOne != null && foundPieceLeftOne.getTeamColor() != color){
                rookMoves.add(new ChessMove(myPosition, leftOne, null));
                System.out.println( leftOne.getRow() + ", " + leftOne.getColumn());

                break;
            } else if (foundPieceLeftOne != null && foundPieceLeftOne.getTeamColor() == color) {
                break;
            }
            if(foundPieceLeftOne == null && leftOne.checkInBound()){
                rookMoves.add(new ChessMove(myPosition, leftOne, null));
                System.out.println( leftOne.getRow() + ", " + leftOne.getColumn());

            }
        }
        //right
        for (int r = myPosition.getColumn(); r <= 8; r++) {
            if(r == myPosition.getColumn()){
                continue;
            }

            ChessPosition rightOne = new ChessPosition(myPosition.getRow(), r);
            ChessPiece foundPieceRightOne = board.getPiece(rightOne);
            if(foundPieceRightOne != null && foundPieceRightOne.getTeamColor() != color){
                rookMoves.add(new ChessMove(myPosition, rightOne, null));
                System.out.println( rightOne.getRow() + ", " + rightOne.getColumn());

                break;
            } else if (foundPieceRightOne != null && foundPieceRightOne.getTeamColor() == color) {
                break;
            }
            if(foundPieceRightOne == null && rightOne.checkInBound()){
                rookMoves.add(new ChessMove(myPosition, rightOne, null));
                System.out.println( rightOne.getRow() + ", " + rightOne.getColumn());

            }
        }

        return rookMoves;
    }

    private Collection<ChessMove> KnightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color){
        Collection<ChessMove> knightMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ChessPosition newPosition = null;
            ChessPiece foundPiece = null;
            ChessPosition finalPosition;

            if(i == 0){
                newPosition = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() -1);
            }
            if(i == 1){
                newPosition = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() +1);
            }
            if(i == 2){
                newPosition = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() -1);
            }
            if(i == 3){
                newPosition = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() +1);
            }
            if(i == 4){
                newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() -2);
            }
            if(i == 5){
                newPosition = new ChessPosition(myPosition.getRow() -1 , myPosition.getColumn() -2);
            }
            if(i == 6){
                newPosition = new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() +2);
            }
            if(i == 7){
                newPosition = new ChessPosition(myPosition.getRow() +1, myPosition.getColumn() +2);
            }

            foundPiece = board.getPiece(newPosition);
            finalPosition = ReturnValidPosition(newPosition, foundPiece, color);
            if(finalPosition != null){
                knightMoves.add(new ChessMove(myPosition, finalPosition, null));
            }
        }


        //upRight

        //leftDown

        //leftUp
        return  knightMoves;
    }

    private Collection<ChessMove> BishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color){
        Collection<ChessMove> bishopMoves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            if(i==0){
                //upright
                ChessPosition newPosition = null;
                ChessPiece foundPiece = null;
                int col = myPosition.getColumn();
                for (int j = myPosition.getRow(); j <= 8; j++) {
                    if(myPosition.getRow() == j && myPosition.getColumn() == col){
                        col++;
                        continue;
                    }
                    newPosition = new ChessPosition(j,col);
                    foundPiece = board.getPiece(newPosition);
                    if(foundPiece != null && foundPiece.getTeamColor() != color){
                        System.out.println("FOUND PIECE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    } else if (foundPiece != null && foundPiece.getTeamColor() == color){
                        break;
                    }else if (foundPiece == null && newPosition.checkInBound()) {
                        System.out.println("EMPTY SPACE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        col++;
                    }
                }
            }
            if(i==1){
                //downright
                ChessPosition newPosition = null;
                ChessPiece foundPiece = null;
                int col = myPosition.getColumn();
                for (int j = myPosition.getRow(); j >= 1; j--) {
                    if(myPosition.getRow() == j && myPosition.getColumn() == col){
                        col++;
                        continue;
                    }
                    newPosition = new ChessPosition(j,col);
                    foundPiece = board.getPiece(newPosition);
                    if(foundPiece != null && foundPiece.getTeamColor() != color){
                        System.out.println("FOUND PIECE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    } else if (foundPiece != null && foundPiece.getTeamColor() == color){
                        break;
                    }else if (foundPiece == null && newPosition.checkInBound()) {
                        System.out.println("EMPTY SPACE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        col++;
                    }
                }
            }
            if(i==2){
                //upLeft
                ChessPosition newPosition = null;
                ChessPiece foundPiece = null;
                int col = myPosition.getColumn();
                for (int j = myPosition.getRow(); j <= 8; j++) {
                    if(myPosition.getRow() == j && myPosition.getColumn() == col){
                        col--;
                        continue;
                    }
                    newPosition = new ChessPosition(j,col);
                    foundPiece = board.getPiece(newPosition);
                    if(foundPiece != null && foundPiece.getTeamColor() != color){
                        System.out.println("FOUND PIECE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    } else if (foundPiece != null && foundPiece.getTeamColor() == color){
                        break;
                    }else if (foundPiece == null && newPosition.checkInBound()) {
                        System.out.println("EMPTY SPACE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        col--;
                    }
                }
            }
            if(i==3){
                //downRight
                ChessPosition newPosition = null;
                ChessPiece foundPiece = null;
                int col = myPosition.getColumn();
                for (int j = myPosition.getRow(); j >= 1; j--) {
                    if(myPosition.getRow() == j && myPosition.getColumn() == col){
                        col--;
                        continue;
                    }
                    newPosition = new ChessPosition(j,col);
                    foundPiece = board.getPiece(newPosition);
                    if(foundPiece != null && foundPiece.getTeamColor() != color){
                        System.out.println("FOUND PIECE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    } else if (foundPiece != null && foundPiece.getTeamColor() == color){
                        break;
                    }else if (foundPiece == null && newPosition.checkInBound()) {
                        System.out.println("EMPTY SPACE ADD: "+newPosition.getRow()+ ", " + newPosition.getColumn());
                        bishopMoves.add(new ChessMove(myPosition, newPosition, null));
                        col--;
                    }
                }
            }



        }
        return bishopMoves;
    }

    private Collection<ChessMove> KingMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color){
        Collection<ChessMove> kingMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ChessPosition newPosition = null;
            ChessPiece foundPiece = null;
            ChessPosition finalPosition;

            if(i == 0){
                newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            }
            if(i == 1){
                newPosition = new ChessPosition(myPosition.getRow() -1, myPosition.getColumn());
            }
            if(i == 2){
                newPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn() -1);
            }
            if(i == 3){
                newPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn() +1);
            }
            if(i == 4){
                newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() -1);
            }
            if(i == 5){
                newPosition = new ChessPosition(myPosition.getRow() +1 , myPosition.getColumn() +1);
            }
            if(i == 6){
                newPosition = new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() -1);
            }
            if(i == 7){
                newPosition = new ChessPosition(myPosition.getRow() -1, myPosition.getColumn() +1);
            }

            foundPiece = board.getPiece(newPosition);
            finalPosition = ReturnValidPosition(newPosition, foundPiece, color);
            if(finalPosition != null){
                kingMoves.add(new ChessMove(myPosition, finalPosition, null));
            }
        }

        return kingMoves;
    }


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new ArrayList<>();

        if(type == PieceType.PAWN && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(PawnMoves(board, myPosition, color));
        }
        if(type == PieceType.ROOK && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(RookMoves(board, myPosition, color));
        }
        if(type == PieceType.KNIGHT && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(KnightMoves(board, myPosition, color));
        }
        if(type == PieceType.BISHOP && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(BishopMoves(board, myPosition, color));
        }
        if(type == PieceType.QUEEN && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(BishopMoves(board, myPosition, color));
        }if(type == PieceType.QUEEN && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(RookMoves(board, myPosition, color));
        }
        if(type == PieceType.KING && color == ChessGame.TeamColor.WHITE){
            allMoves.addAll(KingMoves(board, myPosition, color));
        }

        if(type == PieceType.PAWN && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(PawnMoves(board, myPosition, color));
        }
        if(type == PieceType.ROOK && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(RookMoves(board, myPosition, color));
        }
        if(type == PieceType.KNIGHT && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(KnightMoves(board, myPosition, color));
        }
        if(type == PieceType.BISHOP && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(BishopMoves(board, myPosition, color));
        }
        if(type == PieceType.QUEEN && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(BishopMoves(board, myPosition, color));
        }if(type == PieceType.QUEEN && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(RookMoves(board, myPosition, color));
        }
        if(type == PieceType.KING && color == ChessGame.TeamColor.BLACK){
            allMoves.addAll(KingMoves(board, myPosition, color));
        }


        return allMoves;
    }
}
