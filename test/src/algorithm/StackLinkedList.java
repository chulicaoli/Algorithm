package algorithm;

public class StackLinkedList {
	private class Node {
		String item;
		Node next;
	}

	private Node firstNode;
	private int n;

	public boolean isEmpty() {
		return firstNode == null;
	}

	public int size() {
		return n;
	}

	public void push(String item) {
		Node oldfirst = firstNode;
		firstNode = new Node();
		firstNode.item = item;
		firstNode.next = oldfirst;
		n++;
	}

	public Node pop() {
		if (n == 0) {
			return null;
		}
		Node item = firstNode;
		firstNode = firstNode.next;
		n--;
		return item;
	}
}
