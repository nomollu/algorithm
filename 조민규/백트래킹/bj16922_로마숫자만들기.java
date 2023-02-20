package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj16922_로마숫자만들기 {
    static int[] roma = {1,5,10,50};
    static int N, ans = 0;
    static boolean[] checked = new boolean[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        backtracking(0, 0, 0);
        System.out.println(ans);
    }

    public static void backtracking(int now, int cnt, int sum){
        if(cnt == N){
            if(!checked[sum]){
                checked[sum] = true;
                ans++;
            }
            return;
        }

        for(int i = now ; i < roma.length ; i++){
            backtracking(i, cnt+1, sum+roma[i]);
        }
    }
}
