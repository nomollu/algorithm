class Solution {
    public String solution(String s) {
        String answer = "";
        
        String remains = s;
        if(s.charAt(0)==' ') { // 시작문자열에 공백이 있는 경우
            int i=0;
            while(s.charAt(i++)==' ') answer += " ";
            remains = remains.substring(i);
        }
        
        while(true){
            int len = remains.length();
            if(len==0) break;
            
            int endPoint = remains.indexOf(" "); // 공백이 등장하는 최초 인덱스

            String word;
            if(endPoint<0) word = remains;
            else word = remains.substring(0, endPoint);

            answer += (word.charAt(0)+"").toUpperCase() + word.substring(1).toLowerCase();  // 변환된 문자열 추가
            if(endPoint<0) break; // 추가한 문자열이 마지막 문자열일 경우

            int cnt=1;
            while(++endPoint<len){ // 공백 추가
                if(remains.charAt(endPoint)==' ') cnt++;
                else break;
            } 

            answer += " ".repeat(cnt);
            remains = remains.substring(endPoint);

        }
        
        return answer;
    }
}
