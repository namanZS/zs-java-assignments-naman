package com.zs.assignment5;

import java.util.Date;

public class Commit {
        private final String hash;
        private final String message;
        private final String author;
        private final long timestamp;

    @Override
    public String toString() {
        return
                "hash='" + hash + '\'' +
                ", message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", timestamp=" + timestamp +
                '}'+'\'';
    }

    public Commit(String hash, String message, String author, long timestamp) {
            this.hash = hash;
            this.message = message;
            this.author = author;
            this.timestamp = timestamp*1000;
        }

        public String getHash() {
            return hash;
        }

        public String getMessage() {
            return message;
        }

        public String getAuthor() {
            return author;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
