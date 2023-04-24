package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class bj1758_알바생강호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] tips = new Integer[N];
        for(int i = 0 ; i < N ; i++){
            tips[i] = Integer.parseInt(br.readLine());
        }
        long ans = 0;

        Arrays.sort(tips, Collections.reverseOrder());
        for(int i = 0 ; i < N ; i++){
            ans += Math.max(tips[i] - i, 0);
        }

        System.out.println(ans);
    }
}
