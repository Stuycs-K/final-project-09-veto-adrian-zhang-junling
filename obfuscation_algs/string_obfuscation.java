import java.util.Scanner;
import java.io.*;


public class string_obfuscation {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify a file!\n");
            System.exit(1);
        }

        File cfile = new File(args[0]);
        Scanner cscanner = null;
        try {
            cscanner = new Scanner(cfile);  
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + args[1] + " not found!\n");
        }

        String code = cscanner.useDelimiter("\\A").next();
        cscanner.close();
        
        System.out.println(obfuscated(code, 1));
    }

    private static String obfuscated(String code, int key) {
        String output = new String('*' * code.length());

        for (int i = 0; i < code.length(); i++) {
            // handling for anything other than strings
            if (code.charAt(i) != 34 && code.charAt(i) != 39) {
                output = output.substring(0, i) + code.charAt(i) + output.substring(i + 1, code.length());
            }
            // handling for strings
            else {
                int len = 0;
                while (code.charAt(i + len) != 34 && code.charAt(i + len) != 39) {
                    len++;
                }
                len -= 2;
                String s = code.substring(i + 1, i + len + 1);

                output = output.substring(0, i) + "\"" + caesar(s, key) + "\"" + output.substring(i + len + 1, code.length());
            }
        }

        return output;
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