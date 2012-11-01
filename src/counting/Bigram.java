package counting;

import java.util.HashMap;
import java.util.Map;

public class Bigram {
	private Map<String, Unigram> dict;
	public Bigram(){
		this.dict = new HashMap<String, Unigram>();
	}
	public void increment(TextBuffer text){
		Unigram uni;
		if (!dict.containsKey(text.n_minus_1())){
			uni = new Unigram();
			dict.put(text.n_minus_1(), uni);
		}else{
			uni = dict.get(text.n_minus_1());
		}
		uni.increment(text);
		
	}
	public int get(TextBuffer text){
		return dict.get(text.n_minus_1()).get(text);
	}
}
