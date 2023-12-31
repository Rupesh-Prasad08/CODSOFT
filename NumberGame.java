import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int attemptsLimit = 10;

        System.out.println("Welcome to the Number Game!");
        System.out.println("Guess the number between " + lowerBound + " and " + upperBound);

        int score = 0;

        while (true) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("New round! You have " + attemptsLimit + " attempts.");

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == generatedNumber) {
                    guessedCorrectly = true;
                    score++;
                    System.out.println("Congratulations! You guessed the correct number!");
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attempts++;
                System.out.println("Attempts left: " + (attemptsLimit - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've reached the maximum attempts. The correct number was: " + generatedNumber);
            }

            System.out.println("Your current score: " + score);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Your final score: " + score);
                break;
            }
        }

        scanner.close();
    }
}
