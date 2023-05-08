// G5 골목 대장 호석 - 효율성 2
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p_20183 {

    static class Edge implements Comparable<Edge> {
        long n, w;
        
        Edge(long n, long w) {
            this.n = n;
            this.w = w;
        }
    
        @Override
        public int compareTo(Edge o) {            
            return Long.compare(this.w, o.w);
        }
    }

    static long N, M, A, B, C;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    public static void main(String[] main) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        for(long i = 0; i <= N; i++)
            graph.add(new ArrayList<Edge>());

        for(long i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        long ans = Integer.MAX_VALUE;
        
        long left = 1, right = C;
        while(left <= right) {
            long mid = (left + right) / 2;

            if(isValid(mid)) {
                ans = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }

        System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
    }

    static boolean isValid(long max_w) {
        // dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] distance = new long[(int) N + 1];
        for(int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;

        distance[(int) A] = 0;
        pq.add(new Edge(A, 0));

        while(!pq.isEmpty()) {
            int cur_node = (int) pq.peek().n;
            long cur_dist = pq.peek().w;
            pq.poll();

            if(distance[cur_node] < cur_dist) continue;

            for(Edge e : graph.get(cur_node)) {
                int next_node = (int) e.n;
                long next_node_dist = e.w;
                if(next_node_dist > max_w) continue;

                // update distance
                long next_dist = cur_dist + next_node_dist;
                if(next_dist < distance[next_node]) {
                    distance[next_node] = next_dist;
                    pq.add(new Edge(next_node, next_dist));
                }
            }
        }

        return distance[(int) B] <= C;
    }
}