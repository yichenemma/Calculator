package calculator;
public class Stack {
	//fields
	listNode top;
	
	public void push(String data) {
		// making new node
		listNode input = new listNode(data);
		
		// if the Stack is empty
		if (top == null) {
			
			top = input;
		}
		// when the Stack is not empty
		else {
			
			// link the input listNode to the current Stack
			input.next = top;
			
			// move the top
			top = input;
		}
	}
	
	public String pop() {
		
		// string variable to hold the returned value
		String returnedValue;
		
		// if the stack only has one element left
		if (top.next == null) {
			
			// stored the returned value
			returnedValue = top.data;
			
			// set the top to become null
			top = null;
			
			return returnedValue;
		}
		// when there are more than 1 elements left in the stack
		else {
			// store the returned value
			returnedValue = top.data;
			
			// move the top pointer
			top = top.next;
			
			return returnedValue;
		}
	}
	
	public String getTop() {
		if (top != null) {
			
			return top.data;
		}
		return null;
	}
}
