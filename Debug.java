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
			boolean newline = true ;
			String line = scan.nextLine() ;
			boolean str = false ;
			int lineComment = 0 ;
			boolean star = false ;
			for(char c in line){
				if(c == '"'){
					str = !str ;
				}
				if(str){
					removed += c ;
				}
				if(c == '/'){
					if(lineComment = 0){
						lineComment = 1 ;
					}else if(lineComment = 1){
						lineComment = 2 ;
						removed = removed.substring(0, removed.length() - 2) ;
					}
					
				}else{
					lineComment = 0 ;
				}
				if(lineComment != 2){
					removed += c ;
				}
				if(lineComment == 1 && c == '*'){
					blockComment = true ;
				}else if(c == '/' && star){
					blockComment = false ;
					star = false ;
				}

				if(!blockComment){
					removed += c ;
				}
				if(c == '*'){
					star = true ;
				}

			}
			/*
			if(line.indexOf("/*") != -1){
				blockComment = true ;
				removed += line.substring(0, line.indexOf("/*")) ;
			}
			if(blockComment){
				// System.out.println(line.indexOf("/*")) ;
				// System.out.println(line) ;
				newline = false ;
				if(line.indexOf("*\/") != -1){
					blockComment = false ;
					removed += line.substring(line.indexOf("*\/") + 1) ;
					newline = true ;
				}
			}
			else if(line.indexOf("//") !=  -1){
				removed += line.substring(0, line.indexOf("//")) ;
				newline = false ;
			}else{
				removed += line ;
			}
			if(newline){
				removed += "\n" ;
			}
			*/
		}
		}catch(FileNotFoundException e){
			System.out.println("File Not Found; Check Your Input or If the file actually exists") ;
		}
		return removed ;
	}
}
