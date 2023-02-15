package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj20115_에너지드링크 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] X = new int[N];
        double ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            X[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(X);
        for(int i = 0 ; i < N-1 ; i++){
            ans += (X[i] / 2.0);
        }
        ans += X[N-1];

        System.out.println(ans);
    }
}
