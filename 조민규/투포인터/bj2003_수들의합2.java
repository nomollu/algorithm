package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 구하기
        int left = 0, right = 0, sum = A[0];
        while(left < N && right < N){
            // sum이 M과 같으면 ans + 1하고 다음.
            if(sum == M){
                ans++;
                sum -= A[left];
                left++;
                if(++right < N) sum += A[right];
            }

            // sum이 M보다 작으면
            else if(sum < M){
                if(++right < N) sum += A[right];
            }

            // sum이 M보다 크면
            else if(sum > M){
                if(left == right){
                    right++;
                    if(++left < N) sum = A[left];
                }
                else {
                    sum -= A[left];
                    left++;
                }
            }
        }
        System.out.println(ans);
    }
}
