package editDistanceAnagraph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

enum Atom {
	C, E, O, R, S, DOT, LINE, SCOOP
};

public class Anagraph {
	public static Set<Atom> toAtomList(String in) {
		if (in == null)
			throw new IllegalArgumentException("Input cannot be null");
		if (in.isEmpty())
			return Set.of();

		Set<Atom> components = new HashSet<>();

		// Fo
		for (var c : in.toCharArray()) {

		}
		return components;
	}

	private static List<Atom> charToAtoms(char c) {
		switch (c) {
		case 'a':
			return List.of(Atom.C, Atom.C, Atom.LINE);
		case 'b':
		case 'd':
		case 'p':
		case 'q':
			return List.of(Atom.C, Atom.LINE, Atom.LINE);
		case 'c':
			return List.of(Atom.C);
		case 'e':
			return List.of(Atom.E);
		case 'f':
			return List.of(Atom.LINE, Atom.LINE, Atom.SCOOP);
		case 'g':
			return List.of(Atom.C, Atom.LINE, Atom.R);
		case 'h':
			return List.of(Atom.LINE, Atom.R, Atom.LINE);
		case 'i':
			return List.of(Atom.LINE, Atom.DOT);
		case 'j':
			return List.of(Atom.LINE, Atom.LINE, Atom.DOT);
		case 'k':
			return List.of(Atom.LINE, Atom.LINE, Atom.LINE, Atom.LINE);
		case 'l':
			return List.of(Atom.LINE, Atom.LINE);
		case 'm':
		case 'w':
			return List.of(Atom.R, Atom.R, Atom.LINE);
		case 'n':
		case 'u':
			return List.of(Atom.R, Atom.LINE);
		case 'o':
			return List.of(Atom.O);
		case 'r':
			return List.of(Atom.R);
		case 's':
			return List.of(Atom.S);
		case 't':
			return List.of(Atom.LINE, Atom.LINE, Atom.LINE);
		case 'v':
			return List.of(Atom.LINE, Atom.LINE);
		case 'x':
			return List.of(Atom.LINE, Atom.LINE);
		case 'y':
			return List.of(Atom.SCOOP, Atom.R, Atom.LINE, Atom.LINE);
		case 'z':
			break;
		default:
			throw new UnsupportedOperationException(
					String.format("The character %s is not currently mapped to atoms.", c));
		}
		throw new UnsupportedOperationException(String.format("Trobule parsing %s", c));

	}
}
