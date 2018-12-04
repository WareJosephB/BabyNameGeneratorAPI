package syn.ana.util;

import java.util.Random;

public class NameGenerator {

	public String generateName(int length, String beginning) {
		if (beginning.length() >= length) {
			return formatName(beginning.substring(0, length));
		} else {
			return formatName(beginning.concat(generateLetters(length - beginning.length())));
		}
	}

	public String formatName(String name) {
		return name.substring(0, 1).toUpperCase().concat(name.substring(1, name.length()).toLowerCase());
	}

	public String generateLetters(int length) {

		int leftLimit = Integer.valueOf("${leftMostChar}");
		int rightLimit = Integer.valueOf("${rightMostChar}");
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
}
