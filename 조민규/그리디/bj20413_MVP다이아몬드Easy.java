package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj20413_MVP다이아몬드Easy {
    static int[] gMoney = new int[4]; // 등급 기준액
    static char[] gName = {'B', 'S', 'G', 'P', 'D'}; // 등급 이름

    public static int gradeToMoney(int grade, int prevMoney){
        return grade == 4 ? gMoney[grade-1] : gMoney[grade] - 1 - prevMoney;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 게임을 플레이한 개월 수
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 4 ; i++){
            gMoney[i] = Integer.parseInt(st.nextToken());
        }
        String str = br.readLine();

        int money = 0; // 상민이의 과금 금액
        int nowGrade = 0; // 상민이의 현재 등급
        int prevMoney = 0; // 이전 돈
        int curMoney = 0; // 이번달 쓴 돈
        for(int i = 0 ; i < N ; i++){

            switch (str.charAt(i)){
                case 'B':
                    nowGrade = 0;
                    break;
                case 'S':
                    nowGrade = 1;
                    break;
                case 'G':
                    nowGrade = 2;
                    break;
                case 'P':
                    nowGrade = 3;
                    break;
                case 'D':
                    nowGrade = 4;
                    break;
                default:
                    nowGrade = -1;
            }

            curMoney = gradeToMoney(nowGrade, prevMoney);
            money += curMoney;
            prevMoney = curMoney;
        }
        System.out.println(money);
    }
}
