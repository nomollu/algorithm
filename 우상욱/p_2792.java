package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S1 보석 상자
 */
public class p_2792 {

    static int N, M;
    static int[] jewels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jewels = new int[M];
        for (int i = 0; i < M; i++)
            jewels[i] = Integer.parseInt(br.readLine());

        int left = 0, right = 1000000000, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                right = mid - 1;
                ans = mid;
            } else
                left = mid + 1;
        }

        System.out.println(ans);
    }

    static boolean isValid(int jealousy) {
        int cnt = 0;
        for (int i = 0; i < M; i++)
            cnt += Math.ceil((double) jewels[i] / jealousy);

        return cnt <= N;
    }
}
