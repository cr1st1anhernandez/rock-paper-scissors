package com.mycompany.app;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class Game {
    private String[] moves;
    private byte[] secretKey;
    private String computerMove;
    private String hmac;

    public Game(String[] moves) {
        this.moves = moves;
    }

    public void start() {
        try {
            generateSecretKey();
            computerMove = makeComputerMove();
            hmac = generateHMAC(computerMove, secretKey);
            System.out.println("HMAC: " + hmac);
            displayMenu();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter your move: ");
                String input = scanner.nextLine();

                if (input.equals("0")) {
                    System.out.println("Exiting...");
                    break;
                } else if (input.equals("?")) {
                    HelpTable helpTable = new HelpTable(moves);
                    helpTable.displayTable();
                } else {
                    try {
                        int userMoveIndex = Integer.parseInt(input) - 1;
                        if (userMoveIndex >= 0 && userMoveIndex < moves.length) {
                            String userMove = moves[userMoveIndex];
                            determineWinner(userMove, computerMove);
                            System.out.println("HMAC key: " + Base64.getEncoder().encodeToString(secretKey));
                            break;
                        } else {
                            System.out.println("Invalid move. Try again.");
                            displayMenu();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Try again.");
                        displayMenu();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateSecretKey() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        secretKey = new byte[32];
        random.nextBytes(secretKey);
    }

    private String makeComputerMove() {
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(moves.length);
        return moves[index];
    }

    private String generateHMAC(String message, byte[] key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
        mac.init(keySpec);
        byte[] hmacBytes = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    private void displayMenu() {
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + " - " + moves[i]);
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
    }

    private void determineWinner(String userMove, String computerMove) {
        int userIndex = findMoveIndex(userMove);
        int computerIndex = findMoveIndex(computerMove);
        int half = moves.length / 2;

        if (userIndex == computerIndex) {
            System.out.println("Draw!");
        } else if ((userIndex > computerIndex && userIndex <= computerIndex + half) ||
                (userIndex < computerIndex && userIndex + moves.length <= computerIndex + half)) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }

        System.out.println("Your move: " + userMove);
        System.out.println("Computer move: " + computerMove);
    }

    private int findMoveIndex(String move) {
        for (int i = 0; i < moves.length; i++) {
            if (moves[i].equals(move)) {
                return i;
            }
        }
        return -1;
    }
}
