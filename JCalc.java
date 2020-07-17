package calculator;
import acm.program.ConsoleProgram;

public class JCalc extends ConsoleProgram{
	
	Queue Qin = new Queue();
	postFix pf = new postFix();
	
	public void run() {
		
		println("Infix expression evaluator, enter expression of blank line to exit");
		
		while (true) {
			
			String input = readLine("expr: ");
			
			if(input.contentEquals(""))break;
			pf.parse(input, Qin);
			double result = pf.doExpression(Qin); 
			println(input+" = "+result);
			
		}
		println("Program terminated.");
	}

}
