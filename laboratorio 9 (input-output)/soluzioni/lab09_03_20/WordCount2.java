package lab09_03_20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// More modular approach to manage options
public class WordCount2 {

	public static final String INPUT_OPT = "-i";
	public static final String OUTPUT_OPT = "-o";
	public static final Map<String, String> options = new HashMap<>();
	static {
		options.put(INPUT_OPT, null);
		options.put(OUTPUT_OPT, null);
	}

	private static void argError() {
		System.err.println("Illegal argument");
		System.exit(1);
	}

	private static String readNext(Iterator<String> it) {
		if (!it.hasNext())
			argError();
		return it.next();
	}

	private static void processArgs(String[] args) {
		Iterator<String> it = Arrays.asList(args).iterator();
		while (it.hasNext()) {
			String curr = it.next();
			if (!options.containsKey(curr))
				argError();
			options.put(curr, readNext(it));
		}
	}

	private static Scanner tryOpenInput(String inputPath) throws FileNotFoundException {
		Reader rd = inputPath == null ? new InputStreamReader(System.in) : new FileReader(inputPath);
		return new Scanner(new BufferedReader(rd));
	}

	private static PrintWriter tryOpenOutput(String outputPath) throws FileNotFoundException {
		return outputPath == null ? new PrintWriter(System.out) : new PrintWriter(outputPath);
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		processArgs(args);
		try (Scanner sc = tryOpenInput(options.get(INPUT_OPT));
				PrintWriter pw = tryOpenOutput(options.get(OUTPUT_OPT));) {
			sc.useDelimiter("[^a-zA-Z]+"); // sc.useDelimiter("[\\W\\d_]+");
			while (sc.hasNext()) {
				String word = sc.next();
				map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
			}
			pw.println(map);
			// String[] keys=map.keySet().toArray(args);
			// Arrays.sort(keys);
			// for(String s:keys)
			// System.out.println(s+":"+map.get(s));
		} catch (Throwable t) {
			System.err.println(t.getMessage());
			System.exit(2);
		}
	}
}
