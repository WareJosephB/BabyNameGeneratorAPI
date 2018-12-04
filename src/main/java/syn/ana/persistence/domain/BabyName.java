package syn.ana.persistence.domain;

public class BabyName {

	public BabyName(String name, boolean workingName) {
		this.name = name;
		this.workingName = workingName;
	}

	public BabyName() {

	}

	private String name;
	private boolean workingName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getWorkingName() {
		return workingName;
	}

	public void setWorkingName(boolean isName) {
		this.workingName = isName;
	}
}
