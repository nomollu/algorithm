package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1707_이분그래프F_BFS {
    static int V, E;
    static int[][] adjMatrix;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < K ; k++){
            if(test()) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    public static boolean test() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjMatrix = new int[V+1][V+1];
        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        // 모든 정점을 시작점으로 한 BFS
        visited = new boolean[V+1];
        for(int i = 1 ; i <= V ; i++){
            if(!visited[i]){
                if(bfs(i)) return true;
            }
        }
        return false;
    }

    public static boolean bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int[] parent = new int[V+1];

        while (!queue.isEmpty()){
            int now = queue.poll();
            visited[now] = true;

            for(int i = 1 ; i < V+1 ; i++){
                if(i != now && !visited[i] && adjMatrix[now][i] == 1){
                    queue.add(i);
                    parent[i] = now;
                }

                if(i != now && visited[i] && adjMatrix[now][i] == 1 && i != parent[now]){
                    return true;
                }
            }
        }
        return false;
    }
}
