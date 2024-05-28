import java.util.* ;
import java.io.* ;

public class Debug{
	public static void main(String[] args){
		try{
			String formatted = RmComments(args[0]) ;
			File file = new File(args[0]) ;
			FileWriter w = new FileWriter(args[0], false) ;
			w.write(formatted) ;
			w.close() ;
		}catch(FileNotFoundException e){
			System.out.println("File Not Found; Check Your Input or If the file actually exists") ;
		}catch(IOException e){
			System.out.println(e) ;
		}
	}

	public static String RmComments(String filename){
		String removed = "" ;
		try{
		Scanner scan = new Scanner(new File(filename)) ;
		boolean blockComment = false ;
		while(scan.hasNextLine()){
			String line = scan.nextLine() ;
			if(line.indexOf("/*") != -1){
				blockComment = true ;
			}
			if( !blockComment){
				removed += line.substring(0, line.indexOf("/*")) ;
				if(line.indexOf("*/") != -1){
					blockComment = false ;
					removed += line.substring(line.indexOf("*/") + 1) ;
				}
			}
			else if(line.indexOf("//") ==  -1){
				removed += line.substring(0, line.indexOf("//")) ;
			}
		}
		}catch(FileNotFoundException e){
			System.out.println("File Not Found; Check Your Input or If the file actually exists") ;
		}
		return removed ;
	}
}
