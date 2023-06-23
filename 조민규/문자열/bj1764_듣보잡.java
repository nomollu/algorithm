package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1764_듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            set.add(input);
        }

        for(int i = 0 ; i < M ; i++){
            String input = br.readLine();
            if(set.contains(input)){
                ans.add(input);
            }
        }

        Collections.sort(ans);

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for(String s : ans){
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }
}
