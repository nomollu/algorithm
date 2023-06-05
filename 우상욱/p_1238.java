package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G3 파티
 */
public class p_1238 {

    static class Edge implements Comparable<Edge> {
        int node;
        int dis;

        public Edge(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dis, o.dis);
        }
    }

    static int N, M, X;
    static ArrayList<ArrayList<Edge>> graph, reverse_graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList<>();
        reverse_graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            reverse_graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Edge(B, C));
            reverse_graph.get(B).add(new Edge(A, C));
        }

        int out_distance[] = new int[N];
        Arrays.fill(out_distance, Integer.MAX_VALUE);
        out_distance[X] = 0;

        dijkstra(out_distance, graph);

        int in_distance[] = new int[N];
        Arrays.fill(in_distance, Integer.MAX_VALUE);
        in_distance[X] = 0;

        dijkstra(in_distance, reverse_graph);

        int ans = -1;
        for (int i = 0; i < N; i++)
            ans = Math.max(ans, in_distance[i] + out_distance[i]);
        System.out.println(ans);
    }

    static void dijkstra(int[] distance, ArrayList<ArrayList<Edge>> p_graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(X, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (distance[cur.node] < cur.dis)
                continue;

            for (Edge next : p_graph.get(cur.node)) {
                int next_dis = next.dis + distance[cur.node];

                if (next_dis < distance[next.node]) {
                    distance[next.node] = next_dis;
                    pq.add(new Edge(next.node, next_dis));
                }
            }
        }
    }
}
