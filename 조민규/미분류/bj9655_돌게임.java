package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj9655_돌게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stones = Integer.parseInt(br.readLine());
        System.out.println(stones % 2 == 1 ? "SK" : "CY");
    }
}
