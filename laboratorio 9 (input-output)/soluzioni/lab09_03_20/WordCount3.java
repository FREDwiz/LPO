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

//Even more modular approach to manage options
public class WordCount3 {

	private static enum Option {
		INPUT("-i"), OUTPUT("-o");
		private final String lexem;

		private Option(String lexem) {
			this.lexem = lexem;
		}
	}

	private static final Map<String, Option> optionLexems = new HashMap<>();
	static {
		for (Option opt : Option.values())
			optionLexems.put(opt.lexem, opt);
	}

	private static final Map<Option, String> optionValues = new HashMap<>();
	static {
		for (Option opt : Option.values())
			optionValues.put(opt, null);
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
			if (!optionLexems.containsKey(curr))
				argError();
			optionValues.put(optionLexems.get(curr), readNext(it));
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
		try (Scanner sc = tryOpenInput(optionValues.get(Option.INPUT));
				PrintWriter pw = tryOpenOutput(optionValues.get(Option.OUTPUT));) {
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
