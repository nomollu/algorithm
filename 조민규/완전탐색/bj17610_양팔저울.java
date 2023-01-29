package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17610_양팔저울 {
    static int K; // 추의 개수
    static long S = 0; // 추의 무게 합
    static long[] G; // 추의 무게
    static int ans = 0;
    static boolean flag; // 무게를 잴 수 있는지 없는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        K = Integer.parseInt(st.nextToken());
        G = new long[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++){
            G[i] = Integer.parseInt(st.nextToken());
            S += G[i];
        }

        // 1 ~ S까지 모든 물의 무게의 경우를 탐색한다.
        for(long water = 1 ; water <= S ; water++){
            flag = false;
            // j : 가장 먼저 계산식에 넣는 추의 index
            for(int j = 0 ; j < K ; j++){
                calc(0, j, '+', 0, water, new boolean[K]);
                calc(0, j, '-', 0, water, new boolean[K]);
            }

            if(!flag) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    // cnt : 현재 계산식에 넣은 추 개수, now : 현재 더하는 추, operator : 더하기 혹은 빼기, result : 현재 무게 합산, water : 물의 무게
    public static void calc(int cnt, int now, char operator, long result, long water, boolean[] visited){
        // 모든 추를 다 계산식에 넣었거나, 성공한 water일 경우 return
        if(cnt == K+1 || flag){
            return;
        }

        visited[now] = true;

        // 현재 결과에 추의 무게를 더하거나 뺀다.
        switch (operator){
            case '+':
                result += G[now];
                break;
            case '-':
                result -= G[now];
                break;
        }

        // 현재 결과에 물을 더하거나 뺐을 때 0이 되는지 확인한다.
        if(result + water == 0 || result - water == 0) flag = true;

        for(int i = 0 ; i < K ; i++){
            // 넣지 않은 추일 경우
            if(!visited[i]){
                calc(cnt + 1, i, '+', result, water, visited);
                calc(cnt + 1, i, '-', result, water, visited);
            }
        }
    }
}
