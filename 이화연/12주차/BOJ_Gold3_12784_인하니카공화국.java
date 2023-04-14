import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold3_12784_인하니카공화국 {
    static int N,M;
    static ArrayList<Node>[] nodes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //섬의 수
            M = Integer.parseInt(st.nextToken()); //다리 수
            nodes = new ArrayList[N+1];
            visited = new boolean[N+1];

            for(int i=1; i<=N; i++){
                nodes[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                nodes[a].add(new Node(b,w));
                nodes[b].add(new Node(a,w));
            }

            visited[1] = true;
            System.out.println(dfs(1, 0));
        }
    }

    static int dfs(int num, int weight){
        int sum = 0;

        for(Node next : nodes[num]){
            if(!visited[next.v]){
                visited[next.v] = true;
                sum += dfs(next.v, next.w);
            }
        }

        if(sum == 0){ //리프노드임
            return weight;
        }else if(weight == 0){ //루트노드임
            return sum;
        }else{
            return Math.min(sum, weight);
        }
    }

    static class Node{
        int v,w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
