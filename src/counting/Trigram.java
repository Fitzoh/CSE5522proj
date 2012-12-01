package counting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Trigram {
	private Map<String, Bigram> dict;
	
	public Trigram(){
		this.dict = new HashMap<String,Bigram>();
	}
	
	//increment entry for text by one
	public void increment(TextBuffer text){
		multIncrement(text,1);
	}
	
	//get count of entry for text
	public int get(TextBuffer text){
		return dict.get(text.n_minus_2()).get(text);
	}
	
	//get most likely list of words
	public PriorityQueue<WordCountPair> getWords(TextBuffer text) {
		Bigram ngram = dict.get(text.n_minus_2());
		return ngram.getWords(text);
	}
	
	//combine counts of this and other bigram
	public void merge(Trigram other) {
		for (String key : other.dict.keySet()){
			Bigram bi = dict.get(key);
			bi.merge(other.dict.get(key));
		}
		
	}
	
	//add count to entry for text
	public void multIncrement(TextBuffer text, int count){		
		Bigram bi;
		
		if (!dict.containsKey(text.n_minus_1())){
			bi = new Bigram();
			dict.put(text.n_minus_1(), bi);
		}else{
			bi = dict.get(text.n_minus_1());
		}
		bi.multIncrement(text, count);
	}
}
