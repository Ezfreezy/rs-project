package practice;

public class WritingCode { //enum used when values will not change
	
	int name = 5; // Variable Scope - Instance variable outside a method can be used by all methods can be referred with .this

	public static void Ifelsestatement() { // Public means it can be used by all Packages
										   //static means its saved to memory
										   //void means it will not return a value
										

		int name = 1; // Local variable can only be used by this method

		if (name == 1) { // condition
			System.out.print("gg"); // do this if true
		} else {
			System.out.print("bg"); // do that if false
		}

	}

	public static void Switchstatement() { // for multiple conditions

		int name;
		name = 1; // condition

		switch (name) {

		case 1:
			System.out.print("gg"); // will print this if name = 1
			break;

		case 2:
			System.out.print("bg"); // will print this if name = 2
			break;
			
		case 3:
			System.out.print("bg");
			break;

		}

	}

	public static void Forloop() {

		for (int start = 0; start <= 10; start++) { // Initialisation
													// conditional change

			System.out.print(start);
		}
	}

	public static void Whileloop() { // will test condition before statement

		int name = 1;
		int name2 = 15;

		while (name < name2) { // condition (will do nothing if false)

			System.out.print("gg");
			name++; // ++ operator increments by 1
		}
	}

	public static void Dowhileloop() { // will test condition after statement

		int name = 1;
		int name2 = 15;

		do {
			System.out.print("gg");
			name++;

		} while (name < name2); // condition (if false it will complete the statement once)
							
	}

	public static void Arrays() { // arrays are a list of similar variables under one name
								

		String[] name; //can also use objects
		name = new String[3]; // how many elements will be stored // 'new' - new object
							 

		name[0] = "gg"; // starts at 0 in java
		name[1] = "bg";
		name[2] = "bg";

		System.out.print(name[0]);

		int[] numbers = { 1, 2, 3 }; // quicker way of writing arrays

		System.out.print(numbers[0]);

	}
}