package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj12764_싸지방에간준하 {
    static class SajiLog{
        int start, end;

        public SajiLog(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static int N; // 사람의 수
    static PriorityQueue<SajiLog> input = new PriorityQueue<>(Comparator.comparingInt((SajiLog s) -> s.start));

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            input.add(new SajiLog(start, end));
        }
        int[] endTime = new int[N]; // 해당 자리의 이용 종료 시간
        int[] cnt = new int[N]; // 해당 자리를 이용한 사람의 수

        // 시작시간이 빠른 사람부터 순서대로 시작
        while(!input.isEmpty()){
            SajiLog now = input.poll();
            //System.out.println("now : " + now);

            for(int i = 0 ; i < N ; i++){
                // 해당 자리가 아직 이용시작하지 않았을 경우
                if(endTime[i] == 0){
                    endTime[i] = now.end;
                    cnt[i]++;
                    break;
                }

                // 해당 자리의 종료 시간이 들어올 사람의 시작 시간보다 작을 경우
                if(endTime[i] < now.start) {
                    endTime[i] = now.end;
                    cnt[i]++;
                    break;
                }
            }
        }

        int idx = 0;
        for(int i = 0 ; i < N ; i++){
            if(cnt[i] != 0){
                idx++;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(idx).append("\n");
        for(int i = 0 ; i < idx ; i++){
            sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
