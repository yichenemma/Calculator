package calculator;
import java.util.StringTokenizer;

public class postFix {
	
	final String[][] PRECEDENCE = {{"+", "-"}, {"*", "/"},{"^"}};
	
	
	// STEART OF HELPER METHODS
	/**
	 * @param take an String number as input v
	 * this method checks if the String is a number or not
	 */
	public boolean isNum(String str) {
	
		return Character.isDigit(str.charAt(0));
	}
	
	/**
	 * @param take an String operator as input
	 * @return this method return the precedence of the input operator
	 */
	
	public int precedenceValue(String operator) {
		
		for (int i = 0; i < PRECEDENCE.length; i++) {
			for (int j = 0; j < PRECEDENCE[i].length; j++) {
				if (PRECEDENCE[i][j].equals(operator)) {
					return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * @param take an String operator as input
	 * the method is for checking whether the input is an operator or not
	 * @return this method return the boolean 
	 */
	public boolean isOperator(String operator) {
		return operator.equals("^") || operator.equals("*") || 
				operator.equals("/") || operator.equals("+") || operator.equals("-");
	}
	
	/**
	 * @param take an String operator as input
	 * the method is for checking whether the input is a left associative operator or not
	 * @return this method return the boolean 
	 */
	public boolean isLeftAssociative(String operator) {
		return operator.equals("*") || operator.equals("/") || operator.equals("+") || operator.equals("-");
	}
	
	public double doExpression(Queue Qin) {
		
		
		Queue outputQueue = new Queue();	
		Stack operatorStack = new Stack();
		
		
		// if t has more tokens
		while (Qin.front != null) {
						
			String cur = Qin.DeQueue();

						
			// if the token is a num
			if (isNum(cur)) {
							
				// push it to the output queue
				outputQueue.EnQueue(cur);
			}
						
			// when the token is an operator
			else if (isOperator(cur)){
							
							
				while (operatorStack.getTop() != null &&
					((isOperator(operatorStack.getTop()) && precedenceValue(operatorStack.getTop()) > precedenceValue(cur))
					|| (isOperator(operatorStack.getTop()) && precedenceValue(operatorStack.getTop()) == precedenceValue(cur) && isLeftAssociative(cur))
					&&  !operatorStack.getTop().equals("("))){
									
					// pop the operators from the stack onto output queue
					outputQueue.EnQueue(operatorStack.pop());
									 
				}
								
								// push cur onto the operator stack
				operatorStack.push(cur);	
								
			}
							
						
			// if the token is (
			else if (cur.equals("(")) {
							
				// push onto the operation stack
				operatorStack.push(cur);
			}
						
			// if the token is )
			else if (cur.equals(")")) {
							
				try {
					while (!operatorStack.getTop().equals("(")) {
								
						// pop the operators from the stack onto output queue
						outputQueue.EnQueue(operatorStack.pop());
								
						/* handling the parenthesis doesn't match case */
								
						}
					if (operatorStack.getTop().equals("(")) {
								
						operatorStack.pop();
						}
					}
				catch (NullPointerException e) {
								
					operatorStack = null;
				}
							
			}
						
		}
				
		try {	
						
			while (operatorStack.getTop() != null) {
							
				String curElement = operatorStack.pop();
							
				// check if it's a parenthesis
				if (curElement.contentEquals(")") || curElement.contentEquals("(") ) {
					// set the operatorStack to null so the NullPointerException can be catched later
					operatorStack = null;
					}
							
				else {
					outputQueue.EnQueue( curElement );
					}
			}
					
		}
					
			catch(NullPointerException e) {
					// catch the exception so that program won't crash
				}
						
		double result = PostEval(outputQueue);
		return result;
			
	}
	
	private double PostEval(Queue PostFix) {
		
		// used to store the result
		Double result;
		Stack stack = new Stack();
		String OP_A;
		String OP_B;
		Double A;
		Double B;
		
		// take an element from the PostFix queue
		String token;
		
		// keep retrieving data from the PistFix until it's empty
		while (PostFix.front != null) {
			// read a token from the queue
			token = PostFix.DeQueue();
		
			// testing token...
			switch (token) {
		
			// if the token is an operator
			case "+":
				// pop two operands from the stack
				OP_A = stack.pop();
				OP_B = stack.pop();
					
				// convert the String into double
				A = Double.parseDouble(OP_A);
				B = Double.parseDouble(OP_B);
				
				// calculate the result
				result = B + A;
				
				// push it back to the stack
				stack.push(Double.toString(result));
				break;
				
			case "-":
				// pop two operands from the stack
				OP_A = stack.pop();
				OP_B = stack.pop();
					
				// convert the String into double
				A = Double.parseDouble(OP_A);
				B = Double.parseDouble(OP_B);
				
				// calculate the result
				result = B - A;
				
				// push it back to the stack
				stack.push(Double.toString(result));
				break;
				
			case "*":
				// pop two operands from the stack
				OP_A = stack.pop();
				OP_B = stack.pop();
					
				// convert the String into double
				A = Double.parseDouble(OP_A);
				B = Double.parseDouble(OP_B);
				
				// calculate the result
				result = B * A;
				
				// push it back to the stack
				stack.push(Double.toString(result));
				break;
				
			case "/":
				// pop two operands from the stack
				OP_A = stack.pop();
				OP_B = stack.pop();
					
				// convert the String into double
				A = Double.parseDouble(OP_A);
				B = Double.parseDouble(OP_B);
				
				// calculate the result
				result = B / A;
				
				// push it back to the stack
				stack.push(Double.toString(result));
				break;
			case "^":
				OP_A = stack.pop();
				OP_B = stack.pop();
					
				// convert the String into double
				A = Double.parseDouble(OP_A);
				B = Double.parseDouble(OP_B);
				
				// calculate the result
				result = Math.pow(B, A);
				
				// push it back to the stack
				stack.push(Double.toString(result));
				
				break;
				// if token is an operand
			default:
				stack.push(token);
				break;
			}	
		}
		result = Double.parseDouble(stack.pop());
		
		return result;
	}
	
	// push the string arg into Qin
	public void parse(String arg, Queue Qin) {
		// make a StringTokenizer
		StringTokenizer t = new StringTokenizer(arg, "/+*-^()", true);
		
		// bulidng up the Qin
		while (t.hasMoreTokens()) {
			String curToken = t.nextToken();
			// strip the empty 
			curToken  = curToken.strip();
		
			Qin.EnQueue(curToken);
			
		}
	}
	
}
