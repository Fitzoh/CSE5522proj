
public class UndoContractions {
	public static String unContract(String line) {
		if (line.contains("n't")) {
			line = (line.substring(0, line.indexOf("n't"))) + " not " + (line.substring(line.indexOf("n't") + 3, line.length()));
		} 
		if (line.contains("'ve")) {
			line = (line.substring(0, line.indexOf("'ve"))) + " have " + (line.substring(line.indexOf("'ve") + 3, line.length()));
		} 
		if (line.contains("'d")) {
			line = (line.substring(0, line.indexOf("'d"))) + " did " + (line.substring(line.indexOf("'d") + 2, line.length()));
		} 
		if (line.contains("'ll")) {
			line = (line.substring(0, line.indexOf("'ll"))) + " will " + (line.substring(line.indexOf("'ll") + 3, line.length()));
		} 
		if (line.contains("'s")) {
			line = (line.substring(0, line.indexOf("'s"))) + " is " + (line.substring(line.indexOf("'s") + 2, line.length()));
		} 
		if (line.contains("'re")) {
			line = (line.substring(0, line.indexOf("'re"))) + " are " + (line.substring(line.indexOf("'re") + 3, line.length()));
		} 
		return line;
	}
}
