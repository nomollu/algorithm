import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
        edgeList = new ArrayList<>(); //통로끼리 좌표 거리 계산
        point = new Point[N+1]; //통로개수 좌표 담을 배열
        parents = new int[N+1]; //부모 배열

        for(int i = 0; i<=N; i++){ // 부모 세팅
            parents[i] = i;
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //좌표
            int b = Integer.parseInt(st.nextToken());
            point[i] = new Point(a,b, i);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b); //이미 연결된 통로들
        }

        // 통로들끼리 거리계산
        for(int i=1; i<=N; i++){
            for(int j=i+1; j<=N; j++){
                double distance = Math.sqrt(Math.pow(point[i].x-point[j].x,2) + Math.pow(point[i].y-point[j].y,2));
                edgeList.add(new Edge(point[i].num, point[j].num, distance));
            }
        }

        double ans = 0;
        Collections.sort(edgeList); //정렬
        // 크루스칼
        for(int i=0; i<edgeList.size(); i++){
            Edge now = edgeList.get(i);
            if(findSet(now.from) != findSet(now.to)){ 
                union(now.from, now.to);
                ans += now.weight;
            }
        }
        System.out.printf("%.2f", ans);
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight < o.weight)
                return -1;
            return 1;
        }
    }

    static class Point{
        int x,y, num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
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
