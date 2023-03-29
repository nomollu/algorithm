package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class bj1283_단축키지정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<Character> registered = new HashSet<>();

        // i : N개의 입력되는 입력 문자열
        pointA : for(int now = 0 ; now < N ; now++){
            // 단어 단위로 분리한다.
            String[] inputArr = br.readLine().split(" ");

            // 1. 단어의 첫 글자가 단축키로 지정되었는지 확인한다.
            for(int i = 0 ; i < inputArr.length ; i++){
                // 지정되지 않은 것이 있을 경우
                if(!registered.contains(inputArr[i].charAt(0))){
                    // set에 등록
                    registered.add(Character.toUpperCase(inputArr[i].charAt(0)));
                    registered.add(Character.toLowerCase(inputArr[i].charAt(0)));

                    // 현재 단어의 첫 번째 글자에 괄호를 씌워준다.
                    StringBuilder sb = new StringBuilder(inputArr[i]);
                    sb.insert(0, '[');
                    sb.insert(2, ']');
                    inputArr[i] = sb.toString();

                    // 출력 후 다음 입력으로 ㄱㄱ
                    printAnswer(inputArr);
                    continue pointA;
                }
            }

            // 2. 모든 단어의 첫 글자가 이미 지정되어 있다면, 왼쪽에서부터 차례대로 본다.
            for(int i = 0 ; i < inputArr.length ; i++){
                for(int j = 0 ; j < inputArr[i].length() ; j++){
                    if(!registered.contains(inputArr[i].charAt(j))){
                        // set에 등록
                        registered.add(Character.toUpperCase(inputArr[i].charAt(j)));
                        registered.add(Character.toLowerCase(inputArr[i].charAt(j)));

                        // 현재 글자에 괄호를 씌워준다.
                        StringBuilder sb = new StringBuilder(inputArr[i]);
                        sb.insert(j, '[');
                        sb.insert(j+2, ']');
                        inputArr[i] = sb.toString();

                        // 출력 후 다음 입력으로 ㄱㄱ
                        printAnswer(inputArr);
                        continue pointA;
                    }
                }
            }

            // 결국 아무것도 없었을 경우...
            printAnswer(inputArr);
        }
    }

    public static void printAnswer(String[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < arr.length ; i++){
            if(i == arr.length - 1) sb.append(arr[i]);
            else sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
