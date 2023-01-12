package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11047_동전0 {
    public static int N, K; // N개의 줄, 동전가치 K
    public static int[] A; // 동전의 가치
    public static int cnt = 0; // 동전 개수
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        for(int i = 0 ; i < N ; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // 높은 가치의 동전부터 보자.
        for(int i = A.length-1 ; i >= 0 ; i--){
            // A[i]가 K보다 작을 때, K가 0이 되기 전까지 A[i]의 값을 반복해서 빼준다.
            // K / A[i] (몫)으로 cnt를 표현할 수 있다.
            // K % A[i] (나머지)으로 남은 K의 값을 표현할 수 있다.
            if(K - A[i] >= 0){
                cnt += K / A[i];
                K = K % A[i];
            }
        }
        
        // 정답 출력
        System.out.println(cnt);
    }
}
