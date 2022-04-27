package editDistanceAnagraph;

import java.util.Scanner;

public class AnagraphEditDistanceDriver {
	public static void main(String[] args) {
		try (Scanner userInput = new Scanner(System.in)) {

			// Main input-output loop
			System.out.print("Enter a word or (Q) to quit: ");
			String inWord = userInput.nextLine();
			while (!inWord.equals("Q")) {
				System.out.println(Anagraph.getAtomEncoding(inWord.toLowerCase()));
				System.out.print("Enter a word or (Q) to quit: ");
				inWord = userInput.nextLine();
			}
		}
	}
}
