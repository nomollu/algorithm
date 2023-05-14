import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold5_2194_유닛이동시키기 {
    static int N,M,A,B,K, map[][];
    static Point start, end;
    static int di[]= {-1,1,0,0};
    static int dj[] = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        A = Integer.parseInt(st.nextToken()); //유닛 행
        B = Integer.parseInt(st.nextToken()); //유닛 열
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = -1; //장애물
        }

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start.x, start.y));
        boolean[][] visited = new boolean[N+1][M+1];
        visited[start.x][start.y] = true;

        int cnt = 0; //이동 횟수
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int s=0; s<size; s++){
                Point now = queue.poll();

                if(now.x == end.x && now.y == end.y){ //도착함
                    return cnt;
                }

                for(int d=0; d<4; d++){
                    int nexti = now.x+di[d];
                    int nextj = now.y+dj[d];
                    // 유닛 모두 이동할 수 있고 아직 방문 안한 곳일때만
                    if(move(d, now.x, now.y) && !visited[nexti][nextj]){
                        queue.add(new Point(nexti,nextj));
                        visited[nexti][nextj] = true;
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    //유닛 모두 이동시켰을때 이동 가능한지
    static boolean move(int d, int x, int y){
        for(int i=x; i<x+A; i++){
            for(int j=y; j<y+B; j++){
                int nexti = di[d]+i;
                int nextj = dj[d]+j;

                if(nexti < 1 || nexti > N || nextj < 1 || nextj > M || map[nexti][nextj] == -1){
                    return false;
                }
            }
        }
        return true;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
