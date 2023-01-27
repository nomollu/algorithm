package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj14650_걷다보니신천역삼Small {
    static int N, ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] selected = new int[N];

        for(int i = 0 ; i < 3 ; i++){
            combination(0, i, selected);
        }

        System.out.println(ans / 3);
    }

    public static void combination(int cnt, int now, int[] selected){
        // 조합이 다 만들어지면 제일 큰 자릿수가 0이 아닌지만 보고 맞으면 ans + 1 한다.
        if(cnt == N){
            if(selected[N-1] != 0){
                int sum = 0;
                for(int i = 0 ; i < N ; i++){
                    sum += selected[i];
                }
                ans = sum % 3 == 0 ? ans+1 : ans;
            }
            return;
        }

        selected[cnt] = now;

        for(int i = 0 ; i < 3 ; i++){
            combination(cnt+1, i, selected);
        }
    }
}
