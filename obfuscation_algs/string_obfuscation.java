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

    
}