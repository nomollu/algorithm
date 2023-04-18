package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj11725_트리의부모찾기 {

    static int N;
    static List<List<Integer>> tree;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for(int i = 0 ; i < N+1 ; i++){
            tree.add(new ArrayList<>());
        }
        ans = new int[N+1]; // ans[i] = i 노드의 부모 노드
        for(int i = 0 ; i < N-1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visit = new boolean[N+1];
        visit[1] = true;

        while(!queue.isEmpty()){
            int parent = queue.poll();

            for(int now : tree.get(parent)){
                if(!visit[now]){
                    queue.add(now);
                    ans[now] = parent;
                    visit[now] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2 ; i <= N ; i++){
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
