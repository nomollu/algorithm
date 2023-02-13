import java.util.*;

class Solution {
    
    static String change(String s){ 입력받은 문자열을 올바른 괄호 문자열로 변환
            int len = s.length();
            if(len==0) return "";
            int left=0, right=0;
            String u="", v="";  
            for(int i=0; i<len; i++){
                if(s.charAt(i)=='(') left++;
                else right++;
                if(left == right) {
                    u = s.substring(0, ++i);
                    v = s.substring(i, len);
                    break;
                }
            }
        
            if(check(u)) return u + change(v);
            else{
                String tmp = "(" + change(v) + ")";
                int tmp_len = u.length()-1;
                for(int i=1; i<tmp_len; i++){
                    tmp += (u.charAt(i)=='(' ? ")" : "(");
                }
                return tmp;
            }
    }
    
    static boolean check(String s){ // 입력받은 문자열이 올바른 괄호 문자열인지 체크
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        for(int i=0; i<len; i++){
            char now = s.charAt(i);
            if(stack.size()==0) stack.push(now);
            else{
                int pre = stack.peek();
                if(pre=='(' && now==')') stack.pop();
                else stack.push(now);
            }
        }
        if(stack.size()>0) return false;
        else return true;
    }
    
    public String solution(String p) {

        return change(p+"");
    }
}
