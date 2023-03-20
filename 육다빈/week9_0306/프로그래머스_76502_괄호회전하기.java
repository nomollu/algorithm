import java.util.*;

class Solution {
    public int solution(String s) {
        Queue<String> queue = new LinkedList<String>();
        int len = s.length();
        for(int i=0; i<len; i++){
            String str = s.substring(i, len) + s.substring(0, i);
            queue.add(str);
        }
        
        int cnt=0, size=s.length();
        while(!queue.isEmpty()){
            String str = queue.poll();
            Stack<Character> stack = new Stack<Character>();
            for(int i=0; i<size; i++){
                char now = str.charAt(i);
                if(now=='[' || now=='(' || now=='{') {
                    stack.push(now);
                }else if(stack.isEmpty()){
                    stack.push(now);
                    break;
                }else{
                    char tmp = stack.pop();
                    if((now==']'&&tmp=='[') || (now==')'&&tmp=='(') || (now=='}' && tmp=='{') ){
                        continue;
                    }else{
                        stack.push(now);
                        break;
                    }
                }
            }
            if(stack.isEmpty()) cnt++;
        }
                       
        return cnt;
    }
}
