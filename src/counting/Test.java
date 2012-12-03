import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Test {
	static Counter totalCounter;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		String line = reader.readLine();
		totalCounter = new Counter();
		while (line != null) {
			//System.out.println(PerformTesting(line, Integer.parseInt(args[1]), Float.parseFloat(args[2])) * 100 + "% accuracy with file " + line);
			System.out.println(PerformTesting(line, Integer.parseInt(args[1]), Float.parseFloat(args[2])) * 100);
			line = reader.readLine();
		}
		reader.close();
	}
	
	public static float PerformTesting(String docName, int testingType, float percentTraining) throws IOException {
		int total = 0, correct = 0, count = 0;
		File trainingFile;
		File testingFile;
		DocFormat format = new DocFormat();
		File formattedFile = format.createFormattedFile(docName);
		int fileLength = countLines(formattedFile);
		if (testingType == 0) {
			trainingFile = createSmallerFile(formattedFile, "training_file", 0, (int)(fileLength * percentTraining));
			testingFile = createSmallerFile(formattedFile,"testing_file", (int)(fileLength * percentTraining), fileLength);
		} else {
			trainingFile = createSmallerFile(formattedFile,"training_file", (int)(fileLength * percentTraining), fileLength);
			testingFile = createSmallerFile(formattedFile, "tresting_file", 0, (int)(fileLength * percentTraining));
		}
		Counter testingCounter = new Counter();
		
		testingCounter.countFile(trainingFile);
		//testingCounter.countFile(formattedFile);
		//totalCounter.merge(testingCounter);
		//System.out.println(testingCounter.getWords(new TextBuffer("to", "speak", null)));
		//System.exit(0);
		
		BufferedReader reader = new BufferedReader(new FileReader(testingFile));
		String line = reader.readLine();
		ArrayList<String> forTrigram = new ArrayList<String>();
		TextBuffer text = new TextBuffer();
		forTrigram.add(null);
		forTrigram.add(null);
		PriorityQueue<WordCountPair> likelyWords = new PriorityQueue<WordCountPair>();
		PriorityQueue<WordCountPair> temp = new PriorityQueue<WordCountPair>();
		while (line != null) {
			if (line.length() == 0) {
				forTrigram = new ArrayList<String>();
				forTrigram.add(null);
				forTrigram.add(null);
			} else {
				forTrigram.add(line);
				forTrigram.remove(0);
				text = new TextBuffer(forTrigram.get(0), forTrigram.get(1), null);
				temp = testingCounter.getWords(text);
				//temp = totalCounter.getWords(text);
				if (temp != null) {
					//System.out.println(forTrigram.get(0) + ' ' + forTrigram.get(1) + ' ' + null);
					likelyWords = new PriorityQueue<WordCountPair>(temp);
				} else {
					likelyWords = new PriorityQueue<WordCountPair>();
				}
				count = 0;
				while ((count < 10) && (likelyWords.size() > 0)) {
					if (likelyWords.remove().word.equals(reader.readLine())) {
						correct++;
					}
					//count++;
				}
				total++;
			}
			line = reader.readLine();
		}
		reader.close();
		return (float)correct/(float)total;
	}
	
	private static int countLines(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int count = 0;
		while(reader.readLine() != null) {
			count++;
		}
		reader.close();
		return count;
	}
	
	private static File createSmallerFile(File infile, String filename, int start, int end) throws IOException {
		File outfile = new File(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
		BufferedReader reader = new BufferedReader(new FileReader(infile));
		int count = 0;
		String line = reader.readLine();
		while (count < end) {
			if (count >= start) {
				writer.write(line + '\n');
			}
			line = reader.readLine();
			count++;	
		}
		writer.close();
		reader.close();
		return outfile;
	}
}
