package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj3980_선발명단 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;
    static int ans;
    public static void main(String[] args) throws IOException {
        int C = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < C ; i++){
            test();
        }
    }

    public static void test() throws IOException {
        // 입력
        ans = 0;
        arr = new int[11][11];
        for(int i = 0 ; i < 11 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 11 ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS
        dfs(0, 0, new boolean[11]);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int sum, boolean[] visited){
        if(cnt == 11){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i = 0 ; i < 11 ; i++){
            if(arr[i][cnt] != 0 && !visited[i]){
                visited[i] = true;
                dfs(cnt+1, sum+arr[i][cnt], visited);
                visited[i] = false;
            }
        }
    }
}
