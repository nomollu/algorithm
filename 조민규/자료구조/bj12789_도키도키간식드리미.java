package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj12789_도키도키간식드리미 {
    static int N;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Integer> waitLine = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            waitLine.add(Integer.parseInt(st.nextToken()));
        }

        // 구한다.
        go(waitLine, new Stack<Integer>(), 0, 0);

        System.out.println(flag ? "Nice" : "Sad");
    }

    // stack : 임시 대기줄, now : 현재 대기열 제일앞 idx, lastGet : 가장 마지막에 받은 학생 번호
    public static void go(Queue<Integer> waitLine, Stack<Integer> tmpLine, int now, int lastGet){
        // 대기열이 모두 빠졌을 경우
        if(waitLine.isEmpty()){
            // 임시 대기열도 모두 빠졌을 경우
            if(tmpLine.empty()){
                flag = true;
            }
            // 임시 대기열은 아직 남았을 경우
            else {
                if(lastGet+1 == tmpLine.peek()){
                    lastGet = tmpLine.pop();
                    go(waitLine, tmpLine, now, lastGet);
                }
            }
            return;
        }

        // 바로 받아도 되는 경우
        if(!waitLine.isEmpty() && lastGet+1 == waitLine.peek()) {
            lastGet = waitLine.poll();
            now++;
        }
        // 바로 받으면 안되는 경우
        else {
            // 임시 대기열에 있는 학생은 가능할 경우
            if(!tmpLine.empty() && lastGet+1 == tmpLine.peek()){
                lastGet = tmpLine.pop();
            }
            // 다 안되니.. 일단 스택에 넣는다.
            else {
                tmpLine.add(waitLine.poll());
                now++;
            }
        }
        go(waitLine, tmpLine, now, lastGet);
    }
}
