package syn.ana.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import syn.ana.persistence.domain.BabyName;
import syn.ana.persistence.domain.NameRequest;
import syn.ana.util.NameGenerator;

@Service
public class NameService {

	@Value("${maxLengthName}")
	private int maxNameLength;

	@Autowired
	private NameGenerator nameGen;

	public BabyName generate(NameRequest nameRequest) {

		if (nameRequest.getLength() > maxNameLength) {
			return new BabyName("${tooLongNameMessage}", false);
		}

		String[] forbiddenNames = "${forbiddenNames}".split(" ");

		int lengthOfMaximumWord = Math.min(maxNameLength, nameRequest.getBeginning().length());

		boolean givenForbiddenWord = Arrays.stream(forbiddenNames)
				.anyMatch(nameRequest.getBeginning().substring(0, lengthOfMaximumWord)::equals);

		if (givenForbiddenWord) {
			return new BabyName("${forbiddenGivenNameMessage}", false);
		}

		String generatedName = nameGen.generateName(nameRequest.getLength(), nameRequest.getBeginning());
		boolean nameForbidden = Arrays.stream(forbiddenNames).anyMatch(generatedName::equals);

		while (nameForbidden) {
			generatedName = nameGen.generateName(nameRequest.getLength(), nameRequest.getBeginning());
		}

		return new BabyName(generatedName, true);

	}

}
