package algorithm.kdTree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import algorithm.kdTree.BinaryHeap;
import algorithm.kdTree.Deque;
import LuoSen.DS.DS.Stack;
import algorithm.kdTree.IllegalParameterException;
import algorithm.kdTree.KDDistance;
public class KDTree<S> implements Iterable<S> {
 /** 根节点 */
 private KDTreeNode<S> root = null;
 /** 树终结点的数目 */
 private int size = 0;
 /** 树的维度 */
 private int k = 0;
 /** k个维度的比较器列表 */
 private ArrayList<Comparator<S>> cp = null;
 /**计算两个数据之间的距离*/
 private KDDistance<S> kdd=null;
 public KDTree(int k) {
 root = new KDTreeNode<S>();
 root.parent = root;
 this.k = k;
 }
 public ArrayList<Comparator<S>> getCp() {
 return cp;
 }
 public void setCp(ArrayList<Comparator<S>> cp) {
 this.cp = cp;
 }
 public KDDistance<S> getKdd() {
 return kdd;
 }
 public void setKdd(KDDistance<S> kdd) {
 this.kdd = kdd;
 }
 public void buildKDTree(List<S> datas) {
 if (datas == null || datas.isEmpty())
 return;
 if (datas.size() == 1) {
 this.insert(datas.get(0));
 return;
 }
 int i = 0;
 Collections.sort(datas, cp.get(i));
 S mid = datas.get(datas.size() / 2 - 1);
 this.insert(mid);
 i++;
 i %= k;
 if (datas.size() >= 2) {
 buildKDTree(datas.subList(0, datas.size() / 2 - 1), i);
 buildKDTree(datas.subList(datas.size() / 2, datas.size()), i);
 } else {
 buildKDTree(datas, i);
 }
 }
 private void buildKDTree(List<S> datas, int i) {
 if (datas == null || datas.isEmpty())
 return;
 if (datas.size() == 1) {
 this.insert(datas.get(0));
 return;
 }
 Collections.sort(datas, cp.get(i));
 S mid = datas.get(datas.size() / 2 - 1);
 this.insert(mid);
 i++;
 i %= k;
 if (datas.size() >= 2) {
 buildKDTree(datas.subList(0, datas.size() / 2 - 1), i);
 buildKDTree(datas.subList(datas.size() / 2, datas.size()), i);
 } else {
 buildKDTree(datas, i);
 }
 }
 /** 插入数据 ,如果data==null返回false,若已存在关键字相同的数据会替换*/
 public boolean insert(S data) {
 if (data == null)
 return false;
 if (size == 0) {
 root.data = data;
 root.i=0;
 root.isRemoved=false;
 size++;
 return true;
 } else {
 return insert(root, data, 0);
 }
 }
 /** 插入数据,如果data==null返回false */
 private boolean insert(KDTreeNode<S> kdtn, S data, int i) {
 KDTreeNode<S> newNode = null;
 if (cp.get(i).compare(data, kdtn.data) < 0) {
 if (kdtn.left == null) {
 newNode = new KDTreeNode<S>(data);
 kdtn.left = newNode;
 newNode.parent = kdtn;
 newNode.i=(byte) (++i%k);
 size++;
 return true;
 } else {
 i++;
 i %= k;
 return insert(kdtn.left, data, i);
 }
 } else if (cp.get(i).compare(data, kdtn.data) > 0) {
 if (kdtn.right == null) {
 newNode = new KDTreeNode<S>(data);
 kdtn.right = newNode;
 newNode.parent = kdtn;
 newNode.i=(byte) (++i%k);
 size++;
 return true;
 } else {
 i++;
 i %= k;
 return insert(kdtn.right, data, i);
 }
 } else {
 if (kdtn.isRemoved) {
 kdtn.data = data;
 kdtn.isRemoved=false;
 size++;
 return true;
 }
 if (this.isEquals(kdtn.data, data)) {
 kdtn.data=data;
 return true;
 } else {
 if (kdtn.left == null) {
 newNode = new KDTreeNode<S>(data);
 kdtn.left = newNode;
 newNode.parent = kdtn;
 newNode.i=(byte) (++i%k);
 size++;
 return true;
 } else {
 i++;
 i %= k;
 return insert(kdtn.left, data, i);
 }
 }
 }
 }
 /** 删除数据 */
 public boolean remove(S data) {
 if (data == null || size == 0)
 return false;
 return remove(root, data, 0);
 }
 /** 删除数据 */
 private boolean remove(KDTreeNode<S> kdtn, S data, int i) {
 if (cp.get(i).compare(data, kdtn.data) < 0) {
 if (kdtn.left == null) {
 return false;
 } else {
 i++;
 i %= k;
 return remove(kdtn.left, data, i);
 }
 } else if (cp.get(i).compare(data, kdtn.data) > 0) {
 if (kdtn.right == null) {
 return false;
 } else {
 i++;
 i %= k;
 return remove(kdtn.right, data, i);
 }
 } else {
 if (this.isEquals(kdtn.data, data)) {
 if (kdtn.isRemoved)
 return false;
 kdtn.isRemoved = true;
 size--;
 if (kdtn.isLeaf()) {
 if (kdtn != root) {
 if (kdtn.parent.left == kdtn)
 kdtn.parent.left = null;
 else
 kdtn.right = null;
 }
 }
 return true;
 } else {
 if (kdtn.left == null) {
 return false;
 } else {
 i++;
 i %= k;
 return remove(kdtn.left, data, i);
 }
 }
 }
 }
 /** 查询对应对数关键字所对应的节点 */
 public KDTreeNode<S> search(S data) {
 if (size == 0 || data == null)
 return null;
 return search(root, data, 0);
 }
 /** 查询对应对数关键字所对应的节点 */
 private KDTreeNode<S> search(KDTreeNode<S> kdtn, S data, int i) {
 if (cp.get(i).compare(data, kdtn.data) < 0) {
 if (kdtn.left == null) {
 return null;
 } else {
 i++;
 i %= k;
 return search(kdtn.left, data, i);
 }
 } else if (cp.get(i).compare(data, kdtn.data) > 0) {
 if (kdtn.right == null) {
 return null;
 } else {
 i++;
 i %= k;
 return search(kdtn.right, data, i);
 }
 } else {
 if (this.isEquals(kdtn.data, data)) {
 if(kdtn.isRemoved)
 return null;
 else
 return kdtn;
 } else {
 if (kdtn.left == null) {
 return null;
 } else {
 i++;
 i %= k;
 return search(kdtn.left, data, i);
 }
 }
 }
 }
 /** 先序遍历KD树，返回迭代器，迭代器中元素为S类型 */
 public Iterator<S> PreOrderTraverse() {
 Deque<S> a = new Deque<S>();
 this.PreOrderTraverse(root, a);
 return new AbstractTreeDataIterator<S>(this, a);
 }
 /** 先序遍历KD树 */
 protected void PreOrderTraverse(KDTreeNode<S> S, Deque<S> a) {
 if (S != null) {
 if(!S.isRemoved)
 a.frontEnQueue(S.data);
 this.PreOrderTraverse(S.left, a);
 this.PreOrderTraverse(S.right, a);
 }
 }
 /** 中序遍历KD树，返回迭代器，迭代器中元素为S类型 */
 public Iterator<S> InOrderTraverse() {
 Deque<S> a = new Deque<S>();
 this.InOrderTraverse(root, a);
 return new AbstractTreeDataIterator<S>(this, a);
 }
 /** 中序遍历KD树 */
 protected void InOrderTraverse(KDTreeNode<S> S, Deque<S> a) {
 if (S != null) {
 this.InOrderTraverse(S.left, a);
 if(!S.isRemoved)
 a.frontEnQueue(S.data);
 this.InOrderTraverse(S.right, a);
 }
 }
 /** 后序遍历KD树，返回迭代器，迭代器中元素为S类型 */
 public Iterator<S> PostOrderTraverse() {
 Deque<S> a = new Deque<S>();
 this.PostOrderTraverse(root, a);
 return new AbstractTreeDataIterator<S>(this, a);
 }
 /** 后序遍历KD树 */
 protected void PostOrderTraverse(KDTreeNode<S> S, Deque<S> a) {
 if (S != null) {
 this.PostOrderTraverse(S.left, a);
 this.PostOrderTraverse(S.right, a);
 if(!S.isRemoved)
 a.frontEnQueue(S.data);
 }
 }
 /** 先序遍历KD树，返回迭代器，迭代器中元素为KDTreeNode类型 */
 public Iterator<KDTreeNode<S>> PreOrderNodeTraverse() {
 Deque<KDTreeNode<S>> a = new Deque<KDTreeNode<S>>();
 this.PreOrderNodeTraverse(root, a);
 return new AbstractTreeNodeDataIterator<KDTreeNode<S>>(this, a);
 }
 /** 先序遍历KD树 */
 protected void PreOrderNodeTraverse(KDTreeNode<S> S, Deque<KDTreeNode<S>> a) {
 if (S != null) {
 if(!S.isRemoved)
 a.frontEnQueue(S);
 this.PreOrderNodeTraverse(S.left, a);
 this.PreOrderNodeTraverse(S.right, a);
 }
 }
 /** 中序遍历KD树，返回迭代器，迭代器中元素为KDTreeNode类型 */
 public Iterator<KDTreeNode<S>> InOrderNodeTraverse() {
 Deque<KDTreeNode<S>> a = new Deque<KDTreeNode<S>>();
 this.InOrderNodeTraverse(root, a);
 return new AbstractTreeNodeDataIterator<KDTreeNode<S>>(this, a);
 }
 /** 中序遍历KD树 */
 protected void InOrderNodeTraverse(KDTreeNode<S> S, Deque<KDTreeNode<S>> a) {
 if (S != null) {
 this.InOrderNodeTraverse(S.left, a);
 if(!S.isRemoved)
 a.frontEnQueue(S);
 this.InOrderNodeTraverse(S.right, a);
 }
 }
 /** 后序遍历KD树，返回迭代器，迭代器中元素为KDTreeNode类型 */
 public Iterator<KDTreeNode<S>> PostOrderNodeTraverse() {
 Deque<KDTreeNode<S>> a = new Deque<KDTreeNode<S>>();
 this.PostOrderNodeTraverse(root, a);
 return new AbstractTreeNodeDataIterator<KDTreeNode<S>>(this, a);
 }
 /** 后序遍历KD树 */
 protected void PostOrderNodeTraverse(KDTreeNode<S> S, Deque<KDTreeNode<S>> a) {
 if (S != null) {
 this.PostOrderNodeTraverse(S.left, a);
 this.PostOrderNodeTraverse(S.right, a);
 if(!S.isRemoved)
 a.frontEnQueue(S);
 }
 }
 /** 判断树种是否存在关键字和data相同的数据结点 */
 public boolean contains(S data) {
 return search(data) != null ? true : false;
 }
 /** 返回KD树的深度 */
 public int getDepth()// 返回KD树的深度
 {
 return this.coutDepth();
 }
 /** 返回KD树节点的数目 */
 public int getNodeNum()// 返回KD树节点的数目
 {
 return size;
 }
 /** 计算KD树的深度 */
 protected int coutDepth() {
 int depth = this.coutDepth(this.root);
 return depth == 0 ? 0 : depth - 1;
 }
 /** 计算KD树的深度 */
 protected int coutDepth(KDTreeNode<S> S) {
 if (S == null)
 return 0;
 int h1 = this.coutDepth(S.left);
 int h2 = this.coutDepth(S.right);
 return (Math.max(h1, h2) + 1);
 }
 /** 删除树 */
 public void clear() {
 root.clear();
 }
 /** 判断KD树是否为空 */
 public boolean isEmpty() {
 if (this.root == null || this.root.data == null)
 return true;
 return false;
 }
 /** 转化为字符串 */
 public void toString(KDTreeNode<S> kdtn, StringBuilder sb) {
 if (kdtn != null) {
 if (!kdtn.isRemoved && kdtn.data != null)
 sb.append(kdtn).append("\n");
 toString(kdtn.left, sb);
 toString(kdtn.right, sb);
 }
 }
 /** 先序转化为字符串 */
 public String toString() {
 StringBuilder sb = new StringBuilder();
 sb.append("树中结点的数目：" + size + "\n");
 toString(root, sb);
 return sb.toString();
 }
 /** 抽象树的数据的迭代器 */
 public static class AbstractTreeDataIterator<S> implements Iterator<S> {
 private Deque<S> D = null;
 private KDTree<S> tree;
 public AbstractTreeDataIterator(KDTree<S> tree, Deque<S> D) {
 this.tree = tree;
 this.D = D;
 }
 @Override
 public boolean hasNext() {
 return !D.isEmpty();
 }
 @Override
 public S next() {
 return D.rearDequeue();
 }
 @Override
 public void remove() {
 S a = D.rearDequeue();
 tree.remove(a);
 }
 }
 /** 抽象树的结点的迭代器 */
 public static class AbstractTreeNodeDataIterator<S> implements Iterator<S> {
 private Deque<S> D = null;
 private KDTree tree;
 public AbstractTreeNodeDataIterator(KDTree tree, Deque<S> D) {
 this.tree = tree;
 this.D = D;
 }
 @Override
 public boolean hasNext() {
 return !D.isEmpty();
 }
 @Override
 public S next() {
 return D.rearDequeue();
 }
 @Override
 public void remove() {
 KDTreeNode a = (KDTreeNode) D.rearDequeue();
 tree.remove(a.data);
 }
 }
 /**判断两个数据的所有关键是否都相等*/
 public boolean isEquals(S a,S b){
 if(a==null||b==null)
 return false;
 for(Comparator<S> c:cp){
 if(c.compare(a, b)!=0)
 return false;
 }
 return true;
 }
 /**最临近查询，首先设置计算节点之间距离的距离计算器
 * @throws IllegalParameterException */
 public S nearest(S data) throws IllegalParameterException{
 if(data==null||size==0)
 return null;
 if(this.kdd==null)
 throw new IllegalParameterException("请先设置计算距离的计算器!");
 BinaryHeap<KDTreeNode<S>> bp=new BinaryHeap<KDTreeNode<S>>(new HPointCP(data));
 nearest(root,data,bp,1);
 return bp.pop().data;
 }
 /**最临近查询，首先设置计算节点之间距离的距离计算器
 * @throws IllegalParameterException */
 public List<S> nearest(S data,int k) throws IllegalParameterException{
 if(data==null||size==0)
 return null;
 if(this.kdd==null)
 throw new IllegalParameterException("请先设置计算距离的计算器!");
 BinaryHeap<KDTreeNode<S>> bp=new BinaryHeap<KDTreeNode<S>>(new HPointCP(data));
 nearest(root,data,bp,k);
 List<S> ll=new LinkedList<S>();
 Stack<S> s=new Stack<S>();
 while(!bp.isEmpty()){
 s.push(bp.pop().data);
 }
 
 for(S sa:s){
 ll.add(sa);
 }
 return ll;
 }
 /**给定半径和原点，索索该范围内所有点
 * @throws IllegalParameterException */
 public List<S> inCircle(S p,double d) throws IllegalParameterException{
 List<S> ll=new LinkedList<S>();
 inCircle(root,p,ll,d);
 return ll;
 }
 /**得到当前结点的上一个同维结点*/
 public KDTreeNode<S> getAboveSameDemisionNode(KDTreeNode<S> kdtn){
 byte i=kdtn.i;
 kdtn=kdtn.parent;
 while(kdtn!=root){
 if(kdtn.i==i){
 return kdtn;
 }
 kdtn=kdtn.parent;
 }
 if(kdtn.i==i)
 return kdtn;
 else
 return null;
 }
 /**最临近查询，首先设置计算节点之间距离的距离计算器
 * @throws IllegalParameterException */
 private void inCircle(KDTreeNode<S> kdtn,S data,List<S> bp,double d) throws IllegalParameterException{
 if(kdtn!=null){
 double dd=0.0;
 double d1=0.0;
 dd=kdd.HPointToHPointDistance(kdtn.data,data);
 if(dd>d){
 d1=kdd.HPointToHPointDistanceInIDemision
 (data,kdtn.data,kdtn.i);
 if(d1<d){
 inCircle(kdtn.left,data,bp,d);
 inCircle(kdtn.right,data,bp,d);
 }else if(d1==d){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 inCircle(kdtn.left,data,bp,d);
 }else{
 inCircle(kdtn.right,data,bp,d);
 }
 }else{
 KDTreeNode<S> tn=this.getAboveSameDemisionNode(kdtn);
 if(tn==null){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 inCircle(kdtn.left,data,bp,d);
 }else{
 inCircle(kdtn.right,data,bp,d);
 }
 }else{
 dd=kdd.HPointToHPointDistanceInIDemision(data,tn.data,tn.i);
 if(d<dd){
 if(cp.get(kdtn.i).compare(kdtn.data, tn.data)>0){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 inCircle(kdtn.left,data,bp,d);
 }else{
 inCircle(kdtn.right,data,bp,d);
 }
 
 }else{
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 inCircle(kdtn.left,data,bp,d);
 }else{
 inCircle(kdtn.right,data,bp,d);
 }
 }
 }else{
 if(cp.get(kdtn.i).compare(kdtn.data, tn.data)>0){
 inCircle(kdtn.left,data,bp,d);
 }else{
 inCircle(kdtn.right,data,bp,d);
 }
 }
 }
 }
 }else{ 
 if(!kdtn.isRemoved){
 bp.add(kdtn.data);
 }
 inCircle(kdtn.left,data,bp,d);
 inCircle(kdtn.right,data,bp,d);
 }
 }
 }
 /**最临近查询，首先设置计算节点之间距离的距离计算器
 * @throws IllegalParameterException */
 private void nearest(KDTreeNode<S> kdtn,S data,BinaryHeap<KDTreeNode<S>> bp,int k) throws IllegalParameterException{
 if(kdtn!=null){
 double d=0.0;
 double dd=0.0;
 double d1=0.0;
 if(bp.size()<k){
 if(!kdtn.isRemoved){
 bp.add(kdtn);
 }
 nearest(kdtn.left,data,bp,k);
 nearest(kdtn.right,data,bp,k);
 }else{
 dd=kdd.HPointToHPointDistance(kdtn.data,data);
 d=kdd.HPointToHPointDistance(data,bp.getTopElement().data);
 if(dd>d){
 d1=kdd.HPointToHPointDistanceInIDemision
 (data,kdtn.data,kdtn.i);
 if(d1<d){
 nearest(kdtn.left,data,bp,k);
 nearest(kdtn.right,data,bp,k);
 }else if(d1==d){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 nearest(kdtn.left,data,bp,k);
 }else{
 nearest(kdtn.right,data,bp,k);
 }
 }else{
 KDTreeNode<S> tn=this.getAboveSameDemisionNode(kdtn);
 if(tn==null){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 nearest(kdtn.left,data,bp,k);
 }else{
 nearest(kdtn.right,data,bp,k);
 }
 }else{
 dd=kdd.HPointToHPointDistanceInIDemision(data,tn.data,tn.i);
 if(d<dd){
 if(cp.get(kdtn.i).compare(kdtn.data, tn.data)>0){
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 nearest(kdtn.left,data,bp,k);
 }else{
 nearest(kdtn.right,data,bp,k);
 }
 
 }else{
 if(cp.get(kdtn.i).compare(kdtn.data, data)>0){
 nearest(kdtn.left,data,bp,k);
 }else{
 nearest(kdtn.right,data,bp,k);
 }
 }
 }else{
 if(cp.get(kdtn.i).compare(kdtn.data, tn.data)>0){
 nearest(kdtn.left,data,bp,k);
 }else{
 nearest(kdtn.right,data,bp,k);
 }
 }
 }
 }
 }else{ 
 if(!kdtn.isRemoved){
 bp.pop();
 bp.add(kdtn);
 }
 nearest(kdtn.left,data,bp,k);
 nearest(kdtn.right,data,bp,k);
 }
 }
 }
 }
 /**查询在指定范围之内的数据集合region是查询区域*/
 public List<S> queryDataInRegion(KDRegion<S> region){
 List<S> ll=new LinkedList<S>();
 queryDataInRegion(root,region,ll);
 return ll;
 }
 /**查询在指定范围之内的数据集合*/
 private void queryDataInRegion(KDTreeNode<S> kdtn,KDRegion<S> region,List<S> ll){
 if(kdtn!=null){
 if(!kdtn.isRemoved&&isInRegion(kdtn.data,region)){
 ll.add(kdtn.data);
 }
 if(!region.low[kdtn.i]||cp.get(kdtn.i).compare(kdtn.data,region.start)>=0)
 queryDataInRegion(kdtn.left,region,ll);
 if(!region.high[kdtn.i]||cp.get(kdtn.i).compare(kdtn.data,region.end)<0)
 queryDataInRegion(kdtn.right,region,ll);
 }
 }
 /**判断指定节点是否在指定区域里*/
 public boolean isInRegion(S data,KDRegion<S> r){
 for(int i=0;i<r.low.length;i++){
 if(!r.low[i]&&!r.high[i]){
 continue;
 }else if(!r.low[i]&&r.high[i]){
 if(cp.get(i).compare(data, r.end)>0)
 return false;
 }else if(r.low[i]&&!r.high[i]){
 if(cp.get(i).compare(data, r.start)<0)
 return false;
 }else if(r.low[i]&&r.high[i]){
 if(cp.get(i).compare(data, r.end)>0||cp.get(i).compare(data, r.start)<0)
 return false;
 }
 }
 return true;
 }
 /**判断对应的两个闭区域是否有交集*/
 public boolean isIntercect(KDRegion<S> r1,KDRegion<S> r2){
 for(int i=0;i<cp.size();i++){
 if((cp.get(i).compare(r1.start, r2.start)>0&&cp.get(i).compare(r1.start, r2.end)<0)
 ||(cp.get(i).compare(r2.start, r1.start)>0
 &&cp.get(i).compare(r2.start, r1.end)<0))
 return true;
 }
 return false;
 }
 /**返回迭代器*/
 @Override
 public Iterator<S> iterator() {
 return this.InOrderTraverse();
 }
 
 /**计算高位数据巨臂比较器*/
 public class HPointCP implements Comparator<KDTreeNode<S>>{
 private S data;
 public HPointCP(S a) {
 super();
 this.data = a;
 }
 @Override
 public int compare(KDTreeNode<S> a, KDTreeNode<S> b) {
 double d1=0.0,d2=0.0;
 d1=KDTree.this.kdd.HPointToHPointDistance(data, a.data);
 d2=KDTree.this.kdd.HPointToHPointDistance(b.data,data);
 if(d1<d2)
 return 1;
 else if(d1>d2)
 return -1;
 else
 return 0;
 }
 }
 /**查询在指定范围之内的数据集合s,e分别表示高位区域，low,hight标示对应维度是否有上下界*/
 public static class KDRegion<S>{
 /**标示左边界*/
 public S start;
 /**标示右边界*/
 public S end;
 /**标示对应维度是否有约束*/
 public boolean []low;
 /**标示对应维度是否有约束*/
 public boolean []high;
 public KDRegion() {
 super();
 }
 public S getS() {
 return start;
 }
 public void setS(S s) {
 this.start = s;
 }
 public S getE() {
 return end;
 }
 public void setE(S e) {
 this.end = e;
 }
 public boolean[] getLow() {
 return low;
 }
 public void setLow(boolean[] low) {
 this.low = low;
 }
 public boolean[] getHigh() {
 return high;
 }
 public void setHigh(boolean[] high) {
 this.high = high;
 } 
 }
}