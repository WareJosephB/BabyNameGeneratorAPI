package syn.ana.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import syn.ana.persistence.domain.NameRequest;
import syn.ana.util.NameGenerator;

@Service
public class NameService {
	
	@Autowired
	private NameGenerator nameGen;

	public String generate(NameRequest nameRequest) {
		
		if (nameRequest.getLength() > Integer.valueOf("${maxLengthName}")) {
			return "${tooLongNameMessage}";
		}
		
		String[] forbiddenNames = "${forbiddenNames}".split(" ");
		
		boolean givenForbiddenBeginning = Arrays.stream(forbiddenNames).anyMatch(nameRequest.getBeginning()::equals);
		boolean givenForbiddenLength = (nameRequest.getLength() == nameRequest.getBeginning().length());
		
		if (givenForbiddenBeginning && givenForbiddenLength) {
			return "${forbiddenGivenNameMessage}";
		}
		
		String generatedName = nameGen.generateName(nameRequest.getLength(), nameRequest.getBeginning());
		boolean nameForbidden = Arrays.stream(forbiddenNames).anyMatch(generatedName::equals);

		while (nameForbidden) {
			generatedName = nameGen.generateName(nameRequest.getLength(), nameRequest.getBeginning());
		}
		
		return generatedName;
		
	}


}
