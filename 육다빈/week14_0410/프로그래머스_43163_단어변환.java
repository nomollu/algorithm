import java.util.*;

class Solution {
    
    static boolean checkChange(String target, String word){ // 한글자만 다른 단어인지 확인
        boolean result = false;
        int size = target.length();
        for(int i=0; i<size; i++){
            if(target.charAt(i)!=word.charAt(i)){
                if(result) return false;
                else result = true;
            }
        }
        return result;
    }
    
    static int changeWord(String begin, String target, String[] words){ // 단어 변환
        List<String> visit = new ArrayList<String>();
        Queue<String> queue = new LinkedList<String>();
        visit.add(begin);
        queue.add(begin);
        
        int depth = 0;
        while(!queue.isEmpty()){
            depth++;
            int size = queue.size();
            for(int i=0; i<size; i++){
                String now = queue.poll();
                
                for(String s : words){
                    if(!visit.contains(s) && checkChange(now, s)) {
                        if(s.equals(target)) return depth; // 목표 단어 도달
                        visit.add(s);
                        queue.add(s);
                    }
                }      
            }
        }
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
    
        return changeWord(begin, target, words);
    }
}
