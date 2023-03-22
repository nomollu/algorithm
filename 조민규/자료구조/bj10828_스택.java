package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj10828_스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < N ; i++){
            String[] input = br.readLine().split(" ");

            if(input[0].equals("push")){
                stack.push(Integer.parseInt(input[1]));
            } else if(input[0].equals("pop")){
                System.out.println(stack.isEmpty() ? -1 : stack.pop());
            } else if(input[0].equals("size")){
                System.out.println(stack.size());
            } else if(input[0].equals("empty")){
                System.out.println(stack.isEmpty() ? 1 : 0);
            } else if(input[0].equals("top")){
                System.out.println(stack.isEmpty() ? -1 : stack.peek());
            }
        }
    }
}
