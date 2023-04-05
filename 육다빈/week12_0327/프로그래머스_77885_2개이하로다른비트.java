import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        int N = numbers.length;
        long[] answer = new long[N];
        
        for(int i=0; i<N; i++){
            if(numbers[i]%2==0) {
                answer[i] = numbers[i]+1;
            }else{
                long bit=1;
                while((numbers[i] & bit) > 0){
                    bit *= 2;
                }
                answer[i] = numbers[i] + bit/2; //(+ bit - bit/2)
            }
        }
        
        return answer;
    }
}
