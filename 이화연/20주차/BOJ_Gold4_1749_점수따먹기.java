import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold4_1749_점수따먹기 {
    static int N,M, prefixSum[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prefixSum = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                prefixSum[i][j] = Integer.parseInt(st.nextToken());
                prefixSum[i][j] += prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1]; //누적합 구하기
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int startI = 1; startI <=N; startI++){
            for(int endI = startI; endI <= N; endI++){
                for(int startJ = 1; startJ <= M; startJ++){
                    for(int endJ = startJ; endJ <= M; endJ++){
                        ans = Math.max(ans, prefixSum[endI][endJ] - prefixSum[startI-1][endJ] - prefixSum[endI][startJ-1] + prefixSum[startI-1][startJ-1]);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
