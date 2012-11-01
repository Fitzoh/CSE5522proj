package counting;

import java.util.HashMap;
import java.util.Map;

public class Unigram {
	private Map<String, Integer> dict;
	public Unigram(){
		this.dict = new HashMap<String, Integer>();
	}
	public void increment(TextBuffer text){
		if (!dict.containsKey(text.n_minus_0())){
			dict.put(text.n_minus_0(), 0);
		}else{
			dict.put(text.n_minus_0(), dict.get(text.n_minus_0())+1);
		}
	}
	public int get(TextBuffer Text){
		return dict.get(Text.n_minus_0());
	}
}
