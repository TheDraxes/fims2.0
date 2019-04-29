package de.hwr.tests;

public class AllgemeineTests {
	
	
public static void main(String[] args) {
	System.out.println(extractHNR("Koppelweg, 20"));
	
	
	
}
	
	
public static String extractHNR(String input) {
		
		int index = input.indexOf(",");
		
		
		
		return input.substring(index + 1).trim();
	}
}
