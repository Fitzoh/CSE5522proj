package counting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
			textBuffer.advance(word);
			unigram.increment(textBuffer);
			bigram.increment(textBuffer);
			trigram.increment(textBuffer);
		}
		r.close();
	}
	
	public void merge(Counter other){
		unigram.merge(other.unigram);
		bigram.merge(other.bigram);
		trigram.merge(other.trigram);
	}
}
