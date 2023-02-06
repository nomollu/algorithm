package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1189_컴백홈 {

    // start : (R-1, 0), end : (0, C-1)
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    static int R, C, K, ans = 0; // 세로, 가로, 거리
    static char[][] map; // 맵
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        for(int i = 0 ; i < R ; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited[R-1][0] = true;
        go(new Point(R-1, 0), 1, visited);
        System.out.println(ans);
    }

    public static void go(Point now, int cnt, boolean[][] visited){
        if(now.x == 0 && now.y == C-1 && cnt == K){
            ans++;
            return;
        }

        for(int d = 0 ; d < 4 ; d++){
            if(isValid(now.x+di[d], now.y+dj[d], visited)){
                visited[now.x][now.y] = true;
                go(new Point(now.x+di[d], now.y+dj[d]), cnt+1, visited);
                visited[now.x][now.y] = false;
            }
        }
    }

    public static boolean isValid(int x, int y, boolean[][] visited){
        return x >= 0 && x < R && y >= 0 && y < C && !visited[x][y] && map[x][y] != 'T';
    }
}
