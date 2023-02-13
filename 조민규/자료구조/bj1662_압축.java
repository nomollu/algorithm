package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj1662_압축 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        
        Stack<Integer> stack = new Stack<>(); // ) 위치 저장
        int now = S.length - 1; // 현재 배열의 인덱스
        long ans = 0; // 총 길이
        long len = 0; // 제일 바깥 괄호 길이

        while(now >= 0){ // 맨뒤에서부터 본다.
            if(S[now] == ')'){
                stack.add(now);
            }
            else if(S[now] == '('){
                if(now-1 >= 0 && S[now-1] != '(' && S[now-1] != ')'){
                    int start = now;
                    int end = stack.pop();
                    len += (S[start-1]-'0') * (end - start - 1) ;
                    now--;
                }
            }
            else { // 숫자일 경우
                if(stack.isEmpty()){ // 괄호 안이 아닐 경우
                    ans += len; // 여태까지 모은 괄호 내 길이를 더한다.
                    len = 0;
                    ans++;
                } else { // 괄호 안일 경우
                    //len++;
                }
            }
            now--;
        }
        ans += len;
        System.out.println(ans);
    }
}
