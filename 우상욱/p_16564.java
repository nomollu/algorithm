// S1 히오스 프로게이머
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p_16564 {

    static int N, K;
    static int[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        levels = new int[N];
        for(int i = 0; i < N; i++)
            levels[i] = Integer.parseInt(br.readLine());
        
        long left = 1, right = 2000000001;
        while(left + 1 < right) {
            long mid = (left + right) / 2;

            if(isValid(mid))
                left = mid;
            else
                right = mid;
        }

        System.out.println(left);
    }

    static boolean isValid(long target_level) {
        long sum = 0;
        for(int i = 0; i < N; i++)
            if(levels[i] < target_level)
                sum += (target_level - levels[i]);

        return sum <= K;
    }
}
