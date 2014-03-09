package algorithm.kdTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
im
public class BinaryHeap<S> implements Iterable<S>, Serializable {  /** 存储数据的动态数组 */ 
	 protected ArrayList<S> data; 
	 /** 堆中数据的个数 */ 
	 protected int num; 
	 /** 比较器 */ 
	 protected Comparator<S> cp; 
	 
	 /** 构造函数初始化 */ 
	 public BinaryHeap() { 
	 data = new ArrayList<S>(100); 
	 num = 0; 
	 cp = null; 
	 } 
	 /** 构造函数初始化 */ 
	 public BinaryHeap(Comparator<S> cp) throws IllegalParameterException { 
	 data = new ArrayList<S>(100); 
	 num = 0; 
	 if (cp == null) 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 this.cp = cp; 
	 } 
	 
	 /** 构造函数初始化 */ 
	 public BinaryHeap(S datas[]) throws IllegalParameterException { 
	 data = new ArrayList<S>(100); 
	 num = 0; 
	 if (datas == null) 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 else { 
	 if (Comparable.class.isAssignableFrom(datas[0].getClass())) { 
	 CreateComparator<S> cc = new CreateComparator<S>(); 
	 cp = cc.getComparator(); 
	 } else { 
	 cp = null; 
	 } 
	 this.add(datas); 
	 } 
	 } 
	 
	 /** 构造函数初始化 */ 
	 public BinaryHeap(S datas[], Comparator<S> cp) 
	 throws IllegalParameterException { 
	 data = new ArrayList<S>(100);  num = 0; 
	 if (datas == null || cp == null) 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 else { 
	 this.cp = cp; 
	 this.add(datas); 
	 } 
	 }

	 /** 设置比较器 */ 
	 public void setComparator(Comparator<S> cp) { 
	 this.cp = cp; 
	 } 
	 
	 /** 返回比较器 */ 
	 public Comparator<S> getComparator() { 
	 return cp; 
	 } 
	 
	 /** 判断堆是否为空 */ 
	 public boolean isEmpty() { 
	 return num == 0; 
	 } 
	 
	 /** 返回堆的大小 */ 
	 public int size() { 
	 return num; 
	 } 
	 
	 /** 获取列表数据 */ 
	 public List<S> getDatas() { 
	 return this.data; 
	 } 
	 
	 /** 返回二叉堆中的第 i 个数据（i 从 1 开始） */ 
	 public S getIndex(int i) throws IllegalParameterException { 
	 if (i < 1 || i > num) { 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 } else 
	 return data.get(i); 
	 } 
	 
	 /** 向上浮动函数 */ 
	 public void shiftUp(int hole) {  if (hole == 1) 
	 return; 
	 S a = this.data.get(hole); 
	 for (; hole > 1 && cp.compare(a, data.get(hole / 2)) < 0; hole /= 2) 
	{ 
	 data.set(hole, data.get(hole / 2)); 
	 } 
	 data.set(hole, a); 
	 } 
	 /** 向下过滤函数 */ 
	 public void percolateDown(int hole) { 
	 S a = data.get(hole); 
	 int child = 0; 
	 for (; hole * 2 <= num; hole = child) { 
	 child = hole * 2; 
	 if (child != num 
	 && cp.compare(data.get(child + 1), 
	data.get(child)) < 0) { 
	 child++; 
	 } 
	 if (cp.compare(data.get(child), a) < 0) { 
	 data.set(hole, data.get(child)); 
	 } else 
	 break; 
	 } 
	 data.set(hole, a); 
	 } 
	 
	 /** 获取堆顶元素 */ 
	 public S getTopElement() { 
	 return data.get(1); 
	 } 

	 /** 向堆中插入元素 */ 
	 public void add(S a) throws IllegalParameterException { 
	 if (a == null) 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 else { 
	 if (cp == null) { 
	 if (Comparable.class.isAssignableFrom(a.getClass())) { 
	 CreateComparator<S> cc = new 
	CreateComparator<S>(); 
	 cp = cc.getComparator();  } else { 
	 cp = null; 
	 } 
	 } 
	 if (cp == null) 
	 throw new IllegalParameterException("比较器为 null 值！ 
	"); 
	 ++num; 
	 if (data.isEmpty()) 
	 data.add((S) new Object()); 
	 while (data.size() < num + 1) 
	 data.add(a); 
	 data.set(num, a); 
	 this.shiftUp(num); 
	 } 
	 } 
 
	
	/** 向堆中加入元素 */ 
	 public final void add(S datas[]) throws IllegalParameterException { 
	 if (datas == null) 
	 throw new IllegalParameterException("输入的参数不合法！"); 
	 else { 
	 for (int i = 0; i < datas.length; i++) { 
	 this.add(datas[i]); 
	 } 
	 } 
	 } 
	 
	 /** 返回迭代器 */ 
	 @Override 
	 public Iterator<S> iterator() { 
	 return this.data.listIterator(1); 
	 } 
	 
	 /** 从堆中弹出顶部元素，并删除 */ 
	 public S pop() { 
	 if (num <= 0) 
	 return null; 
	 S min = data.get(1); 
	 delete(1); 
	 return min; 
	 } 
	 
	 /** 从堆中删除位于 i>=1&&i<num 好的元素 */
	if (index > num || index < 1 || num <= 0) 
	 return null; 
	 S a = data.get(index); 
	 S b = data.get(num); 
	 data.set(index, data.get(num--)); 
	 if (cp.compare(a, b) > 0) 
	 this.shiftUp(index); 
	 else 
	 this.percolateDown(index); 
	 return a; 
	 } 
	 
	 /** 将二叉堆调整为当前大小 */ 
	 public void trimToSize() { 
	 data.trimToSize(); 
	 } 
	 
	 @Override 
	 public String toString() { 
	 StringBuilder a = new StringBuilder("2 叉堆中的元素依次为：\n["); 
	 for (int i = 1; i < num; i++) 
	 a.append(" ").append(data.get(i)).append(","); 
	 if (num > 0) 
	 a.append(data.get(num)).append(" ]"); 
	 else 
	 a.append("堆为空]"); 
	 return a.toString(); 
	 } 
	} 

