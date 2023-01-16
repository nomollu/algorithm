package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14501_퇴사 {
    static int N; // N일만큼 남은 상담기간
    static int[] T, P; // 상담 완료하는데 걸리는 기간, 받을 수 있는 금액
    static int ans = 0; // 최대 수익
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = new int[N];
        P = new int[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 완전탐색
        for(int i = 0 ; i < N ; i++){
            sangdam(i, 0);
        }
        System.out.println(ans);
    }

    // now : 현재 상담날짜, money : 누적된 수익
    public static void sangdam(int now, int money){
        // 최종 수익 계산
        if(now + T[now] >= N) {
            money = now + T[now] == N ? money + P[now] : money; // 최종일에 딱 맞춰 끝나는 경우
            ans = Math.max(ans, money);
            return;
        }
        
        // 상담
        money += P[now];

        // 다음 가능한 상담 날짜로 이동
        for(int i = now+T[now] ; i < N ; i++){
            sangdam(i, money);
        }
    }
}
