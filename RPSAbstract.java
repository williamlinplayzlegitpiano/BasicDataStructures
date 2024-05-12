import java.util.Random;

public abstract class RPSAbstract implements RPSInterface {
    protected static final int SEED = 12;
    protected final Random rand = new Random(SEED);

    // The moves allowed in this version of RPS
    protected String[] possibleMoves;

    // The number of games, player wins, CPU wins and ties
    protected int numGames;
    protected int numPlayerWins;
    protected int numCPUWins;
    protected int numTies;

    // The complete history of the moves
    protected String[] playerMoves;
    protected String[] cpuMoves;

    // The default moves.  Use for the basic implementation of the game.
    protected static final String[] DEFAULT_MOVES = {"rock", "paper",
            "scissors"};

    /* Important: Use the following constants to avoid output matching issues
       and magic numbers! */

    // Messages for the game.
    protected static final String INVALID_INPUT =
            "That is not a valid move. Please try again.";
    protected static final String PLAYER_WIN = "You win.";
    protected static final String CPU_WIN = "I win.";
    protected static final String TIE = "It's a tie.";
    protected static final String CPU_MOVE = "I chose %s. ";
    protected static final String THANKS =
            "Thanks for playing!\nOur most recent games were:";
    protected static final String PROMPT_MOVE =
            "Let's play! What's your move? (Type the move or q to quit)";

    protected static final String OVERALL_STATS =
            "Our overall stats are:\n" +
                    "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    protected static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";


    // Game outcome constants.
    protected static final int CPU_WIN_OUTCOME = 2;
    protected static final int PLAYER_WIN_OUTCOME = 1;
    protected static final int TIE_OUTCOME = 0;
    protected static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.
    protected static final int MAX_GAMES = 100;
    protected static final int MIN_POSSIBLE_MOVES = 3;
    protected static final int NUM_ROUNDS_TO_DISPLAY = 10;
    protected static final int PERCENTAGE_RESIZE = 100;
    protected static final String QUIT = "q";

    @Override
    public boolean isValidMove(String move) {
        // TODO
        // Use a loop here

        for (String possibleMove : possibleMoves) {
                if (possibleMove.equalsIgnoreCase(move)) {
                        return true;
                }
        }

        return false;  // dummy return value so code compiles.
    }


/**
 * Plays a round of the Rock-Paper-Scissors game between the player and the CPU.
 * Determines the winner based on the player's move and the CPU's move, records the moves made,
 * updates the game statistics accordingly, and prints appropriate messages indicating the CPU's move
 * and the outcome of the game.
 *
 * @param playerMove The move chosen by the player.
 * @param cpuMove    The move chosen by the CPU.
*/
    @Override
    public void playRPS(String playerMove, String cpuMove) {
                
        int outcome = determineWinner(playerMove, cpuMove);

        if (outcome != INVALID_INPUT_OUTCOME) {
                if (outcome == PLAYER_WIN_OUTCOME) {
                        numPlayerWins++;
                        System.out.printf(CPU_MOVE + PLAYER_WIN + "%n", cpuMove);
                } else if (outcome == CPU_WIN_OUTCOME) {
                        numCPUWins++;
                        System.out.printf(CPU_MOVE + CPU_WIN + "%n", cpuMove);
                } else if (outcome == TIE_OUTCOME) {
                        numTies++;
                        System.out.printf(CPU_MOVE + TIE + "%n", cpuMove);
                }
                numGames++;

                if (numGames <= MAX_GAMES) {
                        for (int i = 0; i < MAX_GAMES; i++) {
                            if (playerMoves[i] == null) {
                                playerMoves[i] = playerMove;
                                break;
                            }
                        }
                        for (int i = 0; i < MAX_GAMES; i++) {
                            if (cpuMoves[i] == null) {
                                cpuMoves[i] = cpuMove;
                                break;
                            }
                        }
                } else {
        
                }
        }

    }


    // The following methods have been already implemented. Do not change them.

    /**
     * Generates next cpu move
     *
     * @return representing the move, depending on random int
     */
    @Override
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    @Override
    public void displayStats() {
        float cpuWinPercent = (float) numCPUWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float) numPlayerWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float) numTies /
                (float) numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY)
                ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;

        // Print system and user moves
        for (int i = numGames - 1; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                    this.playerMoves[i]);
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
                tiedPercent);
    }
}
