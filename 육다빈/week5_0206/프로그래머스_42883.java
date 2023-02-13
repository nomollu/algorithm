class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int cnt = 0;
        String num = number + "";
        while(true){
            int cnt_inside = 0;
            answer = "";
            int len = num.length();
            for(int i=0; i<len-1; i++){
                if(num.charAt(i)-'0' >= num.charAt(i+1)-'0') answer += num.charAt(i);
                else {
                    cnt_inside++;
                    if(++cnt==k) return answer + num.substring(i+1, len);
                }
            }
            if(cnt_inside==0) return num.substring(0,number.length()-k);
            num = answer + num.charAt(len-1);
        }
    }
}
