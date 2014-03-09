package others;

public class TT{
	public static void main(String [] args){
		System.out.println(Integer.MAX_VALUE+1);
		System.out.println(Integer.MAX_VALUE+1L);
		System.out.println(Integer.MIN_VALUE-1);
		System.out.println(Integer.MIN_VALUE-1L);
	}
}


//
//#include <stdio.h>
//int main(){
//        int n,i,j;
//        while(scanf("%d",&n) != EOF){
//                for(i = 0;i < n;i ++){
//                        int p,q,temp;
//                        scanf("%d %d",&p,&q);
//                        for(j = 0;j < 16;j ++){
//                                temp = ((p << j) | (p >> (16 - j))) & 65535;
//                                //temp = (p << j) | (p >> (16 - j));
//                                if(temp == q){
//                                        printf("YES\n");
//                                        break;
//                                }
//                        }
//                        if(j >= 16){
//                                printf("NO\n");
//                        }
//                }
//        }
//        return 0;
//}