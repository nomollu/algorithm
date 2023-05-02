import java.util.*;

class Solution {
    static class Room implements Comparable<Room>{
        int start, end;
        Room(String[] book){
            this.start = changeToInt(book[0]);
            this.end = changeToInt(book[1]) + 10;
        }
        @Override
        public int compareTo(Room o) {
            return this.start - o.start;
        }
    }
    
    static int changeToInt(String time){
        int result = 0;
        String[] str = time.split(":");
        for(int i=0; i<2; i++) result += Integer.parseInt(str[i]) * Math.pow(60, (i+1)%2);
        return result;
    }
    
    public int solution(String[][] book_time) {
        PriorityQueue<Room> booking = new PriorityQueue<Room>();
        
        for(String[] str : book_time) booking.add(new Room(str));
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int time=0, max=0;
        
        while(!booking.isEmpty()){
            Room now = booking.poll();
            time = now.start;

            while(!queue.isEmpty() && queue.peek() <= time) queue.poll();
            
            queue.add(now.end);
            max = Math.max(max, queue.size());
        }
        return max;
    }
}
