package 플로이드_와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11403_경로찾기 {
    static int N;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 답 찾기
        // k : 거쳐 가는 노드, i : 출발 노드, j : 도착 노드
        for(int k = 0 ; k < N ; k++){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    // i와 k가 연결되어 있고, k와 j가 연결되어 있는가? (가중치는 상관없음)
                    if(adjMatrix[i][k] == 1 && adjMatrix[k][j] == 1){
                        // i와 j가 연결된 것으로 친다.
                        adjMatrix[i][j] = 1;
                    }
                }
            }
        }

        // 출력
        for(int i = 0 ; i < N ; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < N ; j++){
                sb.append(adjMatrix[i][j] + " ");
            }
            System.out.println(sb.toString());
        }
    }
}
