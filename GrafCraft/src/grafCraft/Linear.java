package grafCraft;

import java.util.ArrayList;

import javax.swing.*;

public class Linear {
	
	JTextField input;
	float m;
	float b;
	Display sketch;
	ArrayList<int[]> points = new ArrayList<int[]>();

	
	public Linear(JTextField input, Display sketch) {
		this.input = input;
		this.sketch = sketch;
	}
	
	public void defineEquation() {
		for (int i = 0; i < input.getText().length(); i ++) {
			if (input.getText().charAt(i) == ')') {
				m = Float.parseFloat(input.getText().substring(5, i));
				b = Float.parseFloat(input.getText().substring(i + 6, input.getText().length() - 1));
				break;
				
			}
		}
	}
	
	

}
