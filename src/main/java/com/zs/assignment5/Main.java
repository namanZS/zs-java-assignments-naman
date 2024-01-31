package com.zs.assignment5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    static GitLogParser parser = new GitLogParser();
    public static void main(String[]args){
        try {
            Scanner sc = new Scanner(System.in);
            parser.parseGitLog("/Users/raramuri/Documents/Zopsmart/zs-java-assignments-naman/src/main/resources/git_history.txt");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Enter date in (2024-01-11) format");
            Date sinceDate = dateFormat.parse(sc.nextLine());
            List<Commit> commitsSinceDate = parser.getCommitsSinceDate(sinceDate);
            System.out.println("Commits since " + dateFormat.format(sinceDate) + ": " + commitsSinceDate+"\n");

            Map<String, Integer> commitCountByDeveloper = parser.getCountOfCommitsByDeveloper();
            System.out.println("Commit count by each developer: " + commitCountByDeveloper+"\n");

            Map<String, Map<String, Integer>> commitCountByDeveloperPerDay = parser.getCountOfCommitsByDeveloperPerDay();
            System.out.println("Commit count by each developer per day: " + commitCountByDeveloperPerDay+"\n");

            Set<String> developersWithNoCommits = parser.getDevelopersWithNoCommitsForTwoDays();
            System.out.println("Developers with no commits for two consecutive days: " + developersWithNoCommits+"\n");
        }
        catch (IOException | ParseException | GitLogParseException e) {
           logger.error(e);
        }


    }
}
