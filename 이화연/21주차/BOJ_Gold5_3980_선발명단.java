import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_3980_선발명단 {
    static int map[][], T, ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            map = new int[11][11];
            visited = new boolean[11];
            StringTokenizer st = null;

            for(int i=0; i<11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = -1;
            dfs(0,0);
            System.out.println(ans);
        }
    }

    static void dfs(int cnt, int sum){ // cnt : 포지션
        if(cnt >= 11){
            ans = Math.max(ans,sum);
            return;
        }

        for(int i=0; i<11; i++){
            if(!visited[i] && map[i][cnt] != 0){
                visited[i] = true;
                dfs(cnt+1, sum+map[i][cnt]);
                visited[i] = false;
            }
        }
    }
}
