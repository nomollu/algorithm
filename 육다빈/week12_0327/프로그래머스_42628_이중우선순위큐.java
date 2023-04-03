import java.util.*;

class Solution {
    
    static class Number implements Comparable<Number>{
        int idx, value;
        Number(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
        @Override
        public int compareTo(Number o){
            return this.value - o.value;
        }
    }
    
    public int[] solution(String[] operations) {
        
        PriorityQueue<Number> minQueue = new PriorityQueue<Number>();
        PriorityQueue<Number> maxQueue = new PriorityQueue<Number>(Collections.reverseOrder());
        
        int idx = 0;
        boolean[] deleted = new boolean[operations.length];
        
        for(String oper : operations){
            StringTokenizer st = new StringTokenizer(oper);
            
            if(st.nextToken().charAt(0)=='I'){ //삽입 연산
                int num = Integer.parseInt(st.nextToken());
                minQueue.add(new Number(idx, num));
                maxQueue.add(new Number(idx++, num));
                
            }else{ //삭제 연산
                if(st.nextToken().charAt(0)=='-'){ // 최솟값 삭제
                    while(!minQueue.isEmpty()){
                        Number now = minQueue.poll();
                        if(!deleted[now.idx]) {
                            deleted[now.idx] = true;
                            break;
                        }
                    }
                }else{ // 최댓값 삭제
                    while(!maxQueue.isEmpty()){
                        Number now = maxQueue.poll();
                        if(!deleted[now.idx]) {
                            deleted[now.idx] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        
        // 최댓값 출력
        while(!maxQueue.isEmpty()){
            Number now = maxQueue.poll();
            if(!deleted[now.idx]) {
                answer[0] = now.value;
                break;
            }
        }
        
        // 최솟값 출력
        while(!minQueue.isEmpty()){
            Number now = minQueue.poll();
            if(!deleted[now.idx]) {
                answer[1] = now.value;
                break;
            }
        }
        
        return answer;
    }
}
