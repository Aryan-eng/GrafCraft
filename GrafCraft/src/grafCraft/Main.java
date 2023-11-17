package grafCraft;

public class Main {

	public static void main(String[] args) {
		Display sketch = new Display();
		MyFrame myFrame = new MyFrame(sketch);

		myFrame.show();
		sketch.run();
		
	}
	 

}
