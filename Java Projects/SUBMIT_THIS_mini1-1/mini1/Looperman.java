package mini1;

public class Looperman {

	// disable instantiation
	private Looperman() {

	}

	public static String doubleAllVowels(String s) {
		String str = "";

		for (int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);

			if (isVowel(c)) {

				str += c;
			}

			str += c;
		}
		return str;

	}
	private static boolean isVowel(char ch) {
		return ("aeiouAEIOU".indexOf(ch) != -1);
	}

	public static int collatzCounter(int n) {
		int count = 0;
		while (n != 1) {
			if ((n % 2) == 1) {
				n = 3 * n + 1;
			} else if (n<= 0) {
				return -1;
			} else {

				n = n / 2;
			}
			count++;
		}

		return count;

	}

	public static String parseNameNumbers(String text) {

		String temp = "0";
		String result = "";

		double sum = 0;
		int count = 1;

		for (int i = 0; i<text.length(); i++) {
			char ch = text.charAt(i);

			if (Character.isDigit(ch)) {

				temp += ch;
			} else {

				result += text.charAt(i);
				sum += Integer.parseInt(temp);
				if (sum > 0) {
					count++;
				}
				temp = "0";

			}
		}
		result = result.trim();
		result += ": " + (sum + Integer.parseInt(temp)) / count;

		return result;
	}

	public static boolean differByTwoInsertions(String s,
		String t) {

		int len1 = s.length(), len2 = t.length();

		if (Math.abs(len1 - len2) > 2)
			return false;

		if (len1 == 0 || len2 == 0) {
			return Math.abs(len1 - len2) == 2;
		}
		int count = 0;

		int i = 0, j = 0;
		while (i<len1 && j<len2) {

			if (s.charAt(i) != t.charAt(j)) {
				if (count == 2)
					return false;

				if (len1 > len2)
					i++;
				else if (len1<len2)
					j++;

				count++;
			} else {
				i++;
				j++;

			}

		}

		if (len1 == i || len2 == j) {
			return Math.abs(len1 - len2) == 2;
		}
		if (i<len1 || j<len2)
			count++;

		return count == 2;
	}

	public static java.lang.String plusMode(java.lang.String s) {
		int countSign = 0;
		for (int i = 0; i<s.length(); i++) {
			if (!Character.isAlphabetic(s.charAt(i))) {
				countSign++;
			}
			if (countSign == s.length()) {
				return "";
			}
		}

		int j;
		char temp;
		String str = "";

		for (int i = 0; i<s.length(); i++) {
			int flag = 0;
			char ch = s.charAt(i);
			if (Character.isAlphabetic(ch))
				str += ch;

			else {

				if (ch != '-') {

					j = i + 1;

					while (true) {

						if (s.charAt(j) == '-')
							break;

						if ((j + 1) >= s.length()) {
							str += s.charAt(j);
							break;
						}

						for (int k = j; k<s.length(); k++) {
							if (s.charAt(k) == '-') {
								flag = 1;
								break;
							}
						}

						temp = s.charAt(j);
						if (Character.isAlphabetic(temp)) {
							if (flag == 1) {
								str += Character.toUpperCase(temp);

							} else
								str += temp;

						}
						j++;
					}

					i = j;
				}
			}

		}
		return str;

	}

	public static String doubleVowelsMaybe(String s) {
		String str = "";
		int count = 0;
		char vowel = ' ';
		for (int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);

			if (isVowel(c)) {
				str += c;
				count++;
				vowel = c;
				if (i == s.length() - 1 && !isVowel(s.charAt(i - 1))) {
					str += c;
				}
			} else {

				if (count == 1) {
					str += vowel;

				}

				count = 0;
				vowel = ' ';
				str += c;
			}

		}
		return str;

	}

	public static void witchHat(int n) {
		for (int i = n - 1; i > 0; i--) {
			String s_spaces = stringRepetition(n + i, ' ');
			String s_stars = stringRepetition(2 * (n - 1 - i), '*');
			String s_outline = s_spaces + "/" + s_stars + "\\";
			System.out.println(s_outline);
		}
		String spaces = stringRepetition(n, '_');
		String stars = stringRepetition(2 * (n - 1), '*');
		String pattern = spaces + "/" + stars + "\\" + spaces;
		System.out.println(pattern);
	}
	private static String stringRepetition(int d, char c) {
		String str1 = "";
		for (int i = 0; i<d; i++)
			str1 = str1 + c;
		return str1;
	}

}