import java.util.*;

class Solution {
    static class Number implements Comparable<Number>{
        int num, cnt; 
        Number(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Number o){
            if(this.num == o.num) return this.cnt - o.cnt;
            else return this.num - o.num;
        }
    }

    public int solution(int storey) {
        int result = Integer.MAX_VALUE;
        int depth = ("" + storey).length();
        
        Queue<Number> queue = new LinkedList<Number>();
        queue.add(new Number(storey, 0));
        
        int d = 0;
        while(!queue.isEmpty()){
            int size=queue.size();
            
            for(int s=0; s<size; s++){
                Number now = queue.poll();
                int tmp = (int)(now.num / Math.pow(10, d)) % 10; // d번째 자리 숫자
            
                if(tmp <= 5){
                    int num = now.num - tmp * (int)Math.pow(10, d);
                    int cnt = now.cnt+tmp;
                    
                    if(num>0) queue.add(new Number(num, cnt));
                    else result = Math.min(result, cnt);
                }
                if(tmp >= 5){
                    int num = now.num + (10-tmp) * (int)Math.pow(10, d);
                    int cnt = now.cnt+10-tmp;
                    
                    queue.add(new Number(num, cnt));
                    if(num==0) result = Math.min(result, cnt);
                }
            }
            d++;
        }
        
        return result;
    }
}
