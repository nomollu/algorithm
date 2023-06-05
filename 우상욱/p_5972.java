package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G5 택배 배송
 */
public class p_5972 {

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

    static int N, M;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        int distance[] = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (distance[cur.node] < cur.dis)
                continue;

            for (Edge next : graph.get(cur.node)) {
                int next_dis = distance[cur.node] + next.dis;

                if (next_dis < distance[next.node]) {
                    distance[next.node] = next_dis;
                    pq.add(new Edge(next.node, next_dis));
                }
            }
        }

        System.out.println(distance[N - 1]);
    }
}
