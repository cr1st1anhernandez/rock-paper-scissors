package com.mycompany.app;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3 || args.length % 2 == 0) {
            System.out.println("Error: Invalid number of arguments. Please provide an odd number of at least 3 non-repeating strings.");
            System.out.println("Example: java Main rock paper scissors");
            return;
        }

        Set<String> uniqueMoves = new HashSet<>();
        for (String move : args) {
            if (!uniqueMoves.add(move)) {
                System.out.println("Error: Duplicate moves are not allowed. Please provide unique moves.");
                System.out.println("Example: java Main rock paper scissors");
                return;
            }
        }

        Game game = new Game(args);
        game.start();
    }
}
