package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj1874_스택수열 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean isNo = false;
        int idx = 1; // 현재 숫자
        for(int i = 0 ; i < N ; i++){

            // 다음 맞춰야 할 숫자가 idx보다 클 경우
            while(idx <= input[i]){
                stack.push(idx++);
                sb.append("+\n");
            }

            // 다음 맞춰야 할 숫자와 일치될 경우
            if(stack.peek() == input[i]){
                stack.pop();
                sb.append("-\n");
                continue;
            }
            
            isNo = true; // 위와 일치하지 못할 경우 수열을 만들 수 없다.
        }

        System.out.println(isNo ?  "NO" : sb.toString());
    }
}
