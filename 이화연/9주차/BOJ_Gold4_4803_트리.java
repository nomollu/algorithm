import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_Gold4_4803_트리 {
	static int n, m, parent[];
	static boolean noTree[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		int index = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 정점 개수
			m = Integer.parseInt(st.nextToken()); // 간선 개수
			if (n == 0 && m == 0) {
				break;
			}

			parent = new int[n + 1];
			noTree = new boolean[n + 1];
			setParent();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (!union(a, b)) { // 사이클 생긴 경우
					noTree[parent[a]] = true;
				}
			}

			// 자신의 부모가 최상위 루트로 갱신되지 않은 정점들이 있을 수 있으니 다시 한번 부모 찾기
			for (int i = 1; i <= n; i++) {
				findParent(i);
				// 이미 사이클이 생겼던 부모의 최상위 루트가 바꼈을경우 그 최상위 루트도 사이클 처리해줘야 함
				if (noTree[i]) {
					noTree[parent[i]] = true;
				}
			}

			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				if (!noTree[parent[i]]) { // 사이클 안생긴 루트정점만 담기
					set.add(parent[i]);
				}
			}

			if (set.size() == 0) {
				System.out.println("Case " + index + ": No trees.");
			} else if (set.size() == 1) {
				System.out.println("Case " + index + ": There is one tree.");
			} else {
				System.out.println("Case " + index + ": A forest of " + set.size() + " trees.");
			}
			index++;
		}

	}

	static void setParent() { // 자기 자신으로 부모 세팅
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int findParent(int num) {
		if (num == parent[num]) {
			return num;
		}
		return parent[num] = findParent(parent[num]);
	}

	static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if (aRoot == bRoot) {
			return false;
		}

		parent[bRoot] = aRoot;
		return true;
	}

}
