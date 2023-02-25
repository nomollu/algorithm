package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16953_AB {
    static long A, B;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        dfs(0, A);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void dfs(int cnt, long num){
        if(num == B){
            ans = cnt + 1;
            return;
        }
        else if(num > B){
            return;
        }
        else {
            dfs(cnt+1, num * 2);
            dfs(cnt+1, (num * 10) + 1);
        }
    }
}
