package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S2 서버실
 */
public class p_17245 {

    static int N;
    static long total_com;
    static int server_room[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        server_room = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                server_room[i][j] = Integer.parseInt(st.nextToken());
                total_com += server_room[i][j];
            }
        }

        int left = 0, right = 10000000, ans = 0;
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                ans = mid;
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(ans);
    }

    static boolean isValid(int time) {
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += server_room[i][j] < time ? server_room[i][j] : time;
            }
        }

        return (double) cnt / total_com >= 0.5;
    }
}
