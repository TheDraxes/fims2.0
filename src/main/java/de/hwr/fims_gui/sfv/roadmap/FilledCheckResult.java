package de.hwr.fims_gui.sfv.roadmap;

public class FilledCheckResult {
	String[] roadsNotFilled;
	boolean allFilled;
	
	public FilledCheckResult(String[] roads, boolean filled){
		this.allFilled = filled;
		this.roadsNotFilled = roads;
	}
	
	public String[] getRoadsNotFilled() {
		return roadsNotFilled;
	}
	
	public boolean getAllFilled() {
		return allFilled;
	}
}
