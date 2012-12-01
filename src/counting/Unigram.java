package counting;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Unigram {
	private Map<String, Integer> dict;
	
	public Unigram(){
		this.dict = new HashMap<String, Integer>();
	}
	
	//increment entry for text by one
	public void increment(TextBuffer text){
		multIncrement(text, 1);
	}
	
	//return count of entry for text
	public int get(TextBuffer Text){
		return dict.get(Text.n_minus_0());
	}
	
	//combine counts of this and other unigram
	public void merge(Unigram other) {
		for (String key : other.dict.keySet()){
			TextBuffer t = new TextBuffer(null,null, key);
			multIncrement(t,other.get(t));
		}
	}

	//add count to entry for text
	public void multIncrement(TextBuffer text, int count){
		if (!dict.containsKey(text.n_minus_0())){
			dict.put(text.n_minus_0(), 0);
		}
		dict.put(text.n_minus_0(), dict.get(text.n_minus_0())+count);
	}

	public PriorityQueue<WordCountPair> getWords(TextBuffer text) {
		PriorityQueue<WordCountPair> words = new PriorityQueue<WordCountPair>();
		for (Entry<String, Integer> entry : dict.entrySet()) {
			words.add(new WordCountPair(entry.getKey(), entry.getValue()));
		}
		return words;
	}


}
