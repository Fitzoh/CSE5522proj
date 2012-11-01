package counting;

public class TextBuffer {
	String[] buffer = {null, null, null};
	public TextBuffer(){
	}
	public void advance(String word){
		buffer[2] = buffer[1];
		buffer[1] = buffer[0];
		buffer[0] = word;
	}
	public String n_minus_0(){
		return buffer[0];
	}
	public String n_minus_1(){
		return buffer[1];
	}
	public String n_minus_2(){
		return buffer[2];
	}
}
