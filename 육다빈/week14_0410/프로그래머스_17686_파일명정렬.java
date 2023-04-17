import java.util.*;

class Solution {
    static class File implements Comparable<File>{
        int idx, number;
        String name;
        File(int idx, String name, int number){
            this.idx = idx;
            this.name = name;
            this.number = number;
        }
        @Override
        public int compareTo(File o){
            if(this.name.compareTo(o.name)==0) {
                if(this.number==o.number) return this.idx - o.idx;
                else return this.number - o.number;
            }else return this.name.compareTo(o.name);
        }
    }
    
    public String[] solution(String[] files) {
        PriorityQueue<File> queue = new PriorityQueue<File>();
        int len = files.length;
        
        for(int i=0; i<len; i++){ 
            int j=-1, k=-1, file_len=files[i].length();
            for(j=0; j<file_len; j++){ // Number 시작구간 탐색
                char c = files[i].charAt(j);
                if(c>='0' && c<='9') {
                    k = j;
                    while(++k<file_len){ // Number 끝구간 탐색
                        char end = files[i].charAt(k);
                        if(end<'0' || end>'9') break;
                    }
                    break;
                }
            }
            
            String name = files[i].substring(0,j).toLowerCase();
            int number = Integer.parseInt(files[i].substring(j, k));
            queue.add(new File(i, name, number));
        }
        
        String[] answer = new String[len];
        for(int i=0; i<len; i++){
            answer[i] = files[queue.poll().idx];
        }
    
        return answer;
    }
}
