
public class TextBuffer {
	private String n_minus_2;
	private String n_minus_1;
	private String n_minus_0;

	public TextBuffer(){
		this.n_minus_2 = null;
		this.n_minus_1 = null;
		this.n_minus_0 = null;
	}
	
	public TextBuffer(String n_minus_1, String n_minus_0){
		this.n_minus_2 = null;
		this.n_minus_1 = n_minus_1;
		this.n_minus_0 = n_minus_0;
	}
	
	public TextBuffer(String n_minus_0){
		this.n_minus_2 = null;
		this.n_minus_1 = null;
		this.n_minus_0 = n_minus_0;
	}

	public TextBuffer(String n_minus_2, String n_minus_1, String n_minus_0){
		this.n_minus_2 = n_minus_2;
		this.n_minus_1 = n_minus_1;
		this.n_minus_0 = n_minus_0;
	}

	public void advance(String word){
		this.n_minus_2 = n_minus_1;
		this.n_minus_1 = n_minus_0;
		this.n_minus_0 = word;
	}
	
	public String n_minus_2(){
		return n_minus_2;
	}
	public String n_minus_1(){
		return n_minus_1;
	}
	public String n_minus_0(){
		return n_minus_0;
	}
}
