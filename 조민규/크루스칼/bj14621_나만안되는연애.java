package 크루스칼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj14621_나만안되는연애 {
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    static int[][] adjMatrix;
    static String[] gender; // 성별 정보만 저장

    static int N,M, ans = Integer.MAX_VALUE; // 정점 갯수, 간선 갯수
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        gender = br.readLine().split(" ");
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(gender[a-1].equals(gender[b-1])) continue;

            // 이미 가중치가 존재할 경우
            if(adjMatrix[a][b] != 0){
                weight = Math.min(weight, adjMatrix[a][b]);
            }
            adjMatrix[a][b] = weight;
            adjMatrix[b][a] = weight;
        }

        kruskal();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void kruskal(){
        // 설정 및 입력
        int[] parent = new int[N+1];
        Arrays.fill(parent, -1);
        List<Edge> edges = new ArrayList<>();
        for(int i = 1 ; i < N+1 ; i++){
            for(int j = 1 ; j < N+1 ; j++){
                if(adjMatrix[i][j] != 0){
                    edges.add(new Edge(i, j, adjMatrix[i][j]));
                }
            }
        }
        // 정렬
        Collections.sort(edges);

        // 크루스칼 알고리즘
        Edge[] result = new Edge[N];
        int index = 0;
        for(int i = 0 ; i < edges.size() ; i++){
            Edge edge = edges.get(i);

            int xSet = find(parent, edge.from);
            int ySet = find(parent, edge.to);
            if(xSet != ySet){
                result[index++] = edge;
                union(parent, xSet, ySet);
            }
        }

        // 모든 정점에 대해 시행되지 못함 (그래프가 분리되어 있으므로 MST 못만들었음)
        if(index != N-1) return;

        // 합 구하기
        int sum = 0;
        for(int i = 0 ; i < result.length ; i++){
            if(result[i] == null) break;
            sum += result[i].weight;
        }
        ans = Math.min(ans, sum);
    }

    public static void union(int[] parent, int x, int y){
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }

    public static int find(int[] parent, int node){
        if(parent[node] == -1){
            return node;
        }
        return find(parent, parent[node]);
    }
}
