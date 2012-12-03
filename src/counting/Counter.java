
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Counter {
	private TextBuffer textBuffer;
	private Unigram unigram;
	private Bigram bigram;
	private Trigram trigram;
	
	public Counter(){
		unigram = new Unigram();
		bigram = new Bigram();
		trigram = new Trigram();
	}
	
	public void countFile(File formatted) throws IOException{
		textBuffer = new TextBuffer();
		BufferedReader r = new BufferedReader(new FileReader(formatted));
		String word = r.readLine();
		
		while(word!=null){
			if (word.length() > 0) { 
				textBuffer.advance(word);
				unigram.increment(textBuffer);
				bigram.increment(textBuffer);
				trigram.increment(textBuffer);
			} else {
				textBuffer = new TextBuffer();
			}
			//System.out.println(textBuffer.n_minus_2() + ' ' + textBuffer.n_minus_1() + ' ' + textBuffer.n_minus_0());
			word = r.readLine();
		}
		r.close();
	}
	
	public void merge(Counter other){
		unigram.merge(other.unigram);
		bigram.merge(other.bigram);
		trigram.merge(other.trigram);
	}
	
	public PriorityQueue<WordCountPair> getWords(TextBuffer text) {
		PriorityQueue<WordCountPair> ret = trigram.getWords(text);
		return (ret == null) ? null : ret;
	}
}
