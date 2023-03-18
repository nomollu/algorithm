package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1707_이분그래프F_유니온 {
    static int V, E;
    static int[][] adjMatrix;
    static int[] parent;
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
        parent = new int[V+1];
        for(int i = 1 ; i < V+1 ; i++){
            parent[i] = i;
        }
        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        // Union Find
        for(int i = 1 ; i < V+1 ; i++){
            for(int j = 1 ; j < V+1 ; j++){
                if(i < j && adjMatrix[i][j] == 1){
                    int a = find(i);
                    int b = find(j);
                    if(a == b) return true;
                    union(i, j);
                }
            }
        }
        return false;
    }

    public static int find(int x){
        // 초깃값
        if(parent[x] == x)
            return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return false;

        if(x <= y) parent[y] = x;
        else parent[x] = y;

        return true;
    }
}
