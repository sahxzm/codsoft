import java.util.Random;
import java.util.Scanner;

public class numberguessinggame {

    public static void main(String[] args) {

        Random number = new Random();
        Scanner scanner = new Scanner(System.in);

        int randomNumber = number.nextInt(100) + 1;

        System.out.println("Enter your number between 1 to 100:");

        int playerGuess = scanner.nextInt();

        if (playerGuess == randomNumber) {
            System.out.println(" wow :) you are correct");
        }
        else if (randomNumber > playerGuess) {
            System.out.println("sorry the no is higher try again");
        }
        else  {
            System.out.println("sorry the no is lower try again");
        }

        System.out.println("random no is: " +randomNumber);

    }
}
