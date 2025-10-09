import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordleGame game = new WordleGame();
        Scanner sc = new Scanner(System.in);

        System.out.println("Mini-Wordle Time!");
        System.out.println("Guess the 5-letter word, you only have 6 tries, be smart.\n");

        while (!game.isGameOver()) {
            System.out.print("Enter your guess: ");
            String guess = sc.nextLine().trim();

            try {
                Feedback fb = game.makeGuess(guess);
                System.out.println(fb);
                if (fb.isCorrect()) {
                    System.out.println("Hooray! You got it!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong " + e.getMessage());
            }
        }

        System.out.println("Game over! The secret word was: " + game.getSecretWord());
    }
}
