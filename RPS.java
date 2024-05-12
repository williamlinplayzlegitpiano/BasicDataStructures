/**
 * Author: William Lin
 * Email: xil211@ucsd.edu
 * PID: A17503383
 * Sources Used: Java Interface Documentation
 * 
 * This file is for CSE 12 PA1 in Spring 2024 and contains the public class RPS that extends RPSAbstract. In this class, we have the `main` method 
 * and the `determinewinner` method for the main skeleton of the game and winner-determining algorithm, respectively.
 * 
*/

import java.util.Scanner;

/**
  the RPS class serves as the main class for the game implementation. The purpose is to orchestrate the gameplay by interacting with the user,
  generating CPU moves, and managing game statistics. 
*/
public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * The purpose of the main class is to be the backbone of the game. It takes player and CPU responses and direct them through 
     * various methods for the desired result.
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While loop allows the RPS game to be continuously played until the player chooses to end the game.
        while (true) {
            System.out.println(PROMPT_MOVE);
            String playerMove = in.nextLine();

            // condition to end the game by breaking the while loop
            if (playerMove.equalsIgnoreCase("q")) {
                break;
            }


            // resets the while loop if the move is not valid
            if (!game.isValidMove(playerMove)) {
                System.out.println(INVALID_INPUT);
                continue;
            }

            // calls the method genCPUMove for a randomized CPU move
            String cpuMove = game.genCPUMove();

            // takes the playerMove and the cpuMove as parameters for the playRPS method 
            game.playRPS(playerMove, cpuMove);
    
        }

        // calls the displayStats method after q is pressed
        game.displayStats();

        // closes the main method
        in.close();
    }

    @Override
    public int determineWinner(String playerMove, String cpuMove) {

        //set dummy variables
        int playerIndex = -1;
        int cpuIndex = -1;

        //set a numerical value for player and cpu moves 
        for (int i = 0; i < possibleMoves.length; i++) {
            if (possibleMoves[i].equalsIgnoreCase(playerMove)) {
                playerIndex = i;
            }
            if (possibleMoves[i].equalsIgnoreCase(cpuMove)) {
                cpuIndex = i;
            }
        }

        /*check the success of the numerical conversion 
        * if playerIndex or cpuIndex is -1, then the move does not exist
        */
        if (playerIndex == -1 || cpuIndex == -1) {
            System.out.println(INVALID_INPUT);
            return INVALID_INPUT_OUTCOME;
        }

        /*check the outcome by comparing the numerical 
        * values of the player and cpu moves
        */
        if (playerIndex == cpuIndex + 1 || (playerIndex == 0 && cpuIndex == possibleMoves.length - 1)) {
            return PLAYER_WIN_OUTCOME;
        } else if (cpuIndex == playerIndex + 1 || (cpuIndex == 0 && playerIndex == possibleMoves.length - 1)) {
            return CPU_WIN_OUTCOME;
        } else {
            return TIE_OUTCOME;
        }

    }
}
