import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold3_2143_두배열의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0; //가능한 부 배열 쌍 개수
        for (int i = 0; i < n; i++) { // A 배열
            for (int j = 0; j < m; j++) { // B 배열
                int sumA = 0;
                int sumB = 0;

                for (int k = i; k < n; k++) {
                    sumA += A[k]; // A 부 배열 합
                    for (int l = j; l < m; l++) {
                        sumB += B[l]; // B 부 배열 합
                        if (sumA + sumB == T) { //합이 T일때만 개수 증가
                            cnt++;
                        }
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
