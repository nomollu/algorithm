package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G3 제자리 멀리 뛰기
 */
public class p_6208 {

    static int d, n, m;
    static int islands[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // input islands info
        islands = new int[n + 1];
        islands[0] = 0;
        for (int i = 1; i <= n; i++)
            islands[i] = Integer.parseInt(br.readLine());
        Arrays.sort(islands);

        // binary search
        int low = 0, high = d, mid, ans = -1;
        while (low <= high) {
            mid = (low + high) / 2;

            if (isValid(mid)) {
                low = mid + 1;
                ans = mid;
            } else
                high = mid - 1;
        }

        System.out.println(ans);
    }

    static boolean isValid(int dist) {
        int cnt = 0;
        int copy_islands[] = new int[n + 1];
        for (int i = 0; i <= n; i++)
            copy_islands[i] = islands[i];

        for (int i = 0; i <= n; i++) {
            int start = copy_islands[i];
            if (start == -1)
                continue;
            if (start + dist > d)
                return false;

            for (int j = i + 1; j <= n; j++) {
                int island_dist = copy_islands[j];
                if (island_dist < start + dist) {
                    cnt++;
                    copy_islands[j] = -1;
                } else
                    break;
            }
        }

        return cnt <= m;
    }
}
