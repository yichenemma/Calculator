package calculator;

public class Queue {
	
	// fields
	listNode front;
	listNode rare;
	
	// add the element to the rare
	public void EnQueue(String data) {
		// making a new node
		listNode input = new listNode(data);
		
		// when the queue is empty
		if(front == null && rare == null) {
			
			// front and rare point both towards input node
			front = input;
			rare = input;
		}
		
		// when the queue is not empty
		else {
			// link the input node to the previous rare
			rare.next = input;
			
			// point rare to input
			rare = input;
		}
	}
	
	// retrieve the data from the front
	public String DeQueue() {
		// a string variable to hold the returned value
		String returnedValue;
		
		// when there is only one node in the queue
		if(front == rare) {
			
			// store the value that need to be returned
			returnedValue = front.data;
			
			// set the front and rare to be null
			front = null;
			rare = null;
			return returnedValue;
		}
		// when the queue currently has more than one listNode
		else {
			// get the returned value
			returnedValue = front.data;
			
			// point the front to the next element
			front = front.next;
			return returnedValue;
		}
		
	}
	
	
		
		
	
}
