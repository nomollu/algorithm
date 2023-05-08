// G5 홀수 홀릭 호석
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p_20164 {

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        System.out.println(hoseok(num, true) + " " + hoseok(num, false));
    }

    static int hoseok(String num, boolean isMin) {
        int odds = countOdds(num);

        if(num.length() == 1)
            return odds;
        else if(num.length() == 2) {
            int new_num = (num.charAt(0) - '0') + (num.charAt(1) - '0');
            return odds + hoseok(String.valueOf(new_num), isMin);
        } else {
            int tmp = isMin? Integer.MAX_VALUE : -1;

            for(int i = 1; i < num.length() - 1; i++) {
                for(int j = i + 1; j < num.length(); j++) {
                    String n1 = num.substring(0, i);
                    String n2 = num.substring(i, j);
                    String n3 = num.substring(j);
                    
                    int new_num = Integer.parseInt(n1) + Integer.parseInt(n2) + Integer.parseInt(n3);
                    int res = hoseok(String.valueOf(new_num), isMin);

                    if(isMin) 
                        tmp = Math.min(tmp, res);
                    else
                        tmp = Math.max(tmp, res);
                }
            }

            return odds + tmp;
        }
    }

    static int countOdds(String num) {
        int ret = 0;
        for(int i = 0; i < num.length(); i++)
            if((int) (num.charAt(i) - '0') % 2 == 1) ret++;

        return ret;
    }
}
