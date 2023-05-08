import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int w : works) queue.add(w);
        
        int cnt=0, remains=n, height=queue.peek();
        long answer = 0;
        
        while(!queue.isEmpty() && remains>0){
            int work = queue.peek();
            int diff = height - work;
            
            if(cnt*diff <= remains){
                remains -= cnt * diff;
                height = work;
                cnt++;
                queue.poll();
            }else{
                break;
            }
        }
        
        if(remains>0){
            if(height*cnt<=remains) return 0;
            int tmp_h = remains / cnt;
            int tmp_c = remains % cnt;
            answer += Math.pow(height-tmp_h-1, 2) * tmp_c;
            answer += Math.pow(height-tmp_h, 2) * (cnt - tmp_c);
        }else {
            answer += Math.pow(height, 2) * cnt;
        }
        
        while(!queue.isEmpty()){
            answer += Math.pow(queue.poll(), 2);
        }
        
        return answer;
    }
}
