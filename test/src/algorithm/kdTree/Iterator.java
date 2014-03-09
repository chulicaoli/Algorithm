package algorithm.kdTree;

import java.io.Serializable; 


/**抽象迭代器或遍历器接口*/ 
public interface Iterator<S> extends java.util.Iterator<S> , Serializable 
{ 
 /**返回前一个元素*/ 
 public S prior(); 
 /**判断是否存在前一个元素*/ 
 public boolean hasPrior(); 
} 
