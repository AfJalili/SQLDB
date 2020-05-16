package utils;

import java.io.*;

public class FileUtils {
    public static boolean writeToFileUsingPrintWriter(File file, String text) {
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bf = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bf)) {
            out.println(text);
        } catch (IOException e) {
            System.out.println(e.getMessage()); // todo delete
            return false;
        }
        return true;
    }

    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else return null;
    }

    public static long getFileSize(String path) {
        File file = new File(path);
        if(file.exists()) { return file.length(); } else { return -1; }
    }
}
