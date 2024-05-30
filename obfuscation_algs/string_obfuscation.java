import java.util.Scanner;
import java.io.*;

public class string_obfuscation {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please specify a file!\n");
            exit();
        }

        File cfile = new File(args[1]);
        try {
            Scanner cscanner = new Scanner(cfile);  
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + args[1] + " not found!\n");
            exit();
        }

        String code = cscanner.useDelimiter("\\A").next();
        cscanner.close();
    }

    private static String caesar(String text, int x) {
        for (int i = 0; i < text.length(); i++) {
            char oldChar = text.charAt(i);
            if (oldChar >= 65 && oldChar <= 90) {
                oldChar += x;
                if (oldChar > 90) {
                    int off = oldChar - 90;
                    oldChar = 64;
                    oldChar += off;
                }
            }
            else if (oldChar >= 97 && oldChar <= 122) {
                oldChar += x;
                if (oldChar > 122) {
                    int off = oldChar - 122;
                    oldChar = 96;
                    oldChar += off;
                }
            }
            // System.out.println("char: " + oldChar);
            text = text.substring(0, i) + oldChar + text.substring(i + 1, text.length());
            // System.out.println(text);
        }
        // System.out.println("rotated: " + text);
        return text;
    }

}