package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj27969_ILoveJavascript {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        while(st.hasMoreTokens()){
            String now = st.nextToken();

            if(now.equals("[")){
                continue;
            } else if(now.equals("]")){
                sum += 8;
            } else if(now.charAt(0)-'0' >= 0 && now.charAt(0)-'0' <= 9){ // 숫자일 경우
                sum += 8;
            } else {
                sum += now.length() + 12;
            }
        }

        System.out.println(sum);
    }
}
