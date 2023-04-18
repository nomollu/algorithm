package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj12764_싸지방에간준하 {
    static class SajiLog{
        int start, end, seat;

        public SajiLog(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public SajiLog(int start, int end, int seat) {
            this.start = start;
            this.end = end;
            this.seat = seat;
        }

        @Override
        public String toString() {
            return "SajiLog{" +
                    "start=" + start +
                    ", end=" + end +
                    ", seat=" + seat +
                    '}';
        }
    }
    static int N; // 사람의 수
    static PriorityQueue<SajiLog> input = new PriorityQueue<>(Comparator.comparingInt((SajiLog s) -> s.start).thenComparing((SajiLog s) -> s.end));
    static PriorityQueue<SajiLog> pq = new PriorityQueue<>(Comparator.comparingInt((SajiLog s) -> s.end).thenComparing((SajiLog s) -> s.seat)); // 현재 이용중
    static List<Integer> cnt = new ArrayList<>(); // 해당 자리를 이용한 사람의 수
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

        // 시작시간이 빠른 사람부터 순서대로 시작
        while(!input.isEmpty()){
            SajiLog now = input.poll();
            //System.out.println("now : " + now);
            // 초기 설정
            if(cnt.size() == 0) {
                cnt.add(1);
                now.seat = 0;
                pq.add(now);
                continue;
            }
            
            boolean flag = false; // 현재 사용중인 자리 중에 앉을 자리를 찾았는가?

            // 가장 낮은 end, 그 중 가장 낮은 seat을 가진 로그 탐색
            SajiLog using = pq.peek();
            if(using.end < now.start){
                pq.poll();
                flag = true;
                now.seat = using.seat;
                pq.add(now);
                cnt.set(now.seat, cnt.get(now.seat)+1);
            }

            // 현재 있는 자리 중 앉기에 실패했을 경우
            if(!flag){
                //System.out.println("새 자리를 찾자");
                now.seat = cnt.size(); // now의 자리는 현재 이용중인 최고값 자리 + 1
                pq.add(now); // 현재 이용중인 유저에 추가
                cnt.add(1);
            }
            //System.out.println();
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(cnt.size()).append("\n");
        for(int c : cnt){
            sb.append(c).append(" ");
        }
        System.out.println(sb.toString());
    }
}
