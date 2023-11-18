package grafCraft;

import java.util.ArrayList;

import javax.swing.*;

public class Circle {
	
	JTextField input;
	float h;
	float k;
	float r;
	Display sketch;
	ArrayList<int[]> points = new ArrayList<int[]>();

	
	public Circle(JTextField input, Display sketch) {
		this.input = input;
		this.sketch = sketch;
	}
	
	public void defineEquation() {
		for (int i = 6; i < input.getText().length(); i ++) {
			if (input.getText().charAt(i) == ')') {
				h = Float.parseFloat(input.getText().substring(6, i));
				for (int j = i + 13; j < input.getText().length(); j++) {
					if (input.getText().charAt(j) == ')') {
						k = Float.parseFloat(input.getText().substring(i+13, j));
						for (int x = j + 8; x < input.getText().length(); x++) {
							if (input.getText().charAt(x) == ')') {
								r = Float.parseFloat(input.getText().substring(j+8, x));
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
	
	

}
