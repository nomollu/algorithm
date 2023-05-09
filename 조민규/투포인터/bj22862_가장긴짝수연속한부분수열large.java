package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj22862_가장긴짝수연속한부분수열large {
    static int N, K; // 수열의 길이, 삭제할 수 있는 최대 횟수
    static int[] S;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            S[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0; // 투 포인터

        int ans = 0;
        int cnt = 0;
        while(left < N && right < N){

            // 현재 left 포인터가 홀수에 있을 경우
            if(!isEven(S[left])){
                left++;
                if(right < left)
                    right = left;
                continue;
            }

            // 현재 left 포인터가 짝수에 있을 경우
            if(isEven(S[left])){

                // cnt, right, ignoreCnt 초기화
                int secondEvenIndex = 0;
                cnt = 0;
                right = left;
                int ignoreCnt = K; // 홀수를 무시할 수 있는 남은 횟수

                // right 포인터를 움직이며 최대 짝수 수열을 찾는다.
                while(right < N){
                    if(isEven(S[right])){ // right 포인터가 짝수
                        cnt++;
                        if(left != right && secondEvenIndex == 0){
                            secondEvenIndex = right;
                        }
                    } else { // right 포인터가 홀수
                        if(ignoreCnt == 0) {
                            break;
                        }
                        else { // ignoreCnt 1 차감
                            ignoreCnt--;
                        }
                    }

                    if(right == N-1) break;
                    else right++;
                }

                // 정답 계산
                ans = Math.max(ans, cnt);

                // left와 right 포인터 위치 정렬
                if(secondEvenIndex == 0){
                    right++;
                    left = right;
                } else {
                    left = secondEvenIndex;
                    right = left;
                }

            }
        }

        System.out.println(ans);
    }

    public static boolean isEven(int n){
        return n % 2 == 0;
    }
}
