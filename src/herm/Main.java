package herm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String response = "";
        while(!response.equalsIgnoreCase("N"))
        {
            System.out.print("Hello! Welcome to Clue\n" +
                    "What is your name? ");
            String name = "Bryce"; //input.nextLine();

            System.out.println("Who would you like to play as?");
            int counter = 1;
            for (Suspect x: Suspect.values())
            {
                System.out.printf("%d) %s\n", counter, x);
                counter++;
            }
            System.out.print("> ");
            Suspect suspectSelection = Suspect.COL_MUSTARD; // Suspect.values()[input.nextInt()-1];
            //input.nextLine();

            System.out.println("\nLet's get going!\n");

            Game myGame = new Game(name, suspectSelection);
            myGame.play();

            System.out.print("Would you like to play again? ");
            response = input.nextLine();
            System.out.println("\n\n");
        }



    }
}
