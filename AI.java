import java.util.Random;

public class AI {
    Board currentBoard;
    int playerID;
    AI(Board currentPosition, int ID) {
        this.currentBoard = currentPosition;
        playerID = ID;
    }

    public Board makeMove() {
        return this.currentBoard;
    }
}

class RandomAI extends AI {
    public RandomAI(Board currentPosition, int ID) {
        super(currentPosition, ID);
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

class MiniMaxAI extends AI {
    public MiniMaxAI(Board currentPosition, int ID) {
        super(currentPosition, ID);
    }

    public Board makeMove() {
        Board[] possibleMoves = currentBoard.getAllPossibleMoves(playerID);
        Board bestMove = new Board();
        int bestEval = (int) Double.NEGATIVE_INFINITY;
        for (int i = 0; i < possibleMoves.length; i++) {
            int currentEval = minimax(possibleMoves[i], possibleMoves.length, false);
            if (currentEval > bestEval) {
                System.out.println("Evaluation changed");
                bestEval = currentEval;
                bestMove = possibleMoves[i];
            }
        }
        return bestMove;
    }

    private int minimax(Board currentMove, int depth, boolean maximizing) {
        if (depth == 0 || currentMove.didAnyoneWin() > -1) {
            return currentMove.didAnyoneWin();
        }

        if (maximizing) {
            double maxEval = Double.NEGATIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(1);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = minimax(possibleMoves[i], depth - 1, false);
                maxEval = max((double) currentEval, maxEval);
            }
            return (int) maxEval;
        }

        if (!maximizing) {
            double minEval = Double.POSITIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(0);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = minimax(possibleMoves[i], depth - 1, true);
                minEval = min((double) currentEval, minEval);
            }
            return (int) minEval;
        }
        
        return 2;
    }

    private double max(double num1, double num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }

    private double min(double num1, double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
}