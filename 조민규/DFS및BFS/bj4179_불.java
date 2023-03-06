package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj4179_불 {
    static class Point {
        int x, y, time;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int R,C, ans = Integer.MAX_VALUE;
    static char[][] map;
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Queue<Point> fires = new LinkedList<>();
        int x = 0, y = 0;
        for(int i = 0 ; i < R ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < C ; j++){
                map[i][j] = str.charAt(j);
                // 지훈이일 경우
                if(map[i][j] == 'J'){
                    x = i;
                    y = j;
                    //map[i][j] = '.';
                }
                // 불일 경우
                if(map[i][j] == 'F'){
                    fires.add(new Point(i,j));
                }
            }
        }

        // 이미 탈출 가능한 위치일 경우
        if(isBreak(x, y)){
            System.out.println(1);
            return;
        }

        // BFS
        bfs(x,y,fires);
        System.out.println(ans == Integer.MAX_VALUE ? "IMPOSSIBLE" : ans);
    }

    // x,y : 지훈 초기 위치 , fires : 현재 불 위치 담는 큐
    public static void bfs(int x, int y, Queue<Point> fires){
        
        Queue<Point> queue = new LinkedList<>(); // 지훈의 위치 큐
        queue.add(new Point(x,y, 1)); // 지훈 초기 위치 및 시간 설정
        boolean[][] visited = new boolean[R][C]; // 지훈의 방문 체크 배열

        // BFS
        while (!queue.isEmpty()){

            // 지훈이의 새 위치
            Point point = queue.poll();
            visited[point.x][point.y] = true;

            // 불 확산
            Queue<Point> newFires = new LinkedList<>();
            while(!fires.isEmpty()){
                Point fire = fires.poll();
                for(int d = 0 ; d < 4 ; d++){
                    int nX = fire.x + di[d];
                    int nY = fire.y + dj[d];

                    if(isValid(nX, nY)){
                        map[nX][nY] = 'F';
                        newFires.add(new Point(nX, nY));
                    }
                }
            }
            fires = new LinkedList<>(newFires);

            showMap(map, point);

            // 지훈이가 탈출 가능한가?
            if(isBreak(point.x,point.y)){
                ans = Math.min(ans, point.time);
                continue; // 여기서 새 위치를 탐색하는 것은 어차피 시간이 더 걸리는 행위므로
            }

            // 지훈이가 이동 가능한 새 위치 탐색
            for(int d = 0 ; d < 4 ; d++){
                int nX = point.x + di[d];
                int nY = point.y + dj[d];

                if(isValid(nX, nY) && !visited[nX][nY]) {
                    queue.add(new Point(nX, nY,  point.time + 1));
                }
            }
        }

    }

    public static boolean isValid(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C && map[x][y] != '#' && map[x][y] != 'F';
    }

    public static boolean isBreak(int x, int y){
        return x == 0 || x == R-1 || y == 0 || y == C-1;
    }


    public static void showMap(char[][] map, Point point){
        System.out.println("\n==========");
        System.out.println("* 위치 : (" + point.x + "," + point.y + ")");
        System.out.println("* 시간 : " + point.time);
        for(int i = 0 ; i < map.length ; i++){
            for(int j = 0 ; j < map[i].length ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("==========");
    }
}
