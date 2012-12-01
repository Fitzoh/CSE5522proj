<<<<<<< HEAD
package counting;
=======
>>>>>>> 6076ef77bf85e8c24bebdc7d1948c96a7e5a9b54
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DocFormat {
	public File createFormattedFile(String filename) throws IOException {
		File outfile = new File("outfile");
		BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
		String word = new String();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		String line = reader.readLine();
		while (line != null) {
			line = stripPunctuationFromString(line);
			while (line.length() > 0) {
				word = getNextWord(line);
				word.toLowerCase();
				writer.write(word);
				writer.append('\n');
				if (word.length() >= line.length()) {
					line = new String();
				} else {
					line = line.substring(word.length() + 1);
				}
			}
			line = reader.readLine();
		}
		reader.close();
		writer.close();
		return outfile;
	}
	
	private String stripPunctuationFromString(String s) {
		StringBuffer buff = new StringBuffer(s);
		int index = 0;
		while (index < buff.length()) {
			if (Character.isLetterOrDigit(buff.charAt(index)) || Character.isWhitespace(buff.charAt(index)) || isEndingPunctuation(buff.charAt(index))) {
				index++;
			} else {
				buff.deleteCharAt(index);
			}
		}
		return buff.toString();
	}
	
	private String getNextWord(String line) {
		StringBuffer buff = new StringBuffer(line);
		StringBuffer ret = new StringBuffer();
		while ((buff.length() > 0) && Character.isWhitespace(buff.charAt(0))) {
			buff.deleteCharAt(0);
		}
		while ((buff.length() > 0) && !(buff.charAt(0) == ' ')){
			if (isEndingPunctuation(buff.charAt(0))) {
				ret.append('\n');
			} else {
				ret.append(buff.charAt(0));
			}
			buff.deleteCharAt(0);
		}
		return ret.toString();
	}
	
	private boolean isEndingPunctuation(char c) {
		return ((c=='.') || (c=='!') || (c=='?')); 
	}
}
