import java.util.Scanner;

public class Number {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxattempts = 10;

        System.out.println("");
        System.out.println("          The Number Guessing Game!    ");
        System.out.println("");
        System.out.println("Guess a number between 1 and 100.");
        System.out.println("You have a maximum of 10 attempts per round.");
        System.out.println("");

        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = (int) (Math.random() * 100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maxattempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else if (guess > targetNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                }

                if (attempts == maxattempts && !guessedCorrectly) {
                    System.out.println("Sorry, you've used all " + maxattempts + " attempts. The correct number was " + targetNumber + ".");
                }
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String res = scanner.next().trim().toLowerCase();
            playAgain = res.equals("yes");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
