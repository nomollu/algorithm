import java.util.*;

class Solution
{
    public int solution(String s)
    {   
        String str = s;
        int idx = 0;
        
        while(str.length()>0 && idx+1<str.length()){
            if(str.charAt(idx) == str.charAt(idx+1)){
                if(idx == 0){
                    str = str.substring(2, str.length());
                }else{
                    str = str.substring(0, idx) + str.substring(idx+2, str.length());
                    idx -= 1;
                }
            }else{
                idx++;
            }
        }
        
    }
}
