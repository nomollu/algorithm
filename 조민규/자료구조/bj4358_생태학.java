package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class bj4358_생태학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Integer> map = new TreeMap<>(); // 자동 정렬되는 map
        int cnt = 0; // 전체 입력 수

        // 입력
        while(true){
            String input = br.readLine();
            if(input == null || input.length() == 0) break;

            if(map.containsKey(input)){ // 이미 한 번 이상 나온 적 있는 종일 경우
                map.put(input, map.get(input) + 1);
            } else { // 처음 등장하는 종일 경우
                map.put(input, 1);
            }
            cnt++;
        }

        // 출력
        for(String key : map.keySet()){
            double value = (double)map.get(key) / (double)cnt * 100;
            System.out.println(key + " " + String.format("%.4f", value));
        }
    }
}
