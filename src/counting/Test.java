import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Test {
	static Counter totalCounter;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				args[0])));
		String line = reader.readLine();
		totalCounter = new Counter();
		while (line != null) {
			// System.out.println(PerformTesting(line,
			// Integer.parseInt(args[1]), Float.parseFloat(args[2])) * 100 +
			// "% accuracy with file " + line);
			System.out.println(PerformTesting(line, Integer.parseInt(args[1]),
					Float.parseFloat(args[2]), Boolean.parseBoolean(args[3])));
			line = reader.readLine();
		}
		reader.close();
	}

	public static float PerformTesting(String docName, int testingType,
			float percentTraining, boolean initializeEachCounter) throws IOException {
		float percent = percentTraining / 100;
		int total = 0, correct = 0, count = 0;
		File trainingFile;
		File testingFile;
		DocFormat format = new DocFormat();
		File formattedFile = format.createFormattedFile(docName);
		int fileLength = countLines(formattedFile);
		if (testingType == 0) {
			trainingFile = createSmallerFile(formattedFile, "training_file", 0,
					(int) (fileLength * percent));
			testingFile = createSmallerFile(formattedFile, "testing_file",
					(int) (fileLength * percent), fileLength);
		} else if (testingType == 1) {
			trainingFile = createSmallerFile(formattedFile, "training_file",
					(int) (fileLength * percent), fileLength);
			testingFile = createSmallerFile(formattedFile, "testing_file", 0,
					(int) (fileLength * percent));
		} else if (testingType == 2) {
			trainingFile = randomizeFile(formattedFile, "training_file", percent);
			testingFile = randomizeFile(formattedFile, "testing_file", (1 - percent));
		} else {
			trainingFile = createSmallerFile(formattedFile, "training_file",
					0, fileLength);
			testingFile = randomizeFile(formattedFile, "testing_file", (1-percent));
		}
		Counter testingCounter = new Counter();
		
		testingCounter.countFile(trainingFile);
		if (!initializeEachCounter) {
			totalCounter.merge(testingCounter);
		}

		BufferedReader reader = new BufferedReader(new FileReader(testingFile));
		String line = reader.readLine();
		String nextWord = null;
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
				nextWord = reader.readLine();
			} else {
				forTrigram.add(line);
				forTrigram.remove(0);
				text = new TextBuffer(forTrigram.get(0), forTrigram.get(1),
						null);
				if (initializeEachCounter) {
					temp = testingCounter.getWords(text);
				} else {
					temp = totalCounter.getWords(text);
				}
				nextWord = reader.readLine();
				if (temp != null) {
					likelyWords = new PriorityQueue<WordCountPair>(temp);
				} else {
					likelyWords = new PriorityQueue<WordCountPair>();
				}
				count = 0;
				while ((count < 10) && (likelyWords.size() > 0)) {
					if (likelyWords.remove().word.equals(nextWord)) {
						correct++;
						break;
					}
					count++;
				}
				total++;
				//if (total % 10000 == 0) {
				//	System.out.println("Current accuracy: " + correct + "//"
				//			+ total + " = " + (float) correct / total);
				//}
			}
			line = nextWord;
		}
		reader.close();
		return (float) correct / (float) total;
	}

	private static int countLines(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int count = 0;
		while (reader.readLine() != null) {
			count++;
		}
		reader.close();
		return count;
	}

	private static File createSmallerFile(File infile, String filename,
			int start, int end) throws IOException {
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
			// System.out.println("On line # " + count);
		}
		writer.close();
		reader.close();
		return outfile;
	}
	
	public static File randomizeFile(File infile, String filename, float percent) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(infile));
		File outfile = new File (filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
	    Random generator = new Random();
	    int random = 0;
	    boolean canGenerate = true;
		String line = reader.readLine();
		while (line != null) {
			if (canGenerate) {
				random = generator.nextInt(100);
				canGenerate = false;
			}
			if (random <= (percent * 100)) {
				writer.write(line + '\n');
			}
			if (line.length() == 0) {
				canGenerate = true;
			}
			line = reader.readLine();
		}
		reader.close();
		writer.close();
		return outfile;
	}
}
