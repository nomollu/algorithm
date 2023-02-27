import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        
        int answer[] = new int[2];
        Set<String> set = new HashSet<String>();
        char pre = words[0].charAt(words[0].length()-1);
        int len = words.length, idx=1;
        set.add(words[0]);
        answer[0]=1;
        for(int i=1; i<len; i++){
            if(set.contains(words[i]) || words[i].length()<=1 || pre!=words[i].charAt(0)){
                answer[0]++;
                answer[1] = i/n + 1;
                return answer;
            }else{
                answer[0] = (answer[0]+1)%n;
                set.add(words[i]);
                pre = words[i].charAt(words[i].length()-1);
            }
        }
        
        answer[0] = answer[1] = 0;
        return answer;
    }
}
