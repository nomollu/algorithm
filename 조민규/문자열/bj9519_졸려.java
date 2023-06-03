package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class bj9519_졸려 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(br.readLine());
        int len = sb.length(); // 입력 문자열의 길이
        boolean isHorse = sb.length() % 2 == 1; // 홀수인가?

        List<String> words = new ArrayList<>();
        words.add(sb.toString()); // ArrayList에 입력 문자열 추가

        for(int i = 0 ; i < X ; i++){
            for(int j = isHorse ? len-2 : len-1 ; j > 0 ; j-=2){
                String tmp = sb.substring(j, j+1);
                sb.delete(j,j+1);
                sb.insert(len-1, tmp); // 마지막 인덱스에 삽입
            }

            // 사이클이 돌았을 경우
            if(words.contains(sb.toString())){
                break;
            } else {
                words.add(sb.toString());
            }
        }
        
        // ArrayList의 길이 단위로 반복되는 깜빡임 단어므로 나머지가 인덱스
        System.out.println(words.get(X % words.size()));
    }
}
