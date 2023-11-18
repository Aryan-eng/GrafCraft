package grafCraft;

import java.util.ArrayList;

import javax.swing.*;

public class Hyperbola {
	
	JTextField input;
	float h;
	float k;
	float a;
	float b;
	Display sketch;
	ArrayList<int[]> points = new ArrayList<int[]>();
	Boolean vertical = false;
	
	public Hyperbola(JTextField input, Display sketch) {
		this.input = input;
		this.sketch = sketch;
	}
	
	public void defineEquationForVertical() {
		for (int i = 7; i < input.getText().length(); i ++) {
			if (input.getText().charAt(i) == ')') {
				k = Float.parseFloat(input.getText().substring(7, i));
				for (int j = i + 8; j < input.getText().length(); j ++) {
					if (input.getText().charAt(j) == ')') {
						a = Float.parseFloat(input.getText().substring(i + 8, j));
						for (int n = j + 14; n < input.getText().length(); n ++) {
							if (input.getText().charAt(n) == ')') {
								h = Float.parseFloat(input.getText().substring(j + 14, n));
								for (int m = n + 8; m < input.getText().length(); m ++) {
									if (input.getText().charAt(m) == ')') {
										b = Float.parseFloat(input.getText().substring(n + 8, m));
										break;
									}
								}
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
	
	public void defineEquationForHorizantal() {
		for (int i = 7; i < input.getText().length(); i ++) {
			if (input.getText().charAt(i) == ')') {
				h = Float.parseFloat(input.getText().substring(7, i));
				for (int j = i + 8; j < input.getText().length(); j ++) {
					if (input.getText().charAt(j) == ')') {
						a = Float.parseFloat(input.getText().substring(i + 8, j));
						for (int n = j + 14; n < input.getText().length(); n ++) {
							if (input.getText().charAt(n) == ')') {
								k = Float.parseFloat(input.getText().substring(j + 14, n));
								for (int m = n + 8; m < input.getText().length(); m ++) {
									if (input.getText().charAt(m) == ')') {
										b = Float.parseFloat(input.getText().substring(n + 8, m));
										break;
									}
								}
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
