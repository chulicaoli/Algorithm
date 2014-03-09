package algorithm;

import java.util.LinkedList;

//双向队列
public class Deque<T> {
	private LinkedList<T> deque = new LinkedList<T>();

	public void addFirst(T e) {
		deque.addFirst(e);
	}

	public void addLast(T e) {
		deque.addLast(e);
	}

	public T getFirst() {
		return deque.getFirst();
	}

	public T getLast() {
		return deque.getLast();
	}

	public T removeFirst() {
		return deque.removeFirst();
	}

	public T removeLast() {
		return deque.removeLast();
	}

	public int size() {
		return deque.size();
	}

	public String toString() {
		return deque.toString();
	}

	public static void fill(Deque<Integer> deque) {
		for (int i = 20; i < 27; i++) {
			deque.addFirst(i);
		}
		for (int i = 50; i < 55; i++) {
			deque.addLast(i);
		}
	}

	public static void main(String[] args) {
		Deque<Integer> di = new Deque<Integer>();
		fill(di);
		System.out.println(di);
		while (di.size() != 0) {
			System.out.print(di.removeFirst() + " ");
		}
		System.out.println();
		System.out.println(di);
		fill(di);
		while (di.size() != 0) {
			System.out.print(di.removeLast() + " ");
		}
	}
}