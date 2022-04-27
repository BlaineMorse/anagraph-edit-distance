package editDistanceAnagraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

enum Atom {
	C("c"), E("e"), O("o"), R("r"), S("s"), DOT("."), DASH("-"), HOOK(")");

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
			return List.of(Atom.C, Atom.DASH);
		case 'b':
		case 'd':
		case 'p':
		case 'q':
			return List.of(Atom.C, Atom.DASH, Atom.DASH);
		case 'c':
			return List.of(Atom.C);
		case 'e':
			return List.of(Atom.E);
		case 'f':
			return List.of(Atom.DASH, Atom.DASH, Atom.HOOK);
		case 'g':
			return List.of(Atom.C, Atom.DASH, Atom.R);
		case 'h':
			return List.of(Atom.DASH, Atom.R, Atom.DASH);
		case 'i':
			return List.of(Atom.DASH, Atom.DOT);
		case 'j':
			return List.of(Atom.DASH, Atom.R, Atom.DOT);
		case 'k':
			return List.of(Atom.DASH, Atom.DASH, Atom.DASH, Atom.DASH);
		case 'l':
			return List.of(Atom.DASH, Atom.DASH);
		case 'm':
		case 'w':
			return List.of(Atom.R, Atom.R, Atom.DASH);
		case 'n':
		case 'u':
			return List.of(Atom.R, Atom.DASH);
		case 'o':
			return List.of(Atom.O);
		case 'r':
			return List.of(Atom.R);
		case 's':
			return List.of(Atom.S);
		case 't':
			return List.of(Atom.DASH, Atom.DASH, Atom.DASH);
		case 'v':
			return List.of(Atom.DASH, Atom.DASH);
		case 'x':
			return List.of(Atom.DASH, Atom.DASH);
		case 'y':
			return List.of(Atom.HOOK, Atom.R, Atom.DASH, Atom.DASH);
		case 'z':
			return List.of(Atom.DASH, Atom.DASH, Atom.DASH);
		default:
			throw new UnsupportedOperationException(
					String.format("The character %s is not currently mapped to atoms.", c));
		}
	}
}
