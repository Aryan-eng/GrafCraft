package grafCraft;

import java.util.ArrayList;

import javax.swing.*;

public class Quadratic {
	
	JTextField input;
	float a;
	float b;
	float c;
	Display sketch;
	ArrayList<int[]> points = new ArrayList<int[]>();

	
	public Quadratic(JTextField input, Display sketch) {
		this.input = input;
		this.sketch = sketch;
	}
	
	public void defineEquation() {
		for (int i = 0; i < input.getText().length(); i ++) {
			if (input.getText().charAt(i) == ')') {
				a = Float.parseFloat(input.getText().substring(5, i));
				for (int j = i + 8; j < input.getText().length(); j ++) {
					if (input.getText().charAt(j) == ')') {
						b = Float.parseFloat(input.getText().substring(i + 8, j));
						for (int k = j + 6; k < input.getText().length(); k++) {
							if (input.getText().charAt(k) == ')') {
								c = Float.parseFloat(input.getText().substring(j + 6, k));
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
