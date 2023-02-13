package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1790_수이어쓰기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken()); // 몇번째 숫자까지 이어쓸지
        long K = Integer.parseInt(st.nextToken()); // K번째 자리 숫자는?

        // 0. 한자리 입력했을 때
        if(K < 10){
            System.out.println(K);
            return;
        }

        // 1. 자릿수 n을 먼저 찾는다.
        int digit = 0; // k번째 숫자의 자릿수
        int tmp = 0;
        while(true){
            K -= 9 * Math.pow(10, digit-1) * digit;
            tmp += 9 * Math.pow(10, digit-1) * digit;
            if(K < 0){
                K += 9 * Math.pow(10, digit-1) * digit;
                tmp -= 9 * Math.pow(10, digit-1) * digit;
                break;
            }
            digit++;
        }

        if(tmp < N){
            System.out.println(-1);
            return;
        }

        // 2. 정답이 위치한 숫자를 찾는다.
        int number = (int)Math.pow(10, digit-1); // K번째 자릿수가 속한 수
        while(K > digit){
            K -= digit;
            number += 1;
        }

        // 3. 정답이 위치한 숫자에서 해당 인덱스의 정답을 찾는다.
        System.out.println(String.valueOf(number).charAt((int)K-1));
    }
}
