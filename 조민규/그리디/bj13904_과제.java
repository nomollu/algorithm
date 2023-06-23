package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj13904_과제 {
    static class Homework {
        int day, score;

        public Homework(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int N = Integer.parseInt(br.readLine());
        List<Homework> hwList = new ArrayList<>();
        int maxDay = 0;
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, day);
            hwList.add(new Homework(day, score));
        }

        int sum = 0;
        for(int d = maxDay ; d >= 1 ; d--){
            Homework todo = new Homework(0,0);
            for(Homework hw : hwList){
                if(todo.score < hw.score && hw.day >= d){
                    todo = hw;
                }
            }

            sum += todo.score;
            hwList.remove(todo);
        }
        System.out.println(sum);
    }
}
