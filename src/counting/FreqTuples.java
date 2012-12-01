package counting;

public class FreqTuples implements Comparable<FreqTuples>{
	String word;
	int count;
	int freq;
	
	public FreqTuples(String word, int count, int freq) {
		this.word = word;
		this.count = count;
		this.freq = freq;
	}
	@Override
	public int compareTo(FreqTuples arg0) {
		return new Integer(count).compareTo(arg0.count);
	}
}
