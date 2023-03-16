package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj2667_단지번호붙이기 {
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int N, num = 1;
    static List<Integer> ans = new ArrayList<>();
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 모든 좌표에 대해 BFS 수행
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                bfs(new Point(i,j));
            }
        }

        // 정답 출력
        System.out.println(ans.size());
        Collections.sort(ans);
        for(int a : ans){
            System.out.println(a);
        }
    }

    public static void bfs(Point start){
        // 집이 없거나, 이미 방문한 집이면 return
        if(map[start.x][start.y] == 0 || visited[start.x][start.y]) return;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start.x, start.y));
        visited[start.x][start.y] = true;

        int cnt = 0; // 단지에 속한 집의 수
        while(!queue.isEmpty()){
            Point now = queue.poll();
            cnt++;

            for(int d = 0 ; d < 4 ; d++){
                int nx = now.x + di[d];
                int ny = now.y + dj[d];
                if(isValid(nx, ny)){
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        ans.add(cnt);
        num++;
    }

    public static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N && map[x][y] != 0 && !visited[x][y];
    }
}
