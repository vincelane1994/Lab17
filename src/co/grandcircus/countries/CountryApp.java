package co.grandcircus.countries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CountryApp {
	static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		Path path = Paths.get("countries.txt");
		
		if (Files.notExists(path)) {// catch IOExceptions which always may occur when dealing with files.
			try {
				Files.createFile(path);
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		boolean mainMenu = true;
		do {
			
			System.out.println("===Main menu===\n1. Display list of countries\n2. Add a country\n3. Exit");
			System.out.println("Select an option: ");
			int userChoice = scnr.nextInt();
			scnr.nextLine();
			switch (userChoice) {
			case 1:
				List<Country> countries = CountryFileUtil.readFile();
				for (Country c : countries) {
					System.out.println(c.getName() + " has a population of " + c.getPopulation() );
				}
				break;
			case 2:
				do {
					System.out.print("Enter the name of a country: ");
					String name = scnr.nextLine();
					System.out.print("Enter " + name + "'s population: ");
					int population = scnr.nextInt();
					scnr.nextLine();
					Country newCountry = new Country(name, population);
					try {
						CountryFileUtil.appendToFile(newCountry);
					} catch (IOException e) {
						System.out.println("Could not edit file.");
					}
				}while(doAgain(scnr, "Would you like to add another country(y/n)?: "));
				break;
			case 3:
				scnr.close();
				System.out.println("Shutting down... Goodbye.");
				mainMenu = false;
				break;
			}
		}while(mainMenu);
//			
//			if(userChoice == 2) {
//				do {
//					System.out.print("Enter the name of a country: ");
//					String name = scnr.nextLine();
//					System.out.print("Enter " + name + "'s population: ");
//					int population = scnr.nextInt();
//					scnr.nextLine();
//					Country newCountry = new Country(name, population);
//					try {
//						CountryFileUtil.appendToFile(newCountry);
//					} catch (IOException e) {
//						System.out.println("Could not edit file.");
//					}
//				}while(doAgain(scnr, "Would you like to add another country(y/n)?: "));
//			} else if (userChoice ==1) {
//				
//				}
//			} else {
//				
//			}
//				
//			System.out.println("Goodbye!");
			
			
			
			
	}
		private static boolean doAgain(Scanner scnr, String prompt) {
			boolean isValid = false;
			boolean decision = false;
			do {
				System.out.println(prompt);
				String userInput = scnr.nextLine();
				if (userInput.matches("[yYnN][eEoO]{0,1}[sS]{0,1}")) {
					isValid = true;
					if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
						decision = true;
						isValid = true;
					} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
						decision = false;
						isValid = true;
					}
				} else {
					isValid = false;
					System.out.println("\"" + userInput + "\""
							+ " is not a valid option. You can type \"Y\" or \"Yes\" to continue or  \"N\" and \"No\" to exit");
				}
				// System.out.println(isValid);
			} while (!isValid);

			return decision;

		}

}
