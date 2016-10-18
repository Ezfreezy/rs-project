package practice;

public class Alcohol {

	String type;

	String percent;

	String colour;

	public String gettype() {

		return type;
	}

	public String getpercent() {

		return percent;
	}

	public String getname() {

		return colour;		

	}
	
	public Alcohol(String t, String p, String c) { // This is the constructor

		type = t;
		percent = p;
		colour = c;

	}

	/*public class WhatAlcohol {

		private static Alcohol JackDaniels = new Alcohol(); //creating the object // implicit constructor

		private static void settingJD() {

			JackDaniels.type = "Whiskey";
			JackDaniels.percent = "40%";
			JackDaniels.colour = "Brown";
		}

		private static Alcohol JackDaniels1 = new Alcohol("Whiskey", "40%", "Brown");
	}*/

}