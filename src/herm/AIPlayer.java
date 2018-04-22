package herm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AIPlayer extends Player {

    private static ArrayList<String> nameOptions = new ArrayList<>(Arrays.asList(
            "Phineas",
            "Ferb",
            "Dr. Doof",
            "Candace",
            "Perry",
            "Baljeet",
            "Carl",
            "Isabella",
            "Buford",
            "Jeremy",
            "Stacy",
            "Vanessa"
    ));

    private static ArrayList<Suspect> suspectOptions = new ArrayList<>(Arrays.asList(Suspect.values()));


    public AIPlayer (ArrayList<Card> h)
    {
        super(AIPlayer.getNameOption(), AIPlayer.getSuspectOption(), h);
    }

    private static Suspect getSuspectOption()
    {
        return suspectOptions.remove(new Random().nextInt(suspectOptions.size()));
    }

    public static void removeSuspect(Suspect x)
    {
        suspectOptions.remove(x);
    }

    private static String getNameOption()
    {
        return nameOptions.remove(new Random().nextInt(nameOptions.size()));
    }

    public static void resetSuspectOptions()
    {
        suspectOptions = new ArrayList<>(Arrays.asList(Suspect.values()));
    }

    public static void resetNameOptions()
    {
        nameOptions = new ArrayList<>(Arrays.asList(
                "Phineas",
                "Ferb",
                "Dr. Doof",
                "Candace",
                "Perry",
                "Baljeet",
                "Carl",
                "Isabella",
                "Buford",
                "Jeremy",
                "Stacy",
                "Vanessa"
        ));
    }
}
