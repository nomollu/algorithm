package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj13565_침투 {

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int M,N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for(int i = 0 ; i < M ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = input.charAt(j) - '0';
            }
        }

        for(int i = 0 ; i < N ; i++){
            if(map[0][i] == 0){
                boolean result = bfs(i);
                if(result){
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");
    }

    public static boolean bfs(int start){
        boolean[][] visited = new boolean[M][N];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, start));
        visited[0][start] = true;

        while(!queue.isEmpty()){
            Point now = queue.poll();

            // 맨 아래에 도달한 경우
            if(now.x == M-1){
                return true;
            }

            for(int d = 0 ; d < 4 ; d++){
                int nx = now.x + di[d];
                int ny = now.y + dj[d];

                if(nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]){
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }
}
