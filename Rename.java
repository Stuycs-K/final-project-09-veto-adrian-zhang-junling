import java.util.* ;
import java.io.* ;

public class Rename{
    public static void main(String[] args){
        try{
            Scanner scan = new Scanner(new File(args[0])) ;
            String total = "" ; 
            while(scan.hasNextLine()){
                String line = scan.nextLine() ;
                line = variable(line) ;
                line = functions(line) ;
                total += line + "\n" ;
            }
            FileWriter w = new FileWriter(args[0], false) ;
        }catch(Exceptions e){
            System.out.println(e) ;
        }
    }

    public static String variable(String line){
        String finished = "" ;

        return finished ;
    } 

    public static String functions(String line){
        String finished = "" ;

        return finished ;
    } 
}