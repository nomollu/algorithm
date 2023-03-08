package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2606_바이러스 {
    static int N, M; // 노드 갯수, 간선 갯수
    static int[][] adjMatrix;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjMatrix = new int[N+1][N+1];
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        bfs(1);
        System.out.println(ans);
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int i = 2 ; i <= N ; i++){
                if(i != now && !visited[i] && adjMatrix[now][i] == 1){
                    visited[i] = true;
                    ans += 1;
                    queue.add(i);
                }
            }
        }
    }
}
