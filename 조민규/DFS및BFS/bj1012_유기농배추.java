package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1012_유기농배추 {
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    static int M, N, K, ans; // 가로 길이, 세로 길이, 배추가 심어져 있는 위치의 개수
    static int[][] map;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            test();
        }
    }

    public static void test() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        // BFS
        ans = 0;
        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] == 0) continue;

                ans++; // 땅의 갯수가 아닌 존재만 확인하면 되므로 미리 ans + 1
                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(i,j));
                map[i][j] = 0; // 방문처리 (해당 자리 0으로 만들기)

                while(!queue.isEmpty()){
                    Point p = queue.poll();
//                    map[p.x][p.y] = 0; // 해당 위치에서 방문처리하면 메모리 초과 발생!

                    for(int d = 0 ; d < 4 ; d++){
                        int newX = p.x + di[d];
                        int newY = p.y + dj[d];
                        if(isValid(newX, newY)){
                            queue.add(new Point(newX, newY));
                            map[newX][newY] = 0; // 여기서 방문처리하니 메모리 초과 문제 해결
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static boolean isValid(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N && map[x][y] == 1;
    }
}
