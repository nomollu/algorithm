package 플로이드_와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj21278_호석이두마리치킨 {
    static int N,M; // 건물 개수, 도로의 개수
    static int[][] adjMatrix;
    static int[] ansNode = new int[2];
    static int ansSum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        for(int i = 0 ; i <= N ; i++){
            Arrays.fill(adjMatrix[i], 9999);
            adjMatrix[i][i] = 0;
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        // 플로이드-와샬
        for(int k = 1 ; k <= N ; k++){
            for(int i = 1; i <= N ; i++){
                for(int j = 1 ; j <= N ; j++){
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }

        // 2개의 치킨집 조합
        combination(0, 0, new int[2]);

        // 출력
        System.out.println(new StringBuilder().append(ansNode[0]).append(" ").append(ansNode[1]).append(" ").append(ansSum).toString());
    }

    public static void combination(int cnt, int now, int[] selected){
        // 치킨집 2개를 선택했으면
        if(cnt == 2){
            // 모든 집에서 치킨집까지의 최소 거리를 구한다.
            int sum = 0;
            for(int i = 1 ; i <= N ; i++){
                int dist = Math.min(adjMatrix[i][selected[0]], adjMatrix[i][selected[1]]) * 2;
                sum += dist;
            }

            // 합이 더 작으면
            if(ansSum > sum){
                ansSum = sum;
                ansNode[0] = selected[0];
                ansNode[1] = selected[1];
            }

            // 합이 같으면
            if(ansSum == sum){
                // 작은 번호가 더 작을 경우
                if(ansNode[0] > selected[0]){
                    ansNode[0] = selected[0];
                    ansNode[1] = selected[1];
                }
                // 작은 번호도 같고, 큰 번호가 더 작을 경우
                if(ansNode[0] == selected[0] && ansNode[1] > selected[1]) {
                    ansNode[0] = selected[0];
                    ansNode[1] = selected[1];
                }
            }
            return;
        }

        for(int i = now+1 ; i <= N ; i++){
            if(i != selected[0] && i != selected[1]){
                selected[cnt] = i;
                now = i;
                combination(cnt+1, now, selected);
                selected[cnt] = 0;
            }
        }
    }
}
