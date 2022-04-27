package editDistanceAnagraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

enum Atom {
	C("c"), E("e"), O("o"), R("r"), S("s"), DOT("."), LINE("|"), SCOOP(")");

	final String str;

	Atom(String string) {
		str = string;
	}
};

public class Anagraph {
	public static String getAtomEncoding(String inStr) {
		return atomListToString(getAtomList(inStr));
	}

	public static String atomListToString(List<Atom> atoms) {
		return atoms.stream().map(at -> at.str).collect(Collectors.joining());
	}

	public static List<Atom> getAtomList(String in) {
		if (in == null)
			throw new IllegalArgumentException("Input cannot be null");
		if (in.isEmpty())
			return List.of();

		List<Atom> components = new ArrayList<>();

		// For each letter, add the atoms
		for (var c : in.toCharArray()) {
			components.addAll(charToAtoms(c));
		}
		Collections.sort(components);
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
