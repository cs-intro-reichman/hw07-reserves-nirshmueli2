
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		// int ans = levenshtein("123457", "123456");
		// System.out.println(ans);
	}

	public static String tail(String str) {
		str = str.substring(1);
		return str;
	}

	public static int levenshtein(String word1, String word2) {

		String a = word1.toLowerCase();
		String b = word2.toLowerCase();

		if (b.length() == 0) {
			return a.length();
		}
		if (a.length() == 0) {
			return b.length();
		}
		if (a.charAt(0) == b.charAt(0)) {
			return levenshtein(tail(a), tail(b));
		}

		int min = levenshtein(tail(a), b);

		if (levenshtein(a, tail(b)) < min) {
			min = levenshtein(a, tail(b));
		}
		if (levenshtein(tail(a), tail(b)) < min) {
			min = levenshtein(tail(a), tail(b));
		}
		return 1 + min;

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minD = 1000;
		String mostSimilarWord = "";
		for (int i = 0; i < 3000; i++) {
			if (levenshtein(dictionary[i], word) < minD) {
				minD = levenshtein(dictionary[i], word);
				mostSimilarWord = dictionary[i];
			}
		}
		return minD > threshold ? word : mostSimilarWord;
	}

}
