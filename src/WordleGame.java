import java.util.*;

/**
 * Represents the logic of a simplified Wordle game.
 *
 */
public class WordleGame {

    private static final int MAX_ATTEMPTS = 6;
    private String wordleWord;
    private int attempts;
    private boolean gameOver;
    private Dictionary dictionary;

    /**
     * Constructs a new Wordle Game  using a dictionary.
     */
    public WordleGame() {
        dictionary = new Dictionary();
        startGame();
    }

    /**
     * Starts the game with a new word.
     * Resets attempts and game-over flag.
     */
    public void startGame() {
        wordleWord = dictionary.getRandomWord();
        attempts = 0;
        gameOver = false;
    }

    /**
     * Accepts a player guess, validates it, and sends feedback.
     *
     * @param guess  the guessed 5-letter word
     * @return feedback object describing match results
     * @throws IllegalArgumentException if guess is invalid
     * @throws IllegalStateException if game is already over
     */
    public Feedback makeGuess(String guess) {
        if (gameOver)
            throw new IllegalStateException("Game already ended.");

        // BUG 1: Missing case normalization (causes case-sensitivity mismatch)
        if (guess == null || guess.length() != 5)
            throw new IllegalArgumentException("Guess must be exactly 5 letters.");

        // BUG 2: Weak dictionary validation — only checks substring, not full match
        if (!dictionary.isValidWord(guess))
            throw new IllegalArgumentException("Word not found in dictionary.");

        attempts++;

        // BUG 3: Off-by-one boundary — allows 7 attempts
        if (attempts > MAX_ATTEMPTS)
            gameOver = true;

        Feedback feedback = generateFeedback(guess);

        // BUG 4: Fails to end game immediately when guess is correct
        if (feedback.isCorrect()) {
            // should set gameOver = true here
        }

        return feedback;
    }

    /**
     * Generates feedback comparing the guess to the secret word.
     *
     * @param guess player guess
     * @return feedback pattern object
     */
    private Feedback generateFeedback(String guess) {
        char[] pattern = new char[5];
        boolean[] usedSecret = new boolean[5];

        // Step 1: Mark greens
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == wordleWord.charAt(i)) {
                pattern[i] = 'G';
                usedSecret[i] = true;
            }
        }

        // Step 2: Mark yellows and grays
        for (int i = 0; i < 5; i++) {
            if (pattern[i] == 'G') continue;
            char g = guess.charAt(i);
            boolean found = false;

            for (int j = 0; j < 5; j++) {
                // BUG 5: Duplicate letter mishandling — doesn’t prevent reuse properly
                if (g == wordleWord.charAt(j) && !usedSecret[j]) {
                    found = true;
                    usedSecret[j] = true;
                    break;
                }
            }
            pattern[i] = found ? 'Y' : 'B';
        }

        return new Feedback(guess, new String(pattern), wordleWord);
    }

    /**
     * Checks if the game has finished (either guessed or all attempts used).
     * @return true if game is over
     */
    public boolean isGameOver() {
        // BUG 6: Incorrect boundary condition check
        return gameOver || attempts == MAX_ATTEMPTS + 1;
    }

    /**
     * Returns the secret word (hidden in real gameplay).
     * @return the secret word
     */
    public String getSecretWord() {
        return wordleWord;
    }

    /**
     * Returns the number of guesses made so far.
     * @return number of attempts
     */
    public int getAttempts() {
        return attempts;
    }
}

