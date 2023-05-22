package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G5 유닛 이동 시키기
 */
public class p_2194 {

    static int N, M, A, B, K;
    static int map[][];
    static int dx[] = { 0, 0, -1, 1 };
    static int dy[] = { -1, 1, 0, 0 };

    static int s_x, s_y, e_x, e_y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = 0;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = -1; // -1 wall
        }

        st = new StringTokenizer(br.readLine());
        s_x = Integer.parseInt(st.nextToken()) - 1;
        s_y = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        e_x = Integer.parseInt(st.nextToken()) - 1;
        e_y = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(BFS());
    }

    static int BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { s_x, s_y, 0 }); // { x, y, t }
        map[s_x][s_y] = 1; // 1 visited

        while (!q.isEmpty()) {
            int q_size = q.size();

            for (int i = 0; i < q_size; i++) {
                int popped[] = q.poll();
                int x = popped[0];
                int y = popped[1];
                int t = popped[2];

                if (x == e_x && y == e_y)
                    return t;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (isValid(nx, ny) && map[nx][ny] != 1) {
                        map[nx][ny] = 1;
                        q.add(new int[] { nx, ny, t + 1 });
                    }
                }
            }
        }

        return -1;
    }

    static boolean isValid(int x, int y) {
        for (int i = x; i < x + A; i++)
            for (int j = y; j < y + B; j++)
                if (i < 0 || j < 0 || i >= N || j >= M || map[i][j] == -1)
                    return false;

        return true;
    }
}
