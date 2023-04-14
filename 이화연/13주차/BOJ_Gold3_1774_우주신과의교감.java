import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold3_1774_우주신과의교감 {
    static int N,M, parents[];
    static ArrayList<Edge> edgeList;
    static Point[] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //우주신 개수
        M = Integer.parseInt(st.nextToken()); //통로 개수
        edgeList = new ArrayList<>();
        point = new Point[N]; //통로개수 좌표 담을 배열..?

        makeSet();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //좌표
            int b = Integer.parseInt(st.nextToken());
            point[i] = new Point(a,b);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b); //이미 연결된 통로들..
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void makeSet() {
        parents = new int[N];
        // 부모 노드 자신의 값으로 세팅
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public static int findSet(int a) {
        if (a == parents[a])
            return a;
        return parents[a] = findSet(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
