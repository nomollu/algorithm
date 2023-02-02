package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj18429_근손실 {
    static int N, K, ans = 0; // 운동 키트 개수, 중량 감소량, 정답
    static int[] kit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        kit = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            kit[i] = Integer.parseInt(st.nextToken());
        }

        health(0, 500, new boolean[N]);
        System.out.println(ans);
    }

    public static void health(int cnt, int weight, boolean[] visited){
        if(cnt == N){
            ans++;
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(!visited[i] && weight + kit[i] - K >= 500){
                visited[i] = true;
                health(cnt+1, weight + kit[i] - K, visited);
                visited[i] = false;
            }
        }
    }
}
