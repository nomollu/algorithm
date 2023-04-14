import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());

        Arrays.sort(people);
        int answer = 0;
        
        for(int p=people.length-1; p>=0; p--){
            if(queue.isEmpty() || queue.peek()<people[p]) queue.add(limit-people[p]);
            else {
                answer++;
                queue.poll();
            }
        }
        
        return answer + queue.size();
    }
}
