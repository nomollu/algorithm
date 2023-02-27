import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int time=0, idx=0, cnt=0, remain_weight=weight, total=truck_weights.length;
        while(cnt<total){
            if(bridge_length<++time) {
                int truck = queue.poll();
                if(truck != -1){
                    remain_weight += truck;
                    cnt++;
                }
            }
            if(idx<total){
                if(truck_weights[idx]<=remain_weight) {
                    remain_weight -= truck_weights[idx];
                    queue.add(truck_weights[idx++]);
                }else{
                    queue.add(-1);
                }
            }
        }
        return time;
    }
}
