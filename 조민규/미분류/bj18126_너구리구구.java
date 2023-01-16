package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj18126_너구리구구 {

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N;
    static List<Edge>[] ways;
    static boolean[] visited;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ways = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1 ; i <= N ; i++){
            ways[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            ways[from].add(new Edge(to, weight));
            ways[to].add(new Edge(from, weight));
        }

        dfs(1, 0);
        System.out.println(ans);
    }

    public static void dfs(int now, long sumWeight){
        // 방문처리
        visited[now] = true;

        // 지금까지 온 거리가 최장 거리일 경우
        if(ans < sumWeight){
            ans = sumWeight;
        }

        // 인접한 모든 edge들을 탐색한다.
        for(Edge way : ways[now]){
            if(visited[way.to]) continue;
            dfs(way.to, sumWeight + way.weight);
        }
    }
}
