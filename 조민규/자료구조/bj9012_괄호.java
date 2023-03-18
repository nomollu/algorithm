package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj9012_괄호 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T ; t++){
            test();
        }
    }

    public static void test() throws IOException {
        Stack<Character> stack = new Stack<>();
        String str = br.readLine();

        // 괄호 검사
        for(int s = str.length() - 1 ; s >= 0 ; s--){
            if(str.charAt(s) == ')') {
                stack.add(str.charAt(s));
            } else {
                // 이미 스택이 비었을 경우
                if(stack.isEmpty()){
                    System.out.println("NO");
                    return;
                }
                stack.pop();
            }
        }

        // 출력
        if(stack.isEmpty()){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
