package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2194_유닛이동시키기 {

    static class Point{
        int x,y,sum;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", sum=" + sum +
                    '}';
        }
    }

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int N,M; // 맵 세로가로 길이
    static int lenX,lenY; // 유닛의 세로가로 길이
    static int K; // 장애물의 갯수
    static Point start; // 시작점의 위치
    static Point end; // 도착점의 위치
    static Point[] rock; // 장애물의 위치
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lenX = Integer.parseInt(st.nextToken());
        lenY = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        rock = new Point[K];
        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            rock[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // BFS
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][M+1];
        queue.add(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Point now = queue.poll();
//            System.out.println(now.toString());

            // 도착했는지 체크
            if(now.x == end.x && now.y == end.y){
                ans = Math.min(ans, now.sum);
            }

            for(int d = 0 ; d < 4 ; d++){
                int nx = now.x + di[d];
                int ny = now.y + dj[d];
                if(isValid(nx, ny) && !visited[nx][ny]){
                    queue.add(new Point(nx, ny, now.sum+1));
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static boolean isValid(int x, int y){
        // 범위에서 벗어나는지 체크
        if(x <= 0 || x > N || y <= 0 || y > M || x+lenX-1 > N || y+lenY-1 > M)
            return false;

        // 장애물이 있는지 체크
        for(int i = 0 ; i < K ; i++){
            int rockX = rock[i].x;
            int rockY = rock[i].y;
            if((x <= rockX && rockX <= x+lenX-1) && (y <= rockY && rockY <= y+lenY-1)){
                return false;
            }
        }

        return true;
    }
}
