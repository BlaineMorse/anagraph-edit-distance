package editDistanceAnagraph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import editDistance.EditDistance;
import editDistance.EditDistanceDriver;

public class AnagraphEditDistanceDriver {
	private final static int ALTERNATE_DEPTH = 5;

	public static void main(String[] args) {
		// Verify command line arguments
		if (args.length != 1)
			throw new IllegalArgumentException(
					"EditDistanceDriver requires a word source file! Program arguements are: <word_source_file>");

		try (Scanner userInput = new Scanner(System.in)) {
			// Get words from file, then convert into atoms
			System.out.println("Loading text file");
			var knownWordList = getWords(args[0]);
			System.out.println("Loaded text file\nConverting to anagraph Atoms");
			var wordToAtomMap = knownWordList.stream()
					.collect(Collectors.toMap(w -> w, w -> Anagraph.getAtomEncoding(w)));
			System.out.println("Converted words to anagraph atoms");

			// Main input-output loop
			System.out.print("Enter a word or (Q) to quit: ");
			String inWord = userInput.nextLine();
			while (!inWord.equals("Q")) {
				String finalInWord = inWord.toLowerCase();

				// Check user input for validity prior to atomizing
				if (!finalInWord.matches("^[a-z]+$")) {
					System.out.println("Input can only be one word and not contain special characters.");
					System.out.print("Enter a word or (Q) to quit: ");
					inWord = userInput.nextLine();
					continue;
				}

				var finalAtoms = Anagraph.getAtomEncoding(finalInWord);
				// Edit distance using atoms
				var knownWords = knownWordList.stream()
						.collect(Collectors.toMap(w -> w, w -> EditDistance.getEditDistance(finalAtoms, wordToAtomMap.get(w))));

				var bestMatch = knownWords.entrySet().stream().min((e1, e2) -> e1.getValue() - e2.getValue());

				if (bestMatch.isEmpty())
					throw new IllegalArgumentException("There are no words in the known word list!");

				System.out.println(
						String.format("Closest atomic match is: %s%nOther close atomic matches include:", bestMatch.get().getKey()));

				for (var i = 0; i < ALTERNATE_DEPTH; i++) {
					knownWords.remove(bestMatch.get().getKey());
					bestMatch = knownWords.entrySet().stream().min((e1, e2) -> e1.getValue() - e2.getValue());
					if (bestMatch.isEmpty())
						break;
					else
						System.out.println(String.format("\t%s", bestMatch.get()));
				}

				System.out.print("Enter a word or (Q) to quit: ");
				inWord = userInput.nextLine();
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static List<String> getWords(String filename) throws IOException, URISyntaxException {
		if (filename == null) {
			throw new IllegalArgumentException("Error: Filename cannot be null!");
		}
		var words = getPath(filename);
		var inFile = words.toFile();
		if (inFile.isFile() && inFile.getName().endsWith(".txt")) {

			try (var stream = Files.lines(words, StandardCharsets.UTF_8)) {
				return stream.map(w -> w.toLowerCase()).filter(w -> w.matches("^[a-z]+$")).collect(Collectors.toList());
			}
		} else
			throw new IllegalArgumentException(String.format("Error: The file %s is not a .txt file!", inFile));

	}

	private static Path getPath(String filename) throws URISyntaxException {
		URL url = EditDistanceDriver.class.getResource(filename);
		return Path.of(url.toURI());
	}
}
