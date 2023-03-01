package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj15650_N과M2 {
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 1~N의 자연수
        M = Integer.parseInt(st.nextToken()); // 수열의 길이

        for(int i = 1 ; i <= N ; i++){
            bt(1, i, new StringBuilder(i+""));
        }
    }

    public static void bt(int cnt, int now, StringBuilder sb){
        if(cnt == M){
            System.out.println(sb.toString());
            return;
        }

        for(int i = now+1 ; i <= N ; i++){
            bt(cnt+1, i, new StringBuilder().append(sb.toString()).append(" " + i));
        }
    }
}
