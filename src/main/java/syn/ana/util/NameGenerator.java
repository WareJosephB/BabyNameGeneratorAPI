package syn.ana.util;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class NameGenerator {

	@Value("${leftMostChar}")
	private int firstChar; // Allows for limiting of scope to different alphabets

	@Value("${rightMostChar}")
	private int lastChar;

	public String generateName(int length, String beginning) {
		String startString = beginning.replace(" ", "");
		if (startString.length() >= length) {
			return formatName(startString.substring(0, length));
		} else {
			return formatName(startString.concat(generateLetters(length - startString.length())));
		}
	}

	public String formatName(String name) {
		return name.substring(0, 1).toUpperCase().concat(name.substring(1, name.length()).toLowerCase());
	}

	public String generateLetters(int length) {

		Random random = new Random();
		StringBuilder buffer = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomLimitedInt = firstChar + (int) (random.nextFloat() * (lastChar - firstChar + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
}
