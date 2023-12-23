import java.util.Random;

public class AI {
    Board currentBoard;
    int playerID;
    int movesConsidered;
    AI(Board currentPosition, int ID) {
        this.currentBoard = currentPosition;
        playerID = ID;
        movesConsidered = 0;
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
        movesConsidered++;
        System.out.printf("There were %d moves considered\n", movesConsidered);
        movesConsidered = 0;
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
            int currentEval = minimax(possibleMoves[i], 10, false);
            possibleMoves[i].printCurrentPosition();
            if (currentEval > bestEval) {
                bestEval = currentEval;
                bestMove = possibleMoves[i];
            }
            movesConsidered++;
        }
        System.out.printf("There were %d moves considered\n", movesConsidered);
        movesConsidered = 0;
        return bestMove;
    }

    private int minimax(Board currentMove, int depth, boolean maximizing) {
        if (depth == 0 || currentMove.didAnyoneWin() > -1) {
            return currentMove.didAnyoneWin();
        }

        if (maximizing) {
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(1);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = minimax(possibleMoves[i], depth - 1, false);
                maxEval = max(currentEval, maxEval);
                movesConsidered++;
            }
            return maxEval;
        }

        if (!maximizing) {
            int minEval = (int) Double.POSITIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(0);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = minimax(possibleMoves[i], depth - 1, true);
                minEval = min(currentEval, minEval);
                movesConsidered++;
            }
            return minEval;
        }
        
        return -1;
    }

    public int max(int num1, int num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }

    public int min(int num1, int num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
}

class AlphaBetaAI extends MiniMaxAI {
    public AlphaBetaAI(Board currentPosition, int ID) {
        super(currentPosition, ID);
    }

    public Board makeMove() {
        Board[] possibleMoves = currentBoard.getAllPossibleMoves(playerID);
        Board bestMove = new Board();
        int bestEval = (int) Double.NEGATIVE_INFINITY;
        for (int i = 0; i < possibleMoves.length; i++) {
            int currentEval = alphabeta(possibleMoves[i], possibleMoves.length,  (int) Double.NEGATIVE_INFINITY, (int) Double.POSITIVE_INFINITY, false);
            if (currentEval > bestEval) {
                System.out.println("Evaluation changed");
                bestEval = currentEval;
                bestMove = possibleMoves[i];
            }
            movesConsidered++;
        }
        System.out.printf("There were %d moves considered\n", movesConsidered);
        movesConsidered = 0;
        return bestMove;
    }

    private int alphabeta(Board currentMove, int depth, int alpha, int beta, boolean maximizing) {
        if (depth == 0 || currentMove.didAnyoneWin() > -1) {
            return currentMove.didAnyoneWin();
        }

        if (maximizing) {
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(1);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = alphabeta(possibleMoves[i], depth - 1, alpha, beta, false);
                maxEval = max(currentEval, maxEval);
                if (maxEval >  beta) {
                    break;
                }
                alpha = max(alpha, maxEval);
                movesConsidered++;
            }
            return maxEval;
        }

        if (!maximizing) {
            int minEval = (int) Double.POSITIVE_INFINITY;
            Board[] possibleMoves = currentMove.getAllPossibleMoves(0);
            for (int i = 0; i < possibleMoves.length; i++) {
                int currentEval = alphabeta(possibleMoves[i], depth - 1, alpha, beta, true);
                minEval = min(currentEval, minEval);
                if (minEval < alpha) {
                    break;
                }
                beta = min(beta, minEval);
                movesConsidered++;
            }
            return minEval;
        }
        
        return -1;
    }
}