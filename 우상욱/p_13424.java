package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G4 비밀 모임
 */
public class p_13424 {

    static class Node implements Comparable<Node> {
        int node;
        int dis;

        Node(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dis, o.dis);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i < N; i++)
                graph.add(new ArrayList<>());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            int K = Integer.parseInt(br.readLine());
            int[] friends = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++)
                friends[i] = Integer.parseInt(st.nextToken()) - 1;

            int tot_dis = Integer.MAX_VALUE;
            int ans = 0;
            for (int i = 0; i < N; i++) {
                int dis[] = dij(i, graph, N);

                int sum_dis = 0;
                for (int k = 0; k < K; k++)
                    sum_dis += dis[friends[k]];

                if (tot_dis > sum_dis) {
                    tot_dis = sum_dis;
                    ans = i + 1;
                }
            }

            System.out.println(ans);
        }
    }

    static int[] dij(int start, List<List<Node>> graph, int n_size) {
        int distances[] = new int[n_size];
        for (int i = 0; i < n_size; i++)
            distances[i] = Integer.MAX_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distances[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distances[cur.node] < cur.dis)
                continue;

            for (Node next : graph.get(cur.node)) {
                int next_dis = distances[cur.node] + next.dis;

                if (distances[next.node] > next_dis) {
                    distances[next.node] = next_dis;
                    pq.add(new Node(next.node, distances[next.node]));
                }
            }
        }

        return distances;
    }
}
