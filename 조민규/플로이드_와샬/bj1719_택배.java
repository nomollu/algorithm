package 플로이드_와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1719_택배 {
    static int N, M; // 정점 개수, 간선 개수
    static int[][] adjMatrix; // 가중치 있는 인접 행렬
    static int[][] ans; // 경로표
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        ans = new int[N+1][N+1];

        // 배열 초기화
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                if(i == j) continue;
                adjMatrix[i][j] = 200 * 10000 + 1;
                ans[i][j] = j;
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = w;
            adjMatrix[b][a] = w;
            ans[a][b] = b;
            ans[b][a] = a;
        }

        for(int k = 1 ; k <= N ; k++){
            for(int i = 1 ; i <= N ; i++){
                for(int j = 1 ; j <= N ; j++){
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){ // 경유하는 게 더 빨리 갈 경우
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        ans[i][j] = ans[i][k];
                    }
                }
            }
        }

        print(ans);
    }

    public static void print(int[][] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                sb.append(i == j ? "-" : arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
