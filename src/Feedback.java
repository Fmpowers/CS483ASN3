
public class Feedback {

    private final String guess;
    private final String pattern;
    private final String wordleWord;

    /**
     * @param guess guessed word
     * @param pattern string pattern
     * @param wordleWord secret word
     */
    public Feedback(String guess, String pattern, String wordleWord) {
        this.guess = guess;
        this.pattern = pattern;
        this.wordleWord = wordleWord;
    }

    /**
     * @return pattern string
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @return true if the guess is identical to the secret
     */
    public boolean isCorrect() {
        // BUG 9: Case-sensitive comparison
        return guess.equals(wordleWord);
    }

    /**
     * @return combined guess + pattern string
     */
    @Override
    public String toString() {

        return guess + pattern;
    }
}
