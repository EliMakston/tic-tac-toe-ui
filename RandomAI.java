import java.util.Random;

public class RandomAI {
    Board currentBoard;
    int playerID;
    RandomAI(Board currentPosition, int ID) {
        this.currentBoard = currentPosition;
        playerID = ID;
    }

    public Board makeMove() {
        Board[] possibleMoves = currentBoard.getAllPossibleMoves(playerID);
        if (possibleMoves.length == 0) {
            return currentBoard;
        }
        Random rng = new Random();
        int index = rng.nextInt(possibleMoves.length);
        Board newPosition = possibleMoves[index];
        currentBoard = newPosition;
        return newPosition;
    }
}
