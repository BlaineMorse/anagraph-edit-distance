package editDistance;

public class EditDistance {
	private static int[][] distance;

	public static int getEditDistance(String x, String y) {
		if (!(x instanceof String) || !(y instanceof String))
			throw new IllegalArgumentException("Both strings must not be null");
		var n = x.length();
		var m = y.length();
		distance = new int[m + 1][n + 1];
		for (var row = 0; row <= m; row++)
			distance[row][0] = row;
		for (var col = 0; col <= n; col++)
			distance[0][col] = col;

		for (var i = 1; i <= m; i++) {
			for (var j = 1; j <= n; j++) {
				distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1),
						distance[i - 1][j - 1] + (x.charAt(j - 1) == y.charAt(i - 1) ? 0 : 2));
			}
		}

//		printEdit(x,y,distance);
		return distance[m][n];
	}

	private static void printEdit(String x, String y, int[][] distance) {
		System.out.println("\t" + String.join("\t", x.split("")));
		for (var row = 1; row < distance.length; row++) {
			System.out.print(y.charAt(row - 1));
			System.out.print("\t");
			for (var col = 1; col < distance[row].length; col++)
				System.out.print(distance[row][col] + "\t");
			System.out.println();
		}
	}

	private static void printWholeEdit(String x, String y, int[][] distance) {
		System.out.println("\t\t" + String.join("\t", x.split("")));
		for (var row = 0; row < distance.length; row++) {
			if (row > 0)
				System.out.print(y.charAt(row - 1));
			System.out.print("\t");
			for (var col = 0; col < distance[row].length; col++)
				System.out.print(distance[row][col] + "\t");
			System.out.println();
		}
	}
}
