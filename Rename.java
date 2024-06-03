import java.util.*;
import java.io.*;

public class Rename {
	private static boolean str = false;
	public static ArrayList<String> original = new ArrayList<String>(0);
	public static ArrayList<String> obscured = new ArrayList<String>(0);

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(new File(args[0]));
			String total = "";
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				line = functions(line);
				line = variable(line);
				total += line + "\n";
				// System.out.println(line) ;
			}
			FileWriter w = new FileWriter(args[0], false);
			w.write(total);
			w.close();
			scan.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace() ;
		}
	}

	public static String variable(String line) {
		line = line.trim();
		String finished = "";
		boolean detected = false;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '=' && !str) {
				detected = true;
			}
			if (c == '"') {
				str = !str;
			}
		}
		if (detected) {
			String[] split = line.split("[ =]");
			boolean newVar = true;
			String checkState = line.substring(0, line.indexOf("="));
			if (checkState.charAt(checkState.length() - 1) == ' ') {
				checkState = checkState.substring(0, checkState.length() - 1);
			}
			// System.out.println(checkState + " " + split[0] + "|");
			if (split[0].equals(checkState)) {
				newVar = false;
			}
			if (newVar) {
				finished += split[0] + " ";
				String name = randomName(split[1].length());
				finished += name + "=";
				for (int i = 2; i < split.length; i++) {
					finished += split[i];
				}
				original.add(split[1]);
				obscured.add(name);
				// System.out.println(finished + Arrays.toString(split) + name) ;
				// System.out.println(split[1] + " " + name);
			} else {
				for (int i = 0; i < original.size(); i++) {
					if (original.get(i).equals(split[0])) {
						finished += obscured.get(i) + "=";
						break;
					}
				}
				for (int i = 1; i < split.length; i++) {
					finished += split[i];
				}
			}
		} else {
			// ArrayList<String> information = new ArrayList<>();
			/*
			 * while (line.indexOf('"') != -1) {
			 * String temp = line.substring(0, line.indexOf('"'));
			 * line = line.substring(line.indexOf('"') + 1);
			 * information.add(line.substring(0, line.indexOf('"')));
			 * line = temp + line.substring(line.indexOf('"') + 1);
			 * }
			 */
			// System.out.println(line);
			String[] split = new String[0] ;
			if(line.indexOf("public class") != -1 || line.indexOf("return") != -1){
				return line ;
			}else if(line.indexOf("public") != -1){
				split = line.split(" ") ;
			}else{
				line  = line.replaceAll(" ", "") ;
				split = line.split("[{}() +*_&/|]");
			}
			// System.out.println(Arrays.toString(split));
			String temp = "";
			boolean skip = false;
			for (int j = 0; j < split.length; j++) {
				for (int i = 0; i < original.size(); i++) {
					// System.out.println(skip);
					// System.out.println(split[j] + " " + original.get(i) +
					// original.get(i).equals(split[j]));
					if (original.get(i).equals(split[j])) {
						int length = split[j].length();
						if (j == split.length - 1) {
							temp += obscured.get(i) + line.substring(line.indexOf(split[j]) + length);
						} else {
							temp += obscured.get(i)
									+ line.substring(line.indexOf(split[j]) + length, line.indexOf(split[j + 1]));
							line = line.substring(line.indexOf(split[j + 1]));
						}
						skip = true;
						// System.out.println(temp);
					}
				}
				// System.out.println(skip);
				if (!skip) {
					// System.out.println(1);
					int length = split[j].length();
					// System.out.println(length + " " + line.indexOf(split[j]));
					// System.out.println(line + "\n" + temp);
					int end = line.indexOf(split[j]);
					temp += line.substring(end, end + length);
					line = line.substring(end + length);
					// System.out.println(line.indexOf(split[j + 1]));
					if (j == split.length - 1) {
						temp += line.substring(0);
					} else {
						temp += line.substring(0, line.indexOf(split[j + 1]));
						line = line.substring(line.indexOf(split[j + 1]));
					}
					skip = false;
				}
				skip = false;
				// System.out.println(skip);
				// System.out.println(temp);
				// System.out.println(line);
				// System.out.println(og[i] + " " + replace[i]) ;

				// System.out.println(temp);
			}
			line = temp;
			// System.out.println(Arrays.toString(og)) ;
			// System.out.println(line) ;
			return line;
		}
		return finished;
	}

	public static String functions(String line) {
		String finished = "";
		line = line.trim();
		if(line.indexOf("public class") != -1){
			// System.out.println(line);
			return line ;
		}
		String temp = "" ;
		String name = "" ;
		// Function Name at Declaration
		if(line.indexOf("public ") != -1){
			String[] split = line.split("[ ()]");
			// System.out.println(Arrays.toString(split)) ;
			if(split[3].equals("main")){
				return line ;
			}
			String line2 = line ;
			if(split[1].equals("static")){
				temp = line2.substring(0, line2.indexOf(split[3])) ;
				line2 = line2.substring(line2.indexOf(split[4]));
				name = randomName(split[3].length()) ;
				original.add(split[3]) ;
				// System.out.println(temp) ;
			}else{
				temp = line2.substring(0, line2.indexOf(split[2])) ;
				line2 = line2.substring(line2.indexOf(split[3])) ;
				name = randomName(split[2].length()) ;
				original.add(split[2]) ;
			}
			obscured.add(name) ;
			temp += name +"(" + line2;
			line = temp ;
			// System.out.println(temp) ;
			try{
		// Function's local Arguments
		// System.out.println(line) ;
		split = temp.split("[()]") ;
		// System.out.println(Arrays.toString(split)) ;
		if(split.length > 2){
			String temp2 = "" ;
			String args = split[1] ;
			temp2 += split[0] + "(";
			String[] split2 = args.split(",") ;
			System.out.println(Arrays.toString(split2)) ;
			for(int i = 0; i < split2.length ; i++){
				String arg = split2[i].trim() ;
				String[] type = arg.split(" ") ;
				// System.out.println(Arrays.toString(type)) ;
				temp2 += type[0];
				name = randomName(type[1].length()) ;
				temp2 += " " + name;	
				original.add(type[1]) ;
				obscured.add(name) ;
				if(i != split2.length -1){
					temp2 += "," ;
				}
			}
			temp2 += ")" + split[2] ;
			System.out.println(temp2) ;
			line = temp2;
		}
		}catch(Exception e){
			e.printStackTrace() ;
		}
		}
		return line;
	}

	public static String randomName(int length) {
		String name = "";
		char a = 'a';
		for (int i = 0; i < length; i++) {
			int letter = (int) (Math.random() * 100) % 26;
			name += (char) (a + letter);
		}
		
		 for (int i = 0; i < obscured.size(); i++) {
		 if (name.equals(obscured.get(i))) {
		 name = randomName(length);
		 }
		 }
		 
		return name;
	}
}
