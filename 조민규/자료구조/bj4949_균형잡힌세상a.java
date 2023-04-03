package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj4949_균형잡힌세상 {
    private final static char LEFT_SMALL = '(', RIGHT_SMALL = ')', LEFT_LARGE = '[', RIGHT_LARGE = ']';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            // 입력
            String input = br.readLine();
            if(input.equals(".")) break;

            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            for(int i = 0 ; i < input.length() ; i++){
                char now = input.charAt(i);

                if(now == LEFT_SMALL){
                    stack.add(now);
                } else if(now == RIGHT_SMALL){
                    // 스택이 비었을 경우, 다른 괄호를 만났을 경우
                    if(stack.isEmpty() || stack.peek() == LEFT_LARGE){
                        flag = false;
                        break;
                    }
                    stack.pop();
                } else if(now == LEFT_LARGE){
                    stack.add(now);
                } else if(now == RIGHT_LARGE){
                    // 스택이 비었을 경우, 다른 괄호를 만났을 경우
                    if(stack.isEmpty() || stack.peek() == LEFT_SMALL) {
                        flag = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if(!stack.isEmpty()) flag = false;
            System.out.println(flag ? "yes" : "no");
        }
    }
}
