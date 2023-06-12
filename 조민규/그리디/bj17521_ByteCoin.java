package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17521_ByteCoin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken()); // 요일의 수
        long W = Long.parseLong(st.nextToken()); // 초기 현금
        int[] price = new int[N];
        for(int i = 0 ; i < N ; i++){
            price[i] = Integer.parseInt(br.readLine());
        }

        long buyNum = 0;
        for(int i = 0 ; i < N-1 ; i++){

            // 보유중인데 다음 날 떨어질 경우
            if(price[i] > price[i+1] && buyNum > 0){
                W += price[i] * buyNum;
                price[i] = 0;
                buyNum = 0;
                continue;
            }

            // 보유중이지 않은데 다음날 오를 경우
            if(price[i] < price[i+1] && buyNum == 0) {
                buyNum = W / price[i]; // 구매할 갯수 설정
                W -= price[i] * buyNum; // 현금 차감
            }
        }

        W += price[N-1] * buyNum;
        System.out.println(W);
    }
}
