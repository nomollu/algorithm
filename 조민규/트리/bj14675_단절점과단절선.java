package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14675_단절점과단절선 {
    static int[] adj; // 해당 정점이 인접한 정점 개수
    static int N, Q; // 트리 정점 개수, 질의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        adj = new int[N+1];
        for(int i = 0 ; i < N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a]++;
            adj[b]++;
        }

        Q = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < Q ; i++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken()); // 1 : K번 정점이 단절점인가?, 2 : K번째 간선이 단절선인가?
            int K = Integer.parseInt(st.nextToken());

            if(T == 1) System.out.println(isVertex(K) ? "yes" : "no");
            else System.out.println("yes");
        }
    }

    public static boolean isVertex(int K){
        return adj[K] > 1 ? true : false;
    }
}
