import java.util.Objects;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (Objects.equals(dictionary[i], word)) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}

		int N = hashtag.length();
		for (int i = 1; i <= N; i++) {
			String subString = hashtag.substring(0, i);
			if (existInDictionary(subString, dictionary)) {
				System.out.println(subString);
				String restOfString = hashtag.substring(i);
				breakHashTag(restOfString, dictionary);
				return;
			}
		}
	}
}
