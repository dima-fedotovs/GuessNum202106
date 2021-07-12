package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Leaderboard {
    static final ArrayList<GameResult> leaderboard = new ArrayList<>();
    static final File leaderboardFile = new File("leaderboard.txt");

    static void save() {
        try (var out = new PrintWriter(leaderboardFile)) {

            for (var r : leaderboard) {
                out.printf("%s %d %d\n", r.getName(), r.getAttempts(), r.getDuration());
            }

        } catch (IOException e) {
            System.out.println("Sorry, cannot save leaderboard");
        }
    }

    static void load() {
        if (!leaderboardFile.exists()) {
            System.out.println("You are first player!");
            return;
        }
        try (var in = new Scanner(leaderboardFile)) {

            while (in.hasNext()) {
                var result = new GameResult();
                result.setName(in.next());
                result.setAttempts(in.nextInt());
                result.setDuration(in.nextLong());
                leaderboard.add(result);
            }

        } catch (IOException e) {
            System.out.println("Sorry, cannot load leaderboard");
        }
    }

// Original variant
//    static void print() {
//        leaderboard.sort(
//                Comparator
//                        .comparingInt(GameResult::getAttempts)
//                        .thenComparingLong(GameResult::getDuration)
//        );
//        System.out.println("Our leaderboard");
//        for (var r : leaderboard) {
//            System.out.printf("%s %d %.1f\n",
//                    r.getName(),
//                    r.getAttempts(),
//                    r.getDuration() / 1000.0);
//        }
//    }

    // Variant 1
//    static void print() {
//        leaderboard.sort(
//                Comparator
//                        .comparingInt(GameResult::getAttempts)
//                        .thenComparingLong(GameResult::getDuration)
//        );
//        System.out.println("Our leaderboard");
//        int rows = 5;
//        for (var r : leaderboard) {
//            if (--rows < 0) {
//                break;
//            }
//            System.out.printf("%s %d %.1f\n",
//                    r.getName(),
//                    r.getAttempts(),
//                    r.getDuration() / 1000.0);
//        }
//    }

    // variant 2
//    static void print() {
//        leaderboard.sort(
//                Comparator
//                        .comparingInt(GameResult::getAttempts)
//                        .thenComparingLong(GameResult::getDuration)
//        );
//        System.out.println("Our leaderboard");
//        var sublist = leaderboard.subList(0, Math.min(5, leaderboard.size()));
//        for (var r : sublist) {
//            System.out.printf("%s %d %.1f\n",
//                    r.getName(),
//                    r.getAttempts(),
//                    r.getDuration() / 1000.0);
//        }
//    }

    // variant 3
//    static void print() {
//        leaderboard.sort(
//                Comparator
//                        .comparingInt(GameResult::getAttempts)
//                        .thenComparingLong(GameResult::getDuration)
//        );
//        System.out.println("Our leaderboard");
//        for (var i = 0; i < 5 && i < leaderboard.size(); i++) {
//            var r = leaderboard.get(i);
//            System.out.printf("%s %d %.1f\n",
//                    r.getName(),
//                    r.getAttempts(),
//                    r.getDuration() / 1000.0);
//        }
//    }

    // variant 4
    static void print() {
        System.out.println("Our leaderboard");
        leaderboard.stream()
                .sorted(Comparator
                        .comparingInt(GameResult::getAttempts)
                        .thenComparingLong(GameResult::getDuration))
                .limit(5)
                .forEach((GameResult r) -> System.out.printf("%s %d %.1f\n",
                        r.getName(),
                        r.getAttempts(),
                        r.getDuration() / 1000.0));

    }

    static void add(String userName, int attempt, long duration) {
        var result = new GameResult();
        result.setName(userName);
        result.setAttempts(attempt);
        result.setDuration(duration);
        leaderboard.add(result);
    }
}
