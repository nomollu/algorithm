import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> log = new HashMap<String, String>();
        
        int cnt = 0;
        for(String str : record){
            String[] s = str.split(" ");
            if(s[0].charAt(0)!='C') cnt++;
            if(s[0].charAt(0)=='L') continue;
            else if(!log.containsKey(s[1])){
                log.put(s[1], s[2]);
            }else {
                log.replace(s[1], s[2]);                    
            }
        }
        
        String[] answer = new String[cnt];
        int i = 0;
        for(String str : record){
            String[] s = str.split(" ");
            if(s[0].charAt(0)=='E') answer[i++] = log.get(s[1]) + "님이 들어왔습니다.";
            else if(s[0].charAt(0)=='L') answer[i++] = log.get(s[1]) + "님이 나갔습니다.";
        }
        
        return answer;
    }
}
