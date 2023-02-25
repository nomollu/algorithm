package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj7490_0만들기 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T ; t++){
            N = Integer.parseInt(br.readLine());
            calc(1, 1, '+', 0, new StringBuilder("1"));
            calc(1, 1, '-', 0, new StringBuilder("1"));
        }
    }

    public static void calc(int cnt, int nowNum, char ope, int sum, StringBuilder ans){
        // 조건 1 : 1~N까지 식에 모두 넣었을 경우
        if(cnt == N){
            // 마지막 수를 계산한다.
            if(ope == '+'){
                sum += nowNum;
            } else if(ope == '-') {
                sum -= nowNum;
            }

            // 조건 2 : sum이 0일 경우
            if(sum == 0){
                System.out.println(ans.toString());
            }
            return;
        }

        cnt++;
        // 다음이 +
        int newSum = sum + nowNum;
        StringBuilder newAns = new StringBuilder(ans).append('+').append(cnt);
        calc(cnt, cnt, '+', newSum, newAns);

        // 다음이 -
        newSum = sum - nowNum;
        newAns = new StringBuilder(ans).append('-').append(cnt);
        calc(cnt, cnt, '-', newSum, newAns);

        // 이어갈 경우
        newAns = new StringBuilder(ans).append(' ').append(cnt);
        calc(cnt, (nowNum * 10) + cnt, ope, sum, newAns);
    }
}
