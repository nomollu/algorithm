package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * G3 두 배열의 합
 */
public class p_2143 {

    static int T, N, M;
    static int[] A, B;
    static ArrayList<Integer> sum_A, sum_B;
    static int upper, lower;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            B[i] = Integer.parseInt(st.nextToken());

        // calculate sum
        sum_A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int tmp_sum = A[i];
            sum_A.add(tmp_sum);

            for (int j = i + 1; j < N; j++) {
                tmp_sum += A[j];
                sum_A.add(tmp_sum);
            }
        }

        sum_B = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int tmp_sum = B[i];
            sum_B.add(tmp_sum);

            for (int j = i + 1; j < M; j++) {
                tmp_sum += B[j];
                sum_B.add(tmp_sum);
            }
        }

        Collections.sort(sum_B);

        long ans = 0;
        for (int i = 0; i < sum_A.size(); i++) {
            int target = T - sum_A.get(i);

            lower = upper = -1;
            lower_bound(0, sum_B.size() - 1, target);
            upper_bound(0, sum_B.size() - 1, target);

            if (lower != -1)
                ans += (upper - lower + 1);
        }

        System.out.println(ans);

    }

    static void upper_bound(int left, int right, int target) {
        if (left > right)
            return;

        int mid = (left + right) / 2;
        int sum_B_val = sum_B.get(mid);

        if (sum_B_val == target)
            upper = mid;

        if (sum_B_val > target)
            upper_bound(left, mid - 1, target);
        else if (sum_B_val <= target)
            upper_bound(mid + 1, right, target);
    }

    static void lower_bound(int left, int right, int target) {
        if (left > right)
            return;

        int mid = (left + right) / 2;
        int sum_B_val = sum_B.get(mid);

        if (sum_B_val == target)
            lower = mid;

        if (sum_B_val >= target)
            lower_bound(left, mid - 1, target);
        else if (sum_B_val < target)
            lower_bound(mid + 1, right, target);
    }
}
