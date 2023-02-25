package 그리디;

public class p42883_큰수만들기 {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        // start : 탐색을 시작하는 위치, j : 현재 탐색중인 위치, max : 최댓값
        int start = 0;
        int max = 0;
        int cnt = 0;
        while(cnt < number.length() - k){
            max = 0;

            // 앞에서부터 탐색
            for(int j = start ; j <= k + cnt ; j++){
                if(max < ctoi(number.charAt(j))){
                    max = ctoi(number.charAt(j));
                    start = j + 1;
                }
            }

            // 정답에 찾은 값 추가
            answer.append(max);
            cnt++;
        }
        return answer.toString();
    }

    public static int ctoi(char c){
        return c - '0';
    }
}
