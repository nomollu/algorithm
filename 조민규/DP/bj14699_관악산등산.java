package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14699_관악산등산 {
    static int N,M; // 쉼터의 수, 두 쉼터 사이를 연결하는 길의 수
    static int[] height; // 쉼터의 높이
    static int[][] adjMatrix; // 쉼터 인접 행렬
    static int[] ans;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = new int[N+1];
        adjMatrix = new int[N+1][N+1];
        ans = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }
        
        // 쉼터 높이에 따른 from과 to 판단, 인접행렬 입력
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(height[a] < height[b]){
                adjMatrix[a][b] = 1;
            } else {
                adjMatrix[b][a] = 1;
            }
        }

        // 정답 계산
        for(int start = 1 ; start <= N ; start++){
            mountain(1, start, start, new boolean[N+1]);
        }

        for(int i = 1 ; i <= N ; i++){
            System.out.println(ans[i]);
        }
    }

    public static void mountain(int cnt, int now, int start, boolean[] visited){
        visited[now] = true;

        boolean flag = false;
        for(int i = 1 ; i <= N ; i++){
            if(i != now && adjMatrix[now][i] == 1 && !visited[i]){
                flag = true;
                mountain(cnt+1, i, start, visited);
            }
        }

        if(!flag) ans[start] = Math.max(ans[start], cnt);
    }
}
