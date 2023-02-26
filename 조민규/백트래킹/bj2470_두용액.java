package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2470_두용액 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 배열을 오름차순으로 정렬한다.
        Arrays.sort(arr);

        // 모두 양수일 경우 예외처리
        if(arr[0] > 0){
            System.out.println(arr[0] + " " + arr[1]);
            return;
        } else if(arr[N-1] < 0){
            System.out.println(arr[N-2] + " " + arr[N-1]);
            return;
        }


        // 배열의 양끝에 투포인터를 설정한다.
        long[] ans = new long[2];
        int left = 0;
        int right = N-1;
        long minSum = Math.abs(arr[left] - arr[right]); // 최소 합

        while(left < right){
            long now = arr[left] + arr[right];

            // 현재 두 용액의 합이 0일 경우 => 끝내도 된다.
            if(now == 0){
                ans[0] = arr[left];
                ans[1] = arr[right];
                break;
            }

            // 현재 두 용액의 합이 최소일 경우
            if(Math.abs(now) < minSum){
                minSum = Math.abs(now);
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            // 다음 left 포인터를 움직일지 right 포인터를 움직일지 선택한다.
            if(now < 0){
                left++;
            } else {
                right--;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
