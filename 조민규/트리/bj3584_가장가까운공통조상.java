package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class bj3584_가장가까운공통조상 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++) {
            test();
        }
    }

    public static void test() throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] nodes = new int[N+1];

        for(int i = 0 ; i < N-1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            nodes[child] = parent; // 노드의 부모를 설정
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int findNode[] = new int[2];
        findNode[0] = Integer.parseInt(st.nextToken());
        findNode[1] = Integer.parseInt(st.nextToken());
        Stack<Integer>[] stack = new Stack[2];
        stack[0] = new Stack<>();
        stack[1] = new Stack<>();

        // 2개 노드들의 부모를 모두 찾음
        for(int i = 0 ; i < 2 ; i++){
            int nowNode = findNode[i];
            while (nowNode != 0){
                stack[i].add(nowNode);
                nowNode = nodes[nowNode];
            }
        }

        // 2개 스택을 빼면서 달라질때까지 비교
        int n0 = stack[0].pop();
        int n1 = stack[1].pop();
        int find0 = 0, find1 = 0;
        while (true){
            find0 = stack[0].isEmpty() ? find0 : stack[0].peek();
            find1 = stack[1].isEmpty() ? find1 : stack[1].peek();
            // 공통 조상을 찾았을 경우
            if(find0 != find1){
                System.out.println(n0);
                break;
            }

            if(!stack[0].isEmpty()) n0 = stack[0].pop();
            if(!stack[1].isEmpty()) n1 = stack[1].pop();
        }
    }
}
