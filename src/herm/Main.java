package herm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Hello! Welcome to Clue\n" +
                           "What is your name? ");
        String name = input.nextLine();

        System.out.println("Let's get going!");
        String response = "";
        while(!response.equalsIgnoreCase("N"))
        {
            Game myGame = new Game(name);
            myGame.play();

            System.out.print("Would you like to play again? ");
            response = input.nextLine();
        }



    }
}
