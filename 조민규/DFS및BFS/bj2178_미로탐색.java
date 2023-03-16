package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2178_미로탐색 {
    static class Point{
        int x,y,dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int[] di = {0,0,-1,1};
    static int[] dj = {-1,1,0,0};
    static int N,M, ans = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for(int i = 1 ; i <= N ; i++){
            String str = br.readLine();
            for(int j = 1 ; j <= M ; j++){
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        // BFS
        bfs();

        // 출력
        System.out.println(ans);
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 1, 1));

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            ans = now.dist;

            // 이미 도착점에 도착해 더 진행할 필요가 없을 경우
            if (now.x == N && now.y == M) {
                break;
            }

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = now.x + di[d];
                int ny = now.y + dj[d];
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    queue.add(new Point(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static boolean isValid(int x, int y){
        return x >= 1 && x <= N && y >= 1 && y <= M && map[x][y] == 1;
    }
}
