package counting;

import java.util.HashMap;
import java.util.Map;

public class Trigram {
	private Map<String, Bigram> dict;
	public Trigram(){
		this.dict = new HashMap<String,Bigram>();
	}
	public void increment(TextBuffer text){
		Bigram bi;
		if (!dict.containsKey(text.n_minus_2())){
			bi = new Bigram();
			dict.put(text.n_minus_2(), bi);
		}else{
			bi = dict.get(text.n_minus_2());
		}
		bi.increment(text);
		
	}
	public int get(TextBuffer text){
		return dict.get(text.n_minus_2()).get(text);
	}
}
