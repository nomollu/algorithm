package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj20168_골목대장호석 {
    static int N,M,A,B,C; // 교차로 수, 골목 수, 시작점, 도착점, 가진 돈
    static int[][] adjMatrix;
    static int ansShit = -1;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = w;
            adjMatrix[b][a] = w;
        }

        go(A, C, 0, new boolean[N+1]);
        System.out.println(ansShit);
    }

    public static void go(int now, int money, int shit, boolean[] visit){
        // 도착했을 경우
        if(now == B){
            // 첫 도착하는 경우일 때
            if(ansShit == -1 || ansShit > shit){
                ansShit = shit;
            }
            return;
        }

        // 다음 이동지
        for(int i = 1 ; i <= N ; i++){
            if(adjMatrix[now][i] == 0 || visit[i] || (money - adjMatrix[now][i] < 0)) continue;

            visit[i] = true;
            go(i, money - adjMatrix[now][i], Math.max(shit, adjMatrix[now][i]), visit);
            visit[i] = false;
        }
    }
}
