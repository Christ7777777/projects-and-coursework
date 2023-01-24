package hw2;

/**
 * Model of an obscure game called "Corkball" or sometimes "Fuzzball", generally
 * played in the St Louis area.  It is vaguely similar to baseball, except that
 * it is much simpler since there are no actual bases and the runners are imaginary.
 * See the assignment pdf for further explanation.
 *
 * @author Varun Advani
 *
 */
public class Corkball {
    /**
     * Number of strikes causing a player to be out.
     */
    public static final int MAX_STRIKES = 2;

    /**
     * Number of balls causing a player to walk.
     */
    public static final int MAX_BALLS = 5;

    /**
     * Number of outs before the teams switch.
     */
    public static final int MAX_OUTS = 3;
    /**
     * Current number of Innings
     */
    private int currentInning;
    /**
     * Current number of strikes
     */
    private int currentStrikes;
    /**
     * Current number of outs
     */
    private int currentOuts;
    /**
     * Current number of balls declared by the umpire
     */
    private int numBalls;
    /**
     * Current score of team 0
     */
    private int teamScore_0;
    /**
     * Current score of team 1
     */
    private int teamScore_1;
    /**
     * Checks if it is top(team 0) or bottom(team 1)
     */
    private boolean whichHalf;
    /**
     * Checks if the game has ended or not
     */
    private boolean gameState;
    /**
     * Maximum number of innings that can be played
     */
    private final int maxInnings;
    /**
     * Checks if runner is on base 1
     */
    private boolean base1;
    /**
     * Checks if runner is on base 2
     */
    private boolean base2;
    /**
     * Checks if runner is on base 3
     */
    private boolean base3;

    // TODO: EVERYTHING ELSE

    // Note that this code will not compile until you have put in stubs for all
    // the required methods.



    // The methods below are provided for you and you should not modify them.
    // The compile errors will go away after you have written stubs for the
    // rest of the API methods.
    /**
     * Returns a three-character string representing the players on base, in the
     * order first, second, and third, where 'X' indicates a player is present and
     * 'o' indicates no player. For example, the string "XXo" means that there are
     * players on first and second but not on third.
     *
     * @return three-character string showing players on base
     * Constructor for the class Corkball that initializes the instance variables
     */
    public Corkball(int givenNumInnings) {
        currentInning = 1;
        currentStrikes = 0;
        currentOuts = 0;
        numBalls = 0;
        teamScore_0 = 0;
        teamScore_1 = 0;
        whichHalf = true;
        gameState = false;
        maxInnings = givenNumInnings;
        base1 = false;
        base2 = false;
        base3 = false;
    }
    /**
     * Helper method invoked when a batter is out
     */
    private void newBatter() {
        currentStrikes = 0;
        numBalls = 0;
    }
    /**
     * Helper method invoked to switch from Team 0 to Team 1 and vice versa
     * Updates the current number of innings and resets the bases
     */
    private void switchTeams() {
        if (whichHalf) {
            whichHalf = false;
            currentOuts = 0;

        } else {
            whichHalf = true;
            currentOuts = 0;
            currentInning += 1;
        }
        base1 = false;
        base2 = false;
        base3 = false;
    }
    /**
     * Helper method invoked to switch the base of a player
     * Updates the score of team 0 and team 1 if player is on base 3
     * @param nextPlayer checks if a new person comes in after players have shifted bases
     */
    private void nextBase(boolean nextPlayer) {
        if (base3) {
            updateScore(1);
            newBatter(); 
        }
        if (nextPlayer) {
            base3 = base2;
            base2 = base1;
            base1 = true;
        } else {
            base3 = base2;
            base2 = base1;
            base1 = false;
        }
        numBalls = 0;
    }
    /**
     * Helper method that updates the score of team 0 and team 1
     * @param add adds 1 for each player that reaches the home base
     */
    private void updateScore(int add) {
        if (whichHalf) {
            teamScore_0 += add;
        } else {
            teamScore_1 += add;
        }
    }
    /**
     * Helper method invoked for a player to walk
     * Shifts bases only if the the base is not covered
     */
    private void walk() {
        if (!base1) {
            base1 = true;
        } else {
            if (!base2) {
                base2 = true;
            } else {
                base3 = true;
                updateScore(1);
                newBatter(); 
            }
        }
    }
    /**
     * @return whether the game has ended or not
     */
    public boolean gameEnded() {
        return gameState;
    }

    /**
     * @return if a player is on the base
     * @param which is the number of the base
     */
    public boolean runnerOnBase(int which) {
        if (which == 1) {
            return base1;
        }
        if (which == 2) {
            return base2;
        }
        if (which == 3) {
            return base3;
        }
        return false;
    }
    /**
     * @return which Inning is being played currently
     */
    public int whichInning() {
        return currentInning;
    }
    /**
     * @return if top(team 0) or bottom(team 1) is playing
     */
    public boolean isTopOfInning() {
        return whichHalf;
    }
    /**
     * @return current number of outs for the playing team
     */
    public int getCurrentOuts() {
        return currentOuts;
    }
    /**
     * @return current number of strikes for the player that is playing
     */
    public int getCalledStrikes() {
        return currentStrikes;
    }
    /**
     * @return current number of balls thrown by the pitcher
     */
    public int getBallCount() {
        return numBalls;
    }
    /**
     * @return score of team 0
     */
    public int getTeam0Score() {
        return teamScore_0;
    }
    /**
     *
     * @return score of team 1
     */
    public int getTeam1Score() {
        return teamScore_1;
    }
    /**
     * method called when there is a strike
     * updates the out based on the strikes
     * switches teams based on the outs
     * ends the game after the maximum number of innings
     * @param swung checks if the player swung at the ball or not
     */
    public void strike(boolean swung) {
        if (!gameState) {
            if (!swung) {
                currentStrikes += 1;
                if (currentStrikes == MAX_STRIKES) {
                    currentOuts += 1;
                    newBatter();
                    if (currentOuts == MAX_OUTS) {
                        switchTeams();
                    }
                }
            }
            if (swung) {
                currentOuts += 1;
                newBatter();
                if (currentOuts == MAX_OUTS) {
                    switchTeams();
                }
            }
        }
         if (currentInning == (maxInnings + 1)) {
            gameState = true;
         }
        }
    
    /**
     * 	method called when the hit ball is caught
     * calls a new batter when someone is out
     * switches team after max number of outs
     * ends the game after the maximum number of innings
     */
    public void caughtFly() {
        if (!gameState) {
            currentOuts += 1;
            newBatter();
            if (currentOuts == MAX_OUTS) {
                switchTeams();
            }
        }
        if (currentInning == (maxInnings + 1)) {
            gameState = true;
        }
    }
    /**
     * method called when ball is inaccurately thrown by the pitcher
     * gives the player a walk after max number of balls have been thrown
     * calls a new batter after the walk
     */
    public void ball() {
        if (!gameState) {
            numBalls += 1;
            if (numBalls == MAX_BALLS) {
                walk();
                newBatter();

            }
        }
    }
    /**
     * method called when the player hits the ball a certain distance
     * updates the bases if it is a single, double, triple, or home run
     * resets number of balls to zero and updates the scores for team 0 and team 1
     * @param distance of the hit made by the player
     */
    public void hit(int distance) {
        if (!gameState) {
            if (distance < 15) {
                currentOuts += 1;
                newBatter();
                if (currentOuts == MAX_OUTS) {
                    switchTeams();
                }
            } else if (distance < 150) {
                nextBase(true);

            } else if (distance < 200) {
                nextBase(true);
                nextBase(false);

            } else if (distance < 250) {
                nextBase(true);
                nextBase(false);
                nextBase(false);

            } else {

                if (base1 == base2 == base3) {
                    updateScore(4);
                } else {
                    updateScore(1);
                }
            }
        }
    }

    public String getBases() {
        return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o") +
                (runnerOnBase(3) ? "X" : "o");
    }

    /**
     * Returns a one-line string representation of the current game state. The
     * format is:
     *
     * <pre>
     *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
     * </pre>
     *
     * The first three characters represent the players on base as returned by the
     * <code>getBases()</code> method. The 'T' after the inning number indicates
     * it's the top of the inning, and a 'B' would indicate the bottom. The score always
     * shows team 0 first.
     *
     * @return one-line string representation of the game state
     */
    public String toString() {
        String bases = getBases();
        String topOrBottom = (isTopOfInning() ? "T" : "B");
        String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
        return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(),
                getTeam1Score(), getBallCount(), getCalledStrikes(), getCurrentOuts());
    }


}
