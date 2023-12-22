public class Board {
    Piece[][] currentPosition;
    int playerTurn = 0;
    boolean gameOver = false;

    Board() {
        currentPosition =  new Piece[3][3];
        for (int i = 0; i < currentPosition.length; i++) {
            for (int z = 0; z < currentPosition[i].length; z++) {
                currentPosition[i][z] = new Piece();
            }
        }
    }

    Board(Piece[][] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void printCurrentPosition() {
        for (int i = 0; i < currentPosition.length; i++) {
            for (int z = 0; z < currentPosition[i].length; z++) {
                System.out.print(currentPosition[i][z].ID);
            }
            System.out.print("\n");
        }
    }

    public Board[] getAllPossibleMoves(int playerID) {
        int possibleMoveNumber = 0;
        for (int i = 0; i < currentPosition.length; i++) {
            for (int z = 0; z < currentPosition[i].length; z++) {
                if (currentPosition[i][z].ID == -1) {
                    possibleMoveNumber++;
                }
            }
        }
        Board[] possibleMoves = new Board[possibleMoveNumber];
        int boardIndex = 0;
        for (int i = 0; i < this.currentPosition.length; i++) {
            for (int z = 0; z < this.currentPosition[i].length; z++) {
                if (this.currentPosition[i][z].ID == -1) {
                    Board boardCopy = this.copy();
                    boardCopy.currentPosition[i][z] = new Piece(playerID);
                    possibleMoves[boardIndex] = boardCopy;
                    boardIndex++;
                }
            }
        }
        return possibleMoves;
    }

    public void makeMove(Board newBoard) {
        this.currentPosition = newBoard.currentPosition;
    }

    public Board copy() {
        Piece[][] positionClone = new Piece[3][3];
        for (int i = 0; i < currentPosition.length; i++) {
            positionClone[i] = currentPosition[i].clone();
        }
        return new Board(positionClone);
    }

    public int didAnyoneWin() {
        for (int i = 0; i < currentPosition.length; i++) {
            if (currentPosition[i][0].ID == currentPosition[i][1].ID && currentPosition[i][1].ID == currentPosition[i][2].ID && currentPosition[i][0].ID != -1) {
                return currentPosition[i][0].ID;
            }
        }
        for (int i = 0; i < currentPosition.length; i++) {
            if (currentPosition[0][i].ID == currentPosition[1][i].ID && currentPosition[1][i].ID == currentPosition[2][i].ID && currentPosition[0][i].ID != -1) {
                return currentPosition[0][i].ID;
            }
        }
        if (currentPosition[0][0].ID == currentPosition[1][1].ID && currentPosition[1][1].ID == currentPosition[2][2].ID && currentPosition[0][0].ID != -1) {
            return currentPosition[0][0].ID;
        }
        if (currentPosition[0][2].ID == currentPosition[1][1].ID && currentPosition[1][1].ID == currentPosition[2][0].ID && currentPosition[0][2].ID != -1) {
            return currentPosition[0][2].ID;
        }
        int negativeCount = 0;
        for (int i = 0; i < currentPosition.length; i++) {
            for (int z = 0; z < currentPosition[i].length; z++) {
                if (currentPosition[i][z].ID == -1) {
                    negativeCount++;
                }
            }
        }
        if (negativeCount == 0) {
            return 2;
        }
        return -1;
    }
}
