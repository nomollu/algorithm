package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1260_DFS와BFS {

    static int N,M,V; // 정점 개수, 간선 개수, 탐색을 시작할 정점의 번호
    static int[][] adjMatrix;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = 1;
            adjMatrix[to][from] = 1;
        }

        dfs(V);
        System.out.println();
        bfs(V);
        System.out.println();
    }

    public static void dfs(int now){
        visited[now] = true;
        System.out.print(now + " ");

        if(now == N+1){
            return;
        }

        for(int i = 1 ; i < N+1 ; i++){
            if(adjMatrix[now][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }

    public static void bfs(int start){
        visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int now = queue.poll();
            visited[now] = true;
            System.out.print(now + " ");

            for(int i = 1 ; i < N+1 ; i++){
                if(adjMatrix[now][i] == 1 && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
