package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1774_우주인과의교감 {
    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a,b;
        double dist;

        public Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.dist < o.dist) return -1;
            else if(this.dist == o.dist) return 0;
            else return 1;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", dist=" + dist +
                    '}';
        }
    }
    static int N,M; // 우주신 수, 통로 수
    static Point[] vertex; // 정점들의 좌표
    static List<Edge> edges; // 간선들
    static int[] parent;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vertex = new Point[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            vertex[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        int connected1 = Integer.parseInt(st.nextToken()) - 1;
        int connected2 = Integer.parseInt(st.nextToken()) - 1;

        // 부모 초기값 설정
        parent = new int[N];
        for(int i = 0 ; i < N ; i++){
            parent[i] = i;
        }

        // 간선 설정
        edges = new ArrayList<>();
        for(int i = 0 ; i < N-1 ; i++){
            for(int j = i+1 ; j < N ; j++){
                if((i == connected1 && j == connected2) || (i == connected2 && j == connected1)) { // 이미 연결된 간선일 경우
                    continue;
                }
                edges.add(new Edge(i, j, getDist(vertex[i], vertex[j])));
            }
        }

        // 간선 거리순 정렬
        Collections.sort(edges);

        // Kruskal
        int len = edges.size();
        double sum = 0;
        for(int i = 0 ; i < len ; i++){
            int a = edges.get(i).a;
            int b = edges.get(i).b;
            if (find(a) != find(b)) {
                union(a, b);
                sum += edges.get(i).dist;
            }
        }

        sum += getDist(vertex[connected1], vertex[connected2]); // 처음 입력받았던 이미 이어진 간선 길이
        System.out.println(sum);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static double getDist(Point a, Point b){
        double sum = Math.pow(Math.abs(a.x - b.x), 2.0) + Math.pow(Math.abs(a.y - b.y), 2.0);
        return Math.sqrt(sum);
    }
}
