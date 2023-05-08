// G5 골목 대장 호석 - 효율성 1
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class p_20182 {

    static class Edge implements Comparable<Edge> {
        int n, w;
        
        Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }
    
        @Override
        public int compareTo(Edge o) {            
            return Integer.compare(this.w, o.w);
        }
    }

    static int N, M, A, B, C;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    public static void main(String[] main) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++)
            graph.add(new ArrayList<Edge>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 20; 1 <= i; i--)
            if(isValid(i)) ans = i;

        System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
    }

    static boolean isValid(int max_w) {
        // dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        for(int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;

        distance[A] = 0;
        pq.add(new Edge(A, 0));

        while(!pq.isEmpty()) {
            int cur_node = pq.peek().n;
            int cur_dist = pq.peek().w;
            pq.poll();

            for(Edge e : graph.get(cur_node)) {
                int next_node = e.n;
                int next_node_dist = e.w;
                if(next_node_dist > max_w) continue;

                // update distance
                int next_dist = cur_dist + next_node_dist;
                if(next_dist < distance[next_node]) {
                    distance[next_node] = next_dist;
                    pq.add(new Edge(next_node, next_dist));
                }
            }
        }

        return distance[B] <= C;
    }
}