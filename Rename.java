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
				line = variable(line);
				//line = functions(line);
				total += line + "\n";
				// System.out.println(line) ;
			}
			FileWriter w = new FileWriter(args[0], false);
			w.write(total) ;
			w.close() ;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String variable(String line) {
		line = line.trim() ;
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
			finished += split[0] + " ";
			String name = randomName(split[1].length()) ;
			finished += name+"=";
			for (int i = 2; i < split.length; i++) {
				finished += split[i];
			}
			original.add(split[1]) ;
			obscured.add(name) ;
			// System.out.println(finished + Arrays.toString(split) + name) ;
			System.out.println(split[1] + " " + name) ;
		} else {
			ArrayList<String> information = new ArrayList<>();
			while (line.indexOf('"') != -1) {
				String temp = line.substring(0, line.indexOf('"'));
				line = line.substring(line.indexOf('"') + 1);
				information.add(line.substring(0, line.indexOf('"')));
				line = temp + line.substring(line.indexOf('"') + 1);
			}
			String[] template = new String[0] ;
			String[] og = original.toArray(template);
			String[] replace = obscured.toArray(template);
			for (int i = 0; i < og.length; i++) {
				line = line.replaceAll(og[i], replace[i]);
				// System.out.println(og[i] + " " + replace[i]) ;
			}
			// System.out.println(Arrays.toString(og)) ;
			// System.out.println(line) ;
			return line ;
		}
		return finished;
	}

	public static String functions(String line) {
		String finished = "";

		return finished;
	}

	public static String randomName(int length) {
		String name = "";
		char a = 'a';
		for (int i = 0; i < length; i++) {
			int letter = (int) (Math.random() * 100) % 26;
			name += (char) (a + letter);
		}
		/*
		for (int i = 0; i < obscured.size(); i++) {
			if (name.equals(obscured.get(i))) {
				name = randomName(length);
			}
		}*/
		return name;
	}
}
