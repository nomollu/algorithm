package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj15652_N과M4 {
    static int N,M;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        bt(0, 1, new int[M]);
        System.out.println(sb.toString());
    }

    public static void bt(int cnt, int now, int[] selected){
        // M개를 다 골랐을 경우
        if(cnt == M){
            // 고른 숫자들을 문자열로 만든다.
            for(int i = 0 ; i < M ; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 오름차순을 만족하는 다음 경우 탐색
        for(int i = now ; i <= N ; i++){
            selected[cnt] = i;
            bt(cnt+1, i, selected);
        }
    }
}