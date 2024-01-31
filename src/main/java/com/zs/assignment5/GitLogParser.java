package com.zs.assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Parses a Git log file and provides various statistics related to commits and developers.
 */
public class GitLogParser {

    private final List<Commit> commits;

    /**
     * Initializes a new instance of the GitLogParser class.
     */
    public GitLogParser() {
        this.commits = new ArrayList<>();
    }

    /**
     * Parses a Git log file and populates the list of commits.
     *
     * @param filePath The path to the Git log file.
     * @throws IOException          If an I/O error occurs while reading the file.
     * @throws GitLogParseException If there is an error parsing the Git log file.
     */
    public void parseGitLog(String filePath) throws IOException, GitLogParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                try {
                    Commit commit = parseCommitLine(line);
                    commits.add(commit);
                } catch (ParseException e) {
                    System.err.println("Error parsing Git log line at line " + lineNumber + ": " + line);
                    throw new GitLogParseException("Error parsing Git log line: " + line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new GitLogParseException("Error in reading file");
        }
    }

    /**
     * Parses a single line from the Git log file and creates a Commit object.
     *
     * @param line The line to parse.
     * @return A Commit object representing the parsed information.
     * @throws ParseException If there is an error parsing the commit line.
     */
    private Commit parseCommitLine(String line) throws ParseException {
        String[] parts = line.split(", ", 4);
        if (parts.length != 4) {
            throw new ParseException("Invalid number of fields in Git log line", 0);
        }

        String hash = parts[0];
        String message = parts[1];
        String author = parts[3];
        long timestamp = Long.parseLong(parts[2]);

        return new Commit(hash, message, author, timestamp);
    }

    /**
     * Gets a list of commits made since a specified date.
     *
     * @param date The date to compare against.
     * @return A list of commits made since the specified date.
     */
    public List<Commit> getCommitsSinceDate(Date date) {
        List<Commit> filteredCommits = new ArrayList<>();
        System.out.println("Provided Date: " + date);

        for (Commit commit : commits) {
            if (commit.getTimestamp() >= date.getTime()) {
                filteredCommits.add(commit);
            }
        }
        return filteredCommits;
    }

    /**
     * Gets a map containing the count of commits made by each developer.
     *
     * @return A map with developers as keys and their commit counts as values.
     */
    public Map<String, Integer> getCountOfCommitsByDeveloper() {
        Map<String, Integer> commitCountMap = new HashMap<>();
        for (Commit commit : commits) {
            commitCountMap.put(commit.getAuthor(), commitCountMap.getOrDefault(commit.getAuthor(), 0) + 1);
        }
        return commitCountMap;
    }

    /**
     * Gets a map containing the count of commits made by each developer per day.
     *
     * @return A map with developers as outer keys, dates as inner keys, and commit counts as values.
     */
    public Map<String, Map<String, Integer>> getCountOfCommitsByDeveloperPerDay() {
        Map<String, Map<String, Integer>> commitCountMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Commit commit : commits) {
            String author = commit.getAuthor();
            String dateKey = dateFormat.format(new Date(commit.getTimestamp()));

            commitCountMap.computeIfAbsent(author, k -> new HashMap<>());
            Map<String, Integer> authorCommitCount = commitCountMap.get(author);
            authorCommitCount.put(dateKey, authorCommitCount.getOrDefault(dateKey, 0) + 1);
        }

        return commitCountMap;
    }

    /**
     * Gets a set of developers who have not made commits for two consecutive days.
     *
     * @return A set of developers with no commits for two consecutive days.
     */
    public Set<String> getDevelopersWithNoCommitsForTwoDays() {
        Set<String> developersWithNoCommits = new HashSet<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Long> lastCommitDateByDeveloper = new HashMap<>();

        for (Commit commit : commits) {
            String author = commit.getAuthor();
            long commitTimestamp = commit.getTimestamp();
            long lastCommitTimestamp = lastCommitDateByDeveloper.getOrDefault(author, 0L);

            if (commitTimestamp - lastCommitTimestamp > 24 * 60 * 60 * 1000) {
                developersWithNoCommits.add(author);
            }

            lastCommitDateByDeveloper.put(author, commitTimestamp);
        }

        return developersWithNoCommits;
    }
}
