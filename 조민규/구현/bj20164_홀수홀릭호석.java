package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj20164_홀수홀릭호석 {
    static int min = 99999, max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        cut(N, 0);
        System.out.println(min + " " + max);
    }

    public static void cut(String num, int sum){
        // 먼저 홀수 갯수를 sum에 더한다.
        for(int i = 0 ; i < num.length() ; i++){
            int n = num.charAt(i) - '0';
            if(n % 2 == 1) sum++;
        }

        // 한자리일 경우
        if(num.length() == 1){
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }
        // 두자리일 경우
        else if(num.length() == 2){
            num = String.valueOf((num.charAt(0) - '0') + (num.charAt(1) - '0'));
            cut(num, sum);
        }
        // 세 자리 이상일 경우
        else{
            for(int i = 1 ; i < num.length() - 1 ; i++){
                for(int j = i+1 ; j < num.length() ; j++){
                    int n1 = Integer.parseInt(num.substring(0, i));
                    int n2 = Integer.parseInt(num.substring(i, j));
                    int n3 = Integer.parseInt(num.substring(j));
                    cut(String.valueOf(n1 + n2 + n3), sum);
                }
            }
        }
    }
}
