package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S2 과자 나눠주기
 */
public class p_16410 {

    static int M, N;
    static int snacks[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        snacks = new int[N];
        for (int i = 0; i < N; i++)
            snacks[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = 1000000000;
        int ans = 0;
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                left = mid;
                ans = mid;
            } else
                right = mid;
        }

        System.out.println(ans);
    }

    static boolean isValid(int dist) {
        int cnt = 0;
        for (int i = 0; i < N; i++)
            cnt += snacks[i] / dist;

        return cnt >= M;
    }
}
