package algorithm.kdTree;

public class KDTreeNode<S> {
	 /**左子结点*/
	 public KDTreeNode<S> left=null;
	 /**右子结点*/
	 public KDTreeNode<S> right=null;
	 /**父结点*/
	 public KDTreeNode<S> parent=null;
	 /**表示结点是否被删除*/
	 public boolean isRemoved=false;
	 /**当前所在的维度*/
	 public byte i=0;
	 /**结点数据*/
	 public S data;
	 /**默认构造函数*/
	 public KDTreeNode() {
	 }
	 /**根据数据构造*/
	 public KDTreeNode(S data) {
	 super();
	 this.data = data;
	 }
	 /**是否为叶子节点*/
	 public boolean isLeaf(){
	 return (left==null&&right==null);
	 }
	 /**删除树*/
	 public void clear()
	 {
	 left=null;
	 right=null;
	 isRemoved=false;
	 data=null;
	 }
	 @Override
	 public String toString() {
	 return ""+data;
	 }
	}