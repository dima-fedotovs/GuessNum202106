package com.company;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Leaderboard.load();

        var random = new Random();
        String userName;
        do {
            System.out.println("What is your name");
            userName = scanner.next();

            var myNum = random.nextInt(100) + 1;
            System.out.printf("Cheat: %d\n", myNum);

            var userLost = true;

            var t1 = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                var msg = String.format("Try #%d. Guess my number", i + 1);
                var userNum = askNumber(msg, 1, 100);
                System.out.printf("You entered %d\n", userNum);

                if (myNum > userNum) {
                    System.out.println("My number is greater than yours");
                } else if (myNum < userNum) {
                    System.out.println("My number is less than yours");
                } else {
                    var t2 = System.currentTimeMillis();
                    userLost = false;
                    System.out.println("You win!");
                    Leaderboard.add(userName, i + 1, t2 - t1);
                    break;
                }
            }
            if (userLost) {
                System.out.println("You lost, my friend!");
            }
            System.out.println("Do you want to repeat?");
        } while (scanner.next().equalsIgnoreCase("yes"));

        Leaderboard.print();
        Leaderboard.save();

        System.out.printf("Good bye, %s!", userName);
    }

    public static int askNumber(String message, int min, int max) {
        while (true) {
            try {
                System.out.println(message + ":");
                int answer = scanner.nextInt();
                if (answer < min) {
                    System.out.printf("Number should not be less than %d\n", min);
                } else if (answer > max) {
                    System.out.printf("Number should not be greater than %d\n", max);
                } else {
                    return answer;
                }
            } catch (InputMismatchException ex) {
                var str = scanner.next();
                System.out.printf("You entered %s. But number expected.\n", str);
            }
        }
    }

}

