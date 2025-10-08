import java.util.*;

public class Dictionary {
	private List<String> words;
	
	public Dictionary() {
		words = new ArrayList<>();
		
		/**
		 * Some uppercase, some lowercase
		 */
		words.add("APPLE");
		words.add("brave");
		words.add("CIDER");
		words.add("delta");
		words.add("EAGLE");
		words.add("flint");
	}
	
	/**
	 * Returns random 5 letter word
	 * @bug not random, returns first always
	 */
	public String getRandomWord() {
		if (words.isEmpty()) {
			return null;
		} else {
			return words.get(0); //picks random value in array list
		}
	}
	
	/**
	 * Checks for word validity
	 * @bug case sensitive
	 */
	public boolean isValidWord(String guess) {
		return words.contains(guess);
	}
}