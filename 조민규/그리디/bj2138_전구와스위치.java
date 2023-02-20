package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2138_전구와스위치 {
    static int N, ans = Integer.MAX_VALUE;
    static String target;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder bulbs = new StringBuilder(br.readLine());
        target = new String(br.readLine());

        // 0번째 스위치를 누르지 않았을 경우
        StringBuilder bulbs1 = new StringBuilder(bulbs.toString());
        solve(0, bulbs1);

        // 0번째 스위치를 눌렀을 경우
        StringBuilder bulbs2 = new StringBuilder(bulbs.toString());
        bulbs2 = pushSwitch(0, bulbs2);
        solve(1, bulbs2);

        System.out.println(ans == Integer.MAX_VALUE ? "-1" : ans);
    }

    public static void solve(int cnt, StringBuilder bulbs){
        for(int i = 1 ; i < N ; i++){
            if(bulbs.charAt(i-1) != target.charAt(i-1)){
                bulbs = pushSwitch(i, bulbs);
                cnt++;
            }
        }

        if(bulbs.toString().equals(target)){
            ans = Math.min(ans, cnt);
        }
    }

    public static StringBuilder pushSwitch(int now, StringBuilder bulbs){
        for(int i = now-1 ; i <= now+1 ; i++){
            if(i < 0 || i >= N) continue;

            bulbs.replace(i, i+1, bulbs.charAt(i)-'0' == 0 ? "1" : "0");
        }
        return bulbs;
    }
}
