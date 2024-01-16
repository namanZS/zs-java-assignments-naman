package src.main.java.com.zs.assignment1;

import java.io.File;

public class SystemInfo {

    public static void main(String[] args) {
        printCurrentUser();
        printHomeDirectory();
        printSystemMemoryInfo();
        printSystemCPUInfo();
        printSystemDiskInfo();
        printOSInfo();
    }
    private static void printCurrentUser(){
        System.out.println("Current User: " + System.getProperty("user.name"));

    }
    private static void printHomeDirectory() {
        System.out.println("Home Directory: " + System.getProperty("user.home"));
    }

    private static void printSystemMemoryInfo() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Total Memory: " + maxMemory/(8*1024*1024) +" GB");
    }

    private static void printSystemCPUInfo() {
        long count = Runtime.getRuntime().availableProcessors();
        System.out.println("System CPU/Cores: " + count);
    }

    private static void printSystemDiskInfo() {
        File root = new File("/");
        long totalDiskSpace = root.getTotalSpace();
        System.out.println("System Disk Size: " + totalDiskSpace / (1024 * 1024 * 1024) + " GB");
    }

    private static void printOSInfo() {
        System.out.println("OS Build: " + System.getProperty("os.arch"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
    }
}

