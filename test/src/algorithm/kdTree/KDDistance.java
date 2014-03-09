package algorithm.kdTree;


/**用具KD树中的临近查询*/
public interface KDDistance<S> {
 /**返回两个数据之间的距离*/
 public double HPointToHPointDistance(S a,S b);
 /**返回指定高维数据指定维度之间的垂直距离*/
 public double HPointToHPointDistanceInIDemision(S a,S b,byte i);
}


 
