package algorithm.kdTree;

import java.io.Serializable;
import java.util.Iterator;
import algorithm.kdTree.Data;

public class Deque<S> implements Iterable<S>, Serializable {
	/** 指向对头的引用 */
	protected Data<S> front;// 指向对头的引用
	/** 指向队尾的引用 */
	protected Data<S> rear;// 指向队尾的引用
	/** 队列的长度 */
	protected int size;// 队列的长度

	/** 构造一个空队列 */
	public Deque()// 构造一个空队列
	{
		front = new Data<S>();
		rear = front;
		this.front.setNext(null);
		size = 0;
	}

	/** 返回队列的长度 */
	public int length()// 返回队列的长度
	{
		return this.size;
	}

	/** 返回指向队首元素的数据,若队为空，返回 null */
	public S getFrontData()// 返回指向队首元素的数据
	{
		if (this.front.getNext() == null)
			return (S) this.front.getNext().getData();
		return null;
	}

	/** 返回指向队首元素的引用,若队为空，返回 null */
	public Data<S> getFront()// 返回指向队首元素的引用
	{
		return this.front.getNext();
	}

	/** 返回指向队尾元素的数据,若队为空，返回 null */
	public S getRearData()// 返回指向队尾元素的数据
	{
		return (S) this.rear.getData();
	}

	/** 返回指向队尾元素的引用,若队为空，返回 null */
	public Data<S> getRear()// 返回指向队尾元素的引用
	{
		return this.rear;
	}

	/** 判断队列是否为空，若为空返回 true 否则返回 false */
	public boolean isEmpty()// 判断队列是否为空
	{
		if (this.front == this.rear)
			return true;
		return false;
	}

	/** 销毁队列 */
	public void clear()// 销毁队列
	{
		if (this.isEmpty() != true) {
			this.rear = this.front;
			this.front.setNext(null);
			this.size = 0;
			System.gc();
		}
	}

	/** 从队尾将数据 data 进队 */
	public void rearEnQueue(S data)// 尾进队
	{
		Data<S> a = new Data<S>();
		if (a == null)
			System.exit(1);
		a.setData(data);
		a.setPrior(rear);
		this.rear.setNext(a);
		this.rear = a;
		this.size++;
	}

	/** 从队首将数据 data 进队 */
	public void frontEnQueue(S data)// 前进队
	{
		Data<S> a = new Data<S>();
		if (a == null)
			System.exit(1);
		a.setData(data);
		a.setNext(this.front.getNext());
		if (a.getNext() != null)
			a.getNext().setPrior(a);
		else
			this.rear = a;
		a.setPrior(this.front);
		this.front.setNext(a);
		this.size++;
	}

	/** 从队首出队并返回删除的那个数据，若队为空返回 null */
	public S frontDequeue()// 前出队并返回删除的那个数据
	{
		if (this.isEmpty() != true) {
			S a = (S) this.front.getNext().getData();
			Data<S> b = this.front.getNext();
			this.front.setNext(b.getNext());
			if (b.getNext() != null)
				b.getNext().setPrior(b.getPrior());
			else
				this.rear = this.front;
			this.size--;
			return a;
		}
		return null;
	}

	/** 从队尾出队并返回删除的那个数据，若队为空返回 null */
	public S rearDequeue()// 后出队并返回删除的那个数据
	{
		if (this.isEmpty() != true) {
			S a = (S) this.getRear().getData();
			this.rear = this.rear.getPrior();
			this.rear.setNext(null);
			this.size--;
			return a;
		}
		return null;
	}

	/** 判断当前队列与队列 a 是否相等，若相等返回 true 否则返回 false */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof Deque))
			return false;
		Deque<S> a = (Deque<S>) o;
		Data<S> cur1 = this.front.getNext();
		Data<S> cur2 = a.front.getNext();
		while (cur1 != null && cur2 != null) {
			if (cur1.getData().equals(cur2.getData()) != true)
				return false;
			cur1 = cur1.getNext();
			cur2 = cur2.getNext();
		}
		if (cur1 == null && cur2 == null)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 11 * hash + (this.front != null ? this.front.hashCode() : 0);
		hash = 11 * hash + (this.rear != null ? this.rear.hashCode() : 0);
		hash = 11 * hash + this.size;
		return hash;
	}

	/** 返回遍历器 */
	@Override
	public Iterator<S> iterator() {
		if (this.front != null)
			return this.front.iterator();
		return null;
	}

	/** 将队列对象转化为字符串 */
	@Override
	public String toString()// 将队列对象转化为字符串
	{
		StringBuilder a = new StringBuilder("队列里的元素从顶到底依次为：\n");
		int i = 1;
		Data<S> current = this.front.getNext();
		if (current == null)
			a.append("此队列为空队列");
		while (current != null) {
			a.append("\n 第 ").append(i).append(" 个 元 素 为 ：").append(
					current.getData());
			i++;
			current = current.getNext();
		}
		return a.toString();
	}
}
