import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Gold5_20164_홀수홀릭호석 {
    static int min, max;
    static String N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine(); //수
        max = -1;
        min = Integer.MAX_VALUE;

        cal(N, 0);
        System.out.println(min+" "+max);
    }

    static void cal(String N, int cnt){
        //홀수 개수 카운트
        int nowCnt = count(N);

        if(N.length() == 1){ //수가 1자리일때
            min = Math.min(nowCnt+cnt, min);
            max = Math.max(nowCnt+cnt, max);
        }else if(N.length() == 2){ //수가 2자리일때
            int num1 = Integer.parseInt(N.charAt(0)+"");
            int num2 = Integer.parseInt(N.charAt(1)+"");
            nowCnt = count(String.valueOf(num1)) + count(String.valueOf(num2));
            cal(String.valueOf(num1+num2), cnt+nowCnt);
        }else{ //수가 3자리 이상일때
            for(int i=1; i<N.length()-1; i++){
                for(int j=i+1; j<N.length(); j++){
                    int num1 = Integer.parseInt(N.substring(0, i));
                    int num2 = Integer.parseInt(N.substring(i, j));
                    int num3 = Integer.parseInt(N.substring(j, N.length()));
                    nowCnt = count(String.valueOf(num1)) + count(String.valueOf(num2))+count(String.valueOf(num3));
                    cal(String.valueOf(num1+num2+num3), cnt+nowCnt);

                }
            }
        }
    }

    //홀수 개수 세기
    static int count(String N){
        int cnt = 0;
        for(int i=0; i<N.length(); i++){
            if(Integer.parseInt(N.charAt(i)+"") % 2 != 0){
                cnt++;
            }
        }
        return cnt;
    }
}
