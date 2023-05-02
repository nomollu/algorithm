package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1926_그림 {
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int N, M; // 세로, 가로 크기
    static int cnt, max, size; // 그림의 개수, 가장 넓은 그림의 넓이, 하나 그림의 넓이
    static int[][] map;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = 0; max = 0;
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 1) {
                    cnt++;
                    size = 0;
                    dfs(i,j);
                    max = Math.max(size, max);
                }

            }
        }
        
        // 출력
        System.out.println(new StringBuilder().append(cnt).append("\n").append(max).toString());
    }

    public static void dfs(int x, int y){
        map[x][y] = 0; // 방문 처리
        size++; // 사이즈 + 1

        // 4방 탐색
        for(int d = 0 ; d < 4 ; d++){
            int nx = x + di[d];
            int ny = y + dj[d];

            if(isValid(nx,ny)){
                dfs(nx, ny);
            }
        }
    }

    public static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M && map[x][y] == 1;
    }
}
