import java.util.*;
import java.io.*;

public class Rename {
	private static boolean str = false;
	public static ArrayList<String> original = new ArrayList<String>(0);
	public static ArrayList<String> obscured = new ArrayList<String>(0);
	public static boolean blockCommented = false;
	public static boolean functionResult = false;

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(new File(args[0]));
			String total = "";
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				line = functions(line);
				total += line + "\n";
				// System.out.println(line) ;
			}
			scan.close();
			String temp = total;
			total = "";
			scan = new Scanner(temp);
			blockCommented = false;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				line = variable(line);
				total += line + "\n";
			}
			FileWriter w = new FileWriter(args[0], false);
			w.write(total);
			w.close();
			scan.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static String variable(String line) {
		line = line.trim();
		String prefix = "";
		String addon = "";
		// Check for All Comments
		boolean check = blockCommented;
		// System.out.println(line + "\n");
		// System.out.println(line + check);
		boolean Check = blockComment(line);
		// System.out.println(Check);
		if (Check != check) {
			// System.out.println("there");
			if (check) {
				// System.out.println("here");
				prefix = line.substring(0, line.indexOf("*/") + 2);
				line = line.substring(line.indexOf("*/") + 2);
			} else {
				// System.out.println("here2");
				addon = line.substring(line.indexOf("/*"));
				line = line.substring(0, line.indexOf("/*"));
			}
		} else {
			if (check) {
				return line;
			}
		}
		// System.out.println(prefix + " | " + addon + "|" + line);
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
			// System.out.println(Arrays.toString(split));
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
					finished += variable(split[i]);
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
			String temp = finished.substring(0, finished.indexOf("=") + 1);
			finished = finished.substring(finished.indexOf("=") + 1);
			String[] operating = finished.split("[() +*&/|;]");
			// System.out.println(Arrays.toString(operating));
			int index = -1;
			for (int i = 0; i < operating.length; i++) {
				for (int j = 0; j < original.size(); j++) {
					// System.out.println(operating[i]);
					// System.out.println(original.get(j));
					// System.out.println(original.toString());
					// System.out.println(temp);
					// System.out.println(finished + "\n");
					// System.out.println(original.get(j).equals(operating[i]));
					if (original.get(j).equals(operating[i])) {
						if (i == operating.length - 1) {
							temp += finished.substring(finished.indexOf(operating[i]));
							finished = temp;
						} else {
							System.out.println("active");
							temp += obscured.get(j) + finished.substring(original.get(j).length(),
									finished.indexOf(operating[i + 1]));
							finished = finished.substring(finished.indexOf(operating[i]) + operating[i].length());
						}
						break;
					} else if (j == original.size() - 1) {
						if (i == operating.length - 1) {
							temp += finished.substring(finished.indexOf(operating[i]));
						} else {
							temp += finished.substring(0, finished.indexOf(operating[i + 1]));
							finished = finished.substring(finished.indexOf(operating[i]) + operating[i].length());
						}
					}
				}
			}
			// System.out.println(original.toString());
			// System.out.println(finished);
			// System.out.println(temp);
			finished = temp;
		} else {
			// System.out.println("|" + line);
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
			String[] split = new String[0];
			if (line.indexOf("public class") != -1) {
				return prefix + line + addon;
			} else if (line.indexOf("public") != -1) {
				split = line.split(" ");
			} else {
				line = line.replaceAll(" ", "");
				split = line.split("[() +*&/|;]");
			}
			if (line.indexOf("return") != -1) {
				prefix += "return ";
				split = line.substring(line.indexOf("return") + 6).split("[() +*&/|;]");
				// System.out.println(Arrays.toString(split) + prefix);
			}
			// System.out.println(Arrays.toString(split) + "\n");
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
			return prefix + line + addon;
		}
		return prefix + finished + addon;
	}

	public static String functions(String line) {
		String prefix = "";
		String addon = "";
		// Check for All Comments
		boolean check = blockCommented;
		functionResult = check;
		// System.out.println(line + "\n");
		if (blockComment(line) != check) {
			if (check) {
				prefix = line.substring(0, line.indexOf("*/"));
				line = line.substring(line.indexOf("*/"));
			} else {
				addon = line.substring(line.indexOf("/*"));
				line = line.substring(0, line.indexOf("/*"));
			}
		} else {
			if (check) {
				return line;
			}
		}
		String temp = "";
		// System.out.println(prefix + "\n" + line + "\n___");
		// String finished = "";
		line = line.trim();
		if (line.indexOf("public class") != -1) {
			// System.out.println(line);
			return line;
		}
		String name = "";
		// Function Name at Declaration
		if (line.indexOf("public ") != -1) {
			String[] split = line.split("[ ()]");
			// System.out.println(Arrays.toString(split));
			if (split[3].equals("main")) {
				return prefix + line + addon;
			}
			String line2 = line;
			// System.out.println(line);
			if (split[1].equals("static")) {
				temp = line2.substring(0, line2.indexOf(split[3]));
				line2 = line2.substring(line2.indexOf("("));
				name = randomName(split[3].length());
				original.add(split[3]);
				// System.out.println(temp) ;
				// System.out.println(line2);
			} else {
				temp = line2.substring(0, line2.indexOf(split[2]));
				line2 = line2.substring(line2.indexOf("("));
				name = randomName(split[2].length());
				original.add(split[2]);
			}
			obscured.add(name);
			// System.out.println(name);
			temp += name + line2;
			line = temp;
			// System.out.println(temp);
			try {
				// Function's local Arguments
				// System.out.println(line) ;
				split = temp.split("[()]");
				// System.out.println(Arrays.toString(split));
				if (split.length > 2) {
					String temp2 = "";
					String args = split[1];
					temp2 += split[0] + "(";
					String[] split2 = args.split(",");
					// System.out.println(Arrays.toString(split2));
					for (int i = 0; i < split2.length; i++) {
						String arg = split2[i].trim();
						String[] type = arg.split(" ");
						// System.out.println(Arrays.toString(type)) ;
						temp2 += type[0];
						name = randomName(type[1].length());
						temp2 += " " + name;
						original.add(type[1]);
						obscured.add(name);
						if (i != split2.length - 1) {
							temp2 += ",";
						}
					}
					temp2 += ")" + split[2];
					// System.out.println(temp2);
					line = temp2;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prefix + line + addon;
	}

	public static boolean blockComment(String line) {
		if (line.indexOf("/*") != -1) {
			blockCommented = true;
			return true;
		} else if (line.indexOf("*/") != -1) {
			blockCommented = false;
			return false;
		} else {
			return blockCommented;
		}
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
