package grafCraft;

import java.util.ArrayList;
import java.util.Stack;

//Chris's class

public class Equation {
	
	public Display sketch;
	public String equation;
	public String equation2;
	public String equation3;
	private double x=0;
	private double y=0;
	private String Left;
	private String Right;
	private double xmin;
	private double xmax;
	private double ymax;
	private double ymin;
	private double ox=10;
	private double oy=10;
	public ArrayList<Float> pointsY = new ArrayList<Float>();
	public ArrayList<Float> pointsX = new ArrayList<Float>();
	
	
	
	public Equation(String equation, Display sketch) {
		System.out.println(equation);
		equation = equation.replace(" ", "");
		this.equation = equation;
		this.sketch = sketch;
	}
	
    public static double evaluateExpression(String expression) { //Aaryan Patel helped me with this method: such as syntax of stacks and logic.
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        StringBuilder operandBuilder = new StringBuilder();

        for (char character : expression.toCharArray()) {
            if (Character.isDigit(character) || character == '.') {
                operandBuilder.append(character);
            } else {
                if (operandBuilder.length() > 0) {
                    if (isOperand(operandBuilder.toString())) {
                        operands.push(Double.parseDouble(operandBuilder.toString()));
                    } else {
                        throw new IllegalArgumentException("Invalid operand: " + operandBuilder.toString());
                    }
                    operandBuilder.setLength(0);
                }

                if (isOperator(character)) {
                    while (!operators.isEmpty() && getPrecedence(character) <= getPrecedence(operators.peek())) {
                        double operand2 = operands.pop();
                        double operand1 = operands.pop();
                        char operator = operators.pop();
                        operands.push(applyOperator(operator, operand1, operand2));
                    }
                    operators.push(character);
                } else if (character == '(') {
                    operators.push('(');
                } else if (character == ')') {
                    while (operators.peek() != '(') {
                        double operand2 = operands.pop();
                        double operand1 = operands.pop();
                        char operator = operators.pop();
                        operands.push(applyOperator(operator, operand1, operand2));
                    }
                    operators.pop(); // Remove the '('
                }
            }
        }

        if (operandBuilder.length() > 0) {
            if (isOperand(operandBuilder.toString())) {
                operands.push(Double.parseDouble(operandBuilder.toString()));
            } else {
                throw new IllegalArgumentException("Invalid operand: " + operandBuilder.toString());
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = operands.pop();
            double operand1 = operands.pop();
            char operator = operators.pop();
            operands.push(applyOperator(operator, operand1, operand2));
        }

        return operands.pop();
    }
    
    private static String evaluateTrig(String equ) {
    	Double temp = 0.0;
    	int open=0;
    	int close=0;
    	for (int i=0; i < equ.length(); i++) {
    		if (i+3 <= equ.length() && equ.substring(i, i+3).equals("sin")) {
    			for (int j = i+3; j< equ.length(); j++) {
    				if (equ.charAt(j) == ')') {
    					close++;
    				}
    				if (equ.charAt(j) == '(') {
    					open++;
    				}
    				if (open==close) {
    					temp = Math.sin(evaluateExpression(equ.substring(i+3, j+1)));
    					equ = equ.replace(equ.substring(i, j+1), "("+String.valueOf(temp)+")");
    					open=0;
    					close=0;
    					break;
    				}
    			}
    		}
    		if (i+3 <= equ.length() && equ.substring(i, i+3).equals("cos")) {
    			for (int j = i+3; j< equ.length(); j++) {
    				if (equ.charAt(j) == ')') {
    					close++;
    				}
    				if (equ.charAt(j) == '(') {
    					open++;
    				}
    				if (open==close) {
    					temp = Math.cos(evaluateExpression(equ.substring(i+3, j+1)));
    					equ = equ.replace(equ.substring(i, j+1), "("+String.valueOf(temp)+")");
    					open=0;
    					close=0;
    					break;
    				}
    			}
    		}
    		if (i+3 <= equ.length() && equ.substring(i, i+3).equals("tan")) {
    			for (int j = i+3; j< equ.length(); j++) {
    				if (equ.charAt(j) == ')') {
    					close++;
    				}
    				if (equ.charAt(j) == '(') {
    					open++;
    				}
    				if (open==close) {
    					temp = Math.tan(evaluateExpression(equ.substring(i+3, j+1)));
    					equ = equ.replace(equ.substring(i, j+1), "("+String.valueOf(temp)+")");
    					open=0;
    					close=0;
    					break;
    				}
    			}
    		}
    	}
    	return equ;
    }	

    private static boolean isOperand(String character) {
        try {
            Double.parseDouble(character);
        	return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^';
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static double applyOperator(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

	
	public void LeftRight(String equation) 
	{
		for (int i=0; i<equation.length(); i++) 
		{
			if (equation.charAt(i) == '=') 
			{
				Left = equation.substring(0,i);
				Right = equation.substring(i+1, equation.length());
				break;
			}
		}	
	}
	
	/*public void toPowers() 
	{
		String mathL = "";
		String temp = "";
		int beg;
		int fin;
		for (int i = 0; i < Left.length(); i++) 
		{
			if (Left.charAt(i) == '(') 
			{
				beg=i;
				for (int j = i; j < Left.length(); j++) {
					
					if (Left.charAt(j) == ')') {
						fin=j;
						i=j;
						if (j+1 < Left.length()-2 && Left.charAt(j+1) == '^' && Left.charAt(j+2) == '(') {
							for (int m = j+2;  m < Left.length(); m++) {
								temp += Left.charAt(m);
								if (Left.charAt(m) == ')') {
									temp = "(Math.pow(Double.parseDouble(Left.substring(" + String.valueOf(beg) + "," + String.valueOf(fin) + "), Double.parseDouble(" + temp +"))))";
									mathL += temp;
									i=j+2;
									Left = Left.substring(m+1);
									System.out.println("refgwergwr     " + Left);
									i=0;
									break;
								}
							}
						}
						else {
							System.out.println("\n" + mathL);
							mathL += Left.charAt(i);
						}
					}
				}
			}
			else {
				mathL += Left.charAt(i);
				System.out.println("\n" + mathL);
			}
		}
		System.out.println("\n" + mathL);
	}*/
	
	public void autoFtrig() {
		String temp = "";
		for (int i = 0; i < equation.length(); i++) {
			if (    equation.charAt(i) == 's' && equation.charAt(i+1) == 'i' && equation.charAt(i+2) == 'n' && equation.charAt(i+3) != '('
				||	equation.charAt(i) == 'c' && equation.charAt(i+1) == 'o' && equation.charAt(i+2) == 's' && equation.charAt(i+3) != '('
				||  equation.charAt(i) == 't' && equation.charAt(i+1) == 'a' && equation.charAt(i+2) == 'n' && equation.charAt(i+3) != '(') {
				for (int j = i; j < equation.length(); j++) {
					if (equation.charAt(j) == '+' || equation.charAt(j) == '-' || equation.charAt(j) == '/' || equation.charAt(j) == '*' || equation.charAt(j) == ' ' || equation.charAt(j) == '=' || equation.charAt(j) == '(' || equation.charAt(j) == ')') {
						break;
					}
					temp += equation.charAt(j);
				}
				
				equation = equation.replace(equation.substring(i,i+temp.length()), temp.substring(0, 3) + "(" + temp.substring(3) + ")");
				temp = "";
			}
		}
		System.out.println(equation);
	}
	public void autoFpowers() {
		String temp1 = "";
		int a = equation.length()-1;
		int b = 0;
		for (int i=0; i < equation.length(); i++) {	
			if (equation.charAt(i) == '^') {
				if (equation.charAt(i+1) != '(') {
					for (int j = i+1; j < equation.length(); j++) {
						if (equation.charAt(j) == '+' || equation.charAt(j) == '-' || equation.charAt(j) == '/' || equation.charAt(j) == '^' || equation.charAt(j) == '*' || equation.charAt(j) == '=' || equation.charAt(j) == ' ' || equation.charAt(j) == '(' || equation.charAt(j) == ')') {
							a=j;
							break;
						}
						temp1 += equation.charAt(j);						
					}
					if (a==equation.length()-1) {
						equation = equation.substring(0,i+1) + "(" + temp1 + ")" + equation.substring(a+1, equation.length());
					}
					else {
						equation = equation.substring(0,i+1) + "(" + temp1 + ")" + equation.substring(a, equation.length());
					}
					
					temp1 = "";
				}
			}
			if (equation.charAt(i) == '^') {	
				if (equation.charAt(i-1) != ')') {
					for (int j = i-1; j>=0; j--) {
						if (equation.charAt(j) == '+' || equation.charAt(j) == '-' || equation.charAt(j) == '/' || equation.charAt(j) == '^' || equation.charAt(j) == '*' || equation.charAt(j) == '=' || equation.charAt(j) == ' ' || equation.charAt(j) == '(' || equation.charAt(j) == ')') {
							b=j;
							break;
						}
					}
					if (b==0) {
						equation = equation.substring(0, b) + "(" + equation.substring(b, i) + ")" + equation.substring(i, equation.length());
					}
					else {
						equation = equation.substring(0, b+1) + "(" + equation.substring(b+1, i) + ")" + equation.substring(i, equation.length());
					}
				}
			}
		}
		equation = equation.substring(0,equation.length()-2);
	}
	
	public void autoFmultiplication() {
		equation = equation.replace(")(", ")*(");
		for (int i = 0; i < equation.length(); i++) {
			if ((equation.charAt(i) == 'x' || equation.charAt(i) == 'y' || equation.charAt(i) == 's' || equation.charAt(i) == 'c' || equation.charAt(i) == 't' || equation.charAt(i) == 'M')
				&& i-1 >=0 && (Character.isDigit(equation.charAt(i-1)) == true || equation.charAt(i-1) == ')' || equation.charAt(i-1) == 'x' ||equation.charAt(i-1) == 'y')) {
				equation = equation.substring(0,i) + "*" + equation.substring(i, equation.length());
				i++;
			}
		}
		for (int i=0; i < equation.length(); i++) {
			if ((Character.isDigit(equation.charAt(i)) == true || equation.charAt(i) == 'x' || equation.charAt(i) == 'y') && i<equation.length()-1 && equation.charAt(i+1) == '(') {
				equation = equation.substring(0, i+1) + '*' + equation.substring(i+1, equation.length());
				i++;
			}
		}
		
	}
	public void toPowers() {
		ArrayList<Character> tempEquation = new ArrayList<Character>();
		int a=0;
		int b=0;
		String ex = "";
		String v = "";
		for (int j=0; j < equation.length(); j++) {
			tempEquation.add(equation.charAt(j));
		}	
		for (int i = 0; i < tempEquation.size(); i++) {
			if (tempEquation.get(i) == '^' && tempEquation.get(i+1) == '(') {

				for (int z=i+1; z < tempEquation.size(); z++) {
					ex += tempEquation.get(z);
					if (tempEquation.get(z) == ')') {
						a=z;
						break;
					}
				}
				for (int y = i-1; y>=0; y--) {;
					if (tempEquation.get(y) == '(') {
						b=y;
						for (int x = y; x < i; x++) {
							v += tempEquation.get(x);
						}
						break;
					}
				}
				equation = equation.replace(equation.substring(b, a+1), "Math.pow(" + v + "," + ex + ")");
				ex = "";
				v = "";
				i+= 9+ v.length() + 1 + ex.length() + 1;
				tempEquation.clear();
				for (int g = 0; g<equation.length(); g++) {
					tempEquation.add(equation.charAt(g));
				}
			}
		}
	}
	
	public void toTrig() {
		ArrayList<Character> tempEquation = new ArrayList<Character>();
		for (int j=0; j < equation.length(); j++) {
			tempEquation.add(equation.charAt(j));
		}	
		for (int d=0; d < tempEquation.size(); d++) {
			if (d+2 < tempEquation.size() && tempEquation.get(d) == 's' && tempEquation.get(d+1) == 'i' && tempEquation.get(d+2) == 'n'
				||	d+2 < tempEquation.size() && tempEquation.get(d) == 'c' && tempEquation.get(d+1) == 'o' && tempEquation.get(d+2) == 's'
				||  d+2 < tempEquation.size() && tempEquation.get(d) == 't' && tempEquation.get(d+1) == 'a' && tempEquation.get(d+2) == 'n') {
					equation =  equation.substring(0,d) + "Math." + equation.substring(d, equation.length());
					d+= 7;
					tempEquation.clear();
					for (int u = 0; u < equation.length(); u++) {
						tempEquation.add(equation.charAt(u));
					}
			}
		}
	}
	public String formatNegative(String equation) {
		int a=0;
		for (int i=0; i< equation.length(); i++) {
			if (equation.charAt(i) == '-' && i-1 >= 0 && (equation.charAt(i-1) == '(' || equation.charAt(i-1) == '*' || equation.charAt(i-1) == '/' || equation.charAt(i-1) == '^' || equation.charAt(i-1) == '/')) {
				for (int j=i; j< equation.length(); j++) {
					if (equation.charAt(j) == '+' || equation.charAt(j) == '-' || equation.charAt(j) == '/' || equation.charAt(j) == '^' || equation.charAt(j) == '*' || equation.charAt(j) == '=' || equation.charAt(j) == ' ' || equation.charAt(j) == ')') {
						a=j;
						break;
					}
				}
				equation = equation.substring(0, i) + "(0" + equation.substring(i,a) + ")" + equation.substring(a, equation.length());
				i=0;
			}
		}
		return equation;
	}
	
	public void assign() {
		xmin = 0;
		xmax = xmin+ox;
		this.x = xmin;
		ymin = -5;
		ymax = ymin+oy;
		this.y = ymin;
		autoFtrig();
		autoFmultiplication();
	}
	
	public void replaceX() {
		equation2 =  equation.replace("x", "(" +String.valueOf(this.x)+")");
	}
	public void replaceY() {
		equation2 = equation3.replace("y", "("+String.valueOf(this.y)+")");
	}
	
	public void getPoints() {
		String Ltemp = "";
		String Rtemp = "";
		double doublesL = 0.0;
		double doublesR = 0.0;
		for (float i = (-sketch.width/2) + sketch.ox; i <= (sketch.width/2) + sketch.ox; i+=0.2) {
			this.x = i;
			equation2=equation;
			replaceX();
			equation3=equation2;
			for (float j = (-sketch.height/2) + sketch.oy; j <= (sketch.height/2) + sketch.oy; j+=0.2) {
				this.y = j;			
				equation2 = equation3;
				replaceY();
				equation2 = formatNegative(equation2);
				LeftRight(equation2);
				Ltemp = evaluateTrig(Left);
				Rtemp = evaluateTrig(Right);
				Ltemp = formatNegative(Ltemp);
				Rtemp = formatNegative(Rtemp);
				doublesL = evaluateExpression(Ltemp);
				doublesR = evaluateExpression(Rtemp);
				if (Math.abs(doublesL-doublesR) <= 0.2/2) {
					pointsX.add(i);
					pointsY.add(-j);
				}
				
			}
		}
		
	}
	
	public void printPoints() {
		for (int i = 0; i < pointsX.size(); i++) {
			System.out.println("(" + pointsX.get(i) + "," + pointsY.get(i) + ")");
		}
	}
	
	public void evalBasicMultandDiv() { //these three methods do not work well
		ArrayList<Character> tempEquation = new ArrayList<Character>();
		int a=0;
		int b=0;
		Double temp1 = 0.0;
		for (int j=0; j < equation.length(); j++) {
			tempEquation.add(equation.charAt(j));
		}	
		for (int i = 0; i < tempEquation.size(); i++) {
			if ((tempEquation.get(i) == '*'|| tempEquation.get(i) == '/')&& tempEquation.get(i+1) != '(' && tempEquation.get(i-1) != ')') {

				for (int z=i+1; z < tempEquation.size(); z++) {
					if (tempEquation.get(z) == '+' || tempEquation.get(z) == '-' || tempEquation.get(z) == '/' || tempEquation.get(z) == '*' || tempEquation.get(z) == '+' || tempEquation.get(z) == '=' || tempEquation.get(z) == ')' || tempEquation.get(z) == '(') {
						a=z;
						break;
					}
				}
				for (int y = i-1; y>=0; y--) {;
					if (tempEquation.get(y) == '+' || tempEquation.get(y) == '-' || tempEquation.get(y) == '/' || tempEquation.get(y) == '*' || tempEquation.get(y) == '+' || tempEquation.get(y) == '=' || tempEquation.get(y) == '(' || tempEquation.get(y) == ')') {
						b=y;
						break;
					}
				}
				if (tempEquation.get(i) == '*') {
					temp1 = Double.parseDouble(equation.substring(a+1, i)) * Double.parseDouble(equation.substring(i+1, b));
					equation = equation.substring(0, a+1) + String.valueOf(temp1) + equation.substring(b-1, equation.length());
					i=0;
				}
				else if (tempEquation.get(i) == '/') {
					temp1 = Double.parseDouble(equation.substring(a+1, i)) / Double.parseDouble(equation.substring(+1, b));
					equation = equation.substring(0, a+1) + String.valueOf(temp1) + equation.substring(b-1, equation.length());
					i=0;
				}
				tempEquation.clear();
				for (int g = 0; g<equation.length(); g++) {
					tempEquation.add(equation.charAt(g));
				}
			}
		}
	}
	
	public void evalBasicAddandSub() {
		ArrayList<Character> tempEquation = new ArrayList<Character>();
		int a=equation.length()-1;
		int b=0;
		for (int j=0; j < equation.length(); j++) {
			tempEquation.add(equation.charAt(j));
		}
		for (int i = 0; i < tempEquation.size(); i++) {
			if ((tempEquation.get(i) == '+'|| tempEquation.get(i) == '-') && tempEquation.get(i+1) != '(' && tempEquation.get(i-1) != ')') {
				for (int z=i+1; z < tempEquation.size(); z++) {
					if (tempEquation.get(z) == '+' || tempEquation.get(z) == '-' || tempEquation.get(z) == '/' || tempEquation.get(z) == '*' || tempEquation.get(z) == '+' || tempEquation.get(z) == '=') {
						a=z;
						break;
					}
				}
				for (int y = i-1; y>=0; y--) {;
					if (tempEquation.get(y) == '+' || tempEquation.get(y) == '-' || tempEquation.get(y) == '/' || tempEquation.get(y) == '*' || tempEquation.get(y) == '+' || tempEquation.get(y) == '=') {
						b=y;
						break;
					}
				}
				if (tempEquation.get(i) == '+') {
					System.out.println(equation);
					equation = equation.replace(equation.substring(b+1,a), String.valueOf(Double.parseDouble(equation.substring(i+1,a)) + Double.parseDouble(equation.substring(b,i))));
					i=0;
				}
				
				else if (tempEquation.get(i) == '-') {
					equation = equation.replace(equation.substring(b,a), String.valueOf( - Double.parseDouble(equation.substring(i+1,a)) + Double.parseDouble(equation.substring(b,i))));
					i=0;
				}
				tempEquation.clear();
				for (int g = 0; g<equation.length(); g++) {
					tempEquation.add(equation.charAt(g));
				}
				a=equation.length();
				b=0;
			}
		}
	}
	
	public void parentheses() {
		ArrayList<Character> tempEquation = new ArrayList<Character>();
		int a=0;
		int b=0;
		Double temp = 0.0;
		for (int j=0; j < equation.length(); j++) {
			tempEquation.add(equation.charAt(j));
		}	
		for (int i = 0; i < tempEquation.size(); i++) {
			if ((tempEquation.get(i) == '+' || tempEquation.get(i) == '-' || tempEquation.get(i) == '/' || tempEquation.get(i) == '*' ) && tempEquation.get(i+1) == '(' || tempEquation.get(i-1) == ')') {

				for (int z=i+1; z < tempEquation.size(); z++) {
					if (tempEquation.get(z) == ')') {
						a=z;
						break;
					}
				}
				for (int y = i-1; y>=0; y--) {;
					if (tempEquation.get(y) == '(') {
						b=y;
						break;
					}
				}
			}
			if (tempEquation.get(i) == '+') {
				temp = Double.parseDouble(equation.substring(i+1, a+1)) + Double.parseDouble(equation.substring(b, i));
				equation = equation.substring(0,b) + String.valueOf(temp) + equation.substring(b, equation.length());
				i=0;
			}
			else if (tempEquation.get(i) == '-') {
				temp = Double.parseDouble(equation.substring(i+1, a+1)) - Double.parseDouble(equation.substring(b, i));
				equation = equation.substring(0,b) + String.valueOf(temp) + equation.substring(b, equation.length());
				i=0;
			}
			else if (tempEquation.get(i) == '/') {
				temp = Double.parseDouble(equation.substring(i+1, a+1)) / Double.parseDouble(equation.substring(b, i));
				equation = equation.substring(0,b) + String.valueOf(temp) + equation.substring(b, equation.length());
				i=0;
			}
			else if (tempEquation.get(i) == '*') {
				temp = Double.parseDouble(equation.substring(i+1, a+1)) * Double.parseDouble(equation.substring(b, i));
				equation = equation.substring(0,b) + String.valueOf(temp) + equation.substring(b, equation.length());
				i=0;
			}
			tempEquation.clear();
			for (int g = 0; g<equation.length(); g++) {
				tempEquation.add(equation.charAt(g));
			}
		}
	}
}


















