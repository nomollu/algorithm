import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold5_20168_골목대장호석 {
    static int N,M,A,B,C, ans;
    static ArrayList<Road>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //교차로 개수
        M = Integer.parseInt(st.nextToken()); //골목 개수
        A = Integer.parseInt(st.nextToken()); //시작
        B = Integer.parseInt(st.nextToken()); //도착
        C = Integer.parseInt(st.nextToken()); //가진돈
        list = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Road(b,c));
            list[b].add(new Road(a,c));
        }

        ans = Integer.MAX_VALUE; //한 골목에서 내야하는 최대 요금의 최솟값
        visited[A] = true;
        dfs(A, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfs(int now, int sum, int max){
        if(now == B){ //도착함
            ans = Math.min(ans, max); //각 골목에서 낼 수 있는 최대 수금액들 중 최솟값
            return;
        }
        for(int i=0; i<list[now].size(); i++){
            Road r = list[now].get(i);
            //아직 안간 곳, 가진돈으로 갈 수 있는 곳
            if(!visited[r.end] && sum+r.cost <= C){
                visited[r.end] = true;
                // 비용은 현재 골목에서 최대 수금액을 계산해서 넘겨줌
                dfs(r.end, sum+ r.cost, Math.max(max, r.cost));
                visited[r.end] = false;
            }
        }
    }

    static class Road{
        int end, cost;
        public Road(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
