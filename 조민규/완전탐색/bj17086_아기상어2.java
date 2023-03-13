package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x,y, dist;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class bj17086_아기상어2 {
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static int N,M;
    static int[][] map;
    static Queue<Point> sharks = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) sharks.add(new Point(i,j));
            }
        }

        // 최대 안전거리 찾기
        System.out.println(bfs(sharks));
    }

    public static int bfs(Queue<Point> sharks){
        boolean[][] visited = new boolean[N][M];

        int result = 0;
        while(!sharks.isEmpty()){
            Point now = sharks.poll();

            for(int d = 0 ; d < 8 ; d++){
                int nx = now.x + di[d];
                int ny = now.y + dj[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] < 1){
                    visited[nx][ny] = true;
                    map[nx][ny] = map[now.x][now.y] + 1;
                    sharks.add(new Point(nx, ny));
                    result = Math.max(map[now.x][now.y], result);
                }
            }
        }
        return result;
    }
}
