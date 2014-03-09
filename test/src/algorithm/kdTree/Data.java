package algorithm.kdTree;

import java.io.Serializable;
import java.util.Iterator;

/** 此数据类型为栈和队列中的数据元素类型 */
public class Data<S> implements Iterable<S>, Serializable {
	/** 数据元素可以是任何数据类型 */
	protected S data;// 数据元素
	/** 当前元素的下一个元素的引用 */
	protected Data<S> next;// 当前元素的下一个元素的引用
	/** 当前元素的前一个元素的引用 */
	protected Data<S> prior;// 当前元素的下一个元素的引用

	/** 默认构造函数 */
	public Data() {
		this.data = null;
		this.next = null;
		this.prior = null;
	}

	/** 设置当前对象的数据 */
	public void setData(S data) {
		this.data = data;
	}

	/** 返回当前对象的数据 */
	public S getData() {
		return this.data;
	}

	/** 设置当前对象的下一个结点的引用 */
	public void setNext(Data<S> next) {
		this.next = next;
	}

	/** 返回当前对象的下一个结点的引用 */
	public Data<S> getNext() {
		return this.next;
	}

	/** 设置当前对象的前一个结点的引用 */
	public void setPrior(Data<S> prior) {
		this.prior = prior;
	}

	/** 返回当前对象的前一个结点的引用 */
	public Data<S> getPrior() {
		return this.prior;
	}

	/** 判断数据是否相等 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof Data))
			return false;
		Data<S> a = (Data<S>) o;
		if (this.data.equals(a.data))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (this.data != null ? this.data.hashCode() : 0);
		return hash;
	}

	/** 返回遍历器 */
	@Override
	public Iterator<S> iterator() {
		Data<S> c = this;
		class DataIterator<S> implements Iterator<S> {
			Data<S> cur;

			public DataIterator(Data<S> c) {
				cur = c;
			}

			@Override
			public S next() {
				cur = cur.getNext();
				return cur.getData();
			}

		
			public S prior() {
				cur = cur.getPrior();
				return cur.getData();
			}

			@Override
			public boolean hasNext() {
				if (cur.getNext() != null)
					return true;
				return false;
			}

		
			public boolean hasPrior() {
				if (cur.getPrior() != null && cur.getPrior().getData() != null)
					return true;
				return false;
			}

			@Override
			public void remove() {
				if (cur != null && cur.getPrior() != null) {
					if (cur.getNext() != null) {
						cur.getPrior().setNext(cur.getNext());
						cur.getNext().setPrior(cur.getPrior());
						cur = cur.getPrior();
					} else {
						cur = cur.getPrior();
						cur.setNext(null);
					}
				} else if (cur != null && cur.getPrior() == null
						&& cur.getData() != null) {
					cur = null;
				}
			}
		}
		DataIterator<S> iterator = new DataIterator(c);
		return iterator;
	}

	/** 将当前对象的字符串表示 */
	@Override
	public String toString() {
		String a = "";
		a += this.data;
		return a;
	}
}