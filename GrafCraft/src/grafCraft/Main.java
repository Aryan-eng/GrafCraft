package grafCraft;

public class Main {

	public static void main(String[] args) {
		Display sketch = new Display();
		MyFrame myFrame = new MyFrame(sketch);

		myFrame.show();
		sketch.run();
		Equation e = new Equation("y = 3x", sketch);

		
	}
	 

}
