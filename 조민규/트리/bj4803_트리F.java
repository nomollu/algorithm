package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj4803_트리F {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, ans; // 정점 개수, 간선 개수
    static int[][] adjMatrix;
    static boolean[] visited;
    static boolean isTree;
    public static void main(String[] args) throws IOException {
        int T = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 || M == 0) break;
            solve(T++);
        }
    }

    public static void solve(int t) throws IOException {
        // 입력
        adjMatrix = new int[N+1][N+1];
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        ans = 0;
        visited = new boolean[N+1];
        isTree = true;
        for(int i = 1 ; i < N+1 ; i++){
            dfs(i, i);
            if(isTree) ans++;
            isTree = true;
        }

        System.out.print("Case " + t + ": ");
        if(ans == 0){
            System.out.println("No trees.");
        } else if (ans == 1){
            System.out.println("There is one tree.");
        } else {
            System.out.println("A forest of " + ans + " trees.");
        }
    }

    public static void dfs(int now, int start){

        // 현재 도착한 정점이 방문한 적이 있네?
        if(visited[now]){
            isTree = false;
            return;
        }

        visited[now] = true;

        int cNum = 0;
        for(int d = 0 ; d < N+1 ; d++){
            // 이동 가능한 정점
            if(d != now && adjMatrix[now][d] == 1 && !visited[d]){
                cNum++;
                dfs(d, start);
            }
        }

        if(cNum >= 2) isTree = false;
    }
}
