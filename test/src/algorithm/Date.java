package algorithm;
//练习toString和equals的覆盖写法
public class Date {
	private final int year;
	private final int month;
	private final int day;
	public Date(int y,int m,int d){
		year = y;
		month = m;
		day = d;
	}
	public int year(){
		return year;
	}
	public int month(){
		return month;
	}
	public int day(){
		return day;
	}
	public String toString(){
		return year()+"/"+month()+"/"+day();
	}
	public boolean equals(Object x){
		if(this==x){
			return true;
		}
		if(this==null){
			return false;
		}
		if(this.getClass()!=x.getClass()){
			return false;
		}
		
		Date that = (Date)x;
		if(this.year!=that.year||this.month!=that.month||this.day!=that.day){
			return false;
		}
		return true;
	}
}
