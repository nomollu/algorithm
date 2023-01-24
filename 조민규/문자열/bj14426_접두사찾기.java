package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14426_접두사찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집합 S에 포함된 문자열 갯수, 
        int M = Integer.parseInt(st.nextToken()); // 전체 문자열 갯수
        String[] words = new String[N]; // N개의 입력한 문자열
        int ans = 0;

        for(int i = 0 ; i < N ; i++){
            words[i] = br.readLine();
        }
        for(int i = 0 ; i < M ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                if(words[j].startsWith(str)){
                    ans++;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
