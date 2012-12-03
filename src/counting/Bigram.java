
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Bigram {
	private Map<String, Unigram> dict;
	
	public Bigram(){
		this.dict = new HashMap<String, Unigram>();
	}
	
	//increment entry for text by one
	public void increment(TextBuffer text){
		multIncrement(text, 1);
	}
	
	//get count of entry for text
	public int get(TextBuffer text){
		return dict.get(text.n_minus_1()).get(text);
	}

	//combine counts of this and other bigram
	public void merge(Bigram other) {
		for (String key : other.dict.keySet()){
			if (! dict.containsKey(key)){
				dict.put(key, new Unigram());
			}
			Unigram uni = dict.get(key);
			uni.merge(other.dict.get(key));
		}
	}
	
	//add count to entry for text
	public void multIncrement(TextBuffer text, int count){		
		Unigram uni;
		
		if (!dict.containsKey(text.n_minus_1())){
			uni = new Unigram();
			dict.put(text.n_minus_1(), uni);
		}else{
			uni = dict.get(text.n_minus_1());
		}
		uni.multIncrement(text, count);
	}

	public PriorityQueue<WordCountPair> getWords(TextBuffer text) {
		Unigram ngram = dict.get(text.n_minus_1());
		return (ngram == null) ? null : ngram.getWords(text);
	}
}
