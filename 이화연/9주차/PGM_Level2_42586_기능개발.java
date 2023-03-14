import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<speeds.length; i++){
            int temp = (int)Math.ceil((double)(100 - progresses[i])/speeds[i]);
            queue.add(temp);
        }
        
        int before = queue.poll();
        int idx = 0;
        list.add(1);
        while(!queue.isEmpty()){
            int now = queue.poll();
            if(now > before){
                idx++;
                before = now;
                list.add(1);
            }else{
                list.set(idx, list.get(idx)+1);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
