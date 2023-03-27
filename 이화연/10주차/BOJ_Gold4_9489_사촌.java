import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold4_9489_사촌 {
	static int n, k, arr[], idx, searchIdx;
	static Node[] node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 노드 개수
			k = Integer.parseInt(st.nextToken()); // 사촌의 수 구해야할 노드
			arr = new int[n];
			node = new Node[n];
			idx = 0; // 부모가 누군지 기록할 인덱스
			searchIdx = 0; // 사촌의 수 구해야할 노드의 인덱스

			if (n == 0 && k == 0)
				break;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (arr[i] == k) {
					searchIdx = i;
				}
			}

			node[0] = new Node(arr[idx], 1, idx); // 루트 노드는 자기 자신이 부모이며, depth는 1
			for (int i = 1; i < n; i++) {
				node[i] = new Node(arr[idx], node[idx].d + 1, idx);
				if (i != n - 1 && arr[i] + 1 != arr[i + 1]) {
					idx++;
				}
			}
			int ans = 0;
			for (Node n : node) {
				// 사촌의 수 구해야할 노드의 부모가 다르고, depth가 같은 경우가 사촌임
				// 부모의 부모도 같은지 확인해줘야 사촌인지 판별가능
				if (node[searchIdx].d == n.d && node[searchIdx].p != n.p
						&& node[node[searchIdx].idx].p == node[n.idx].p) {
					ans++;
				}
			}
			System.out.println(ans);
		}
	}

	static class Node {
		int p, d, idx;

		public Node(int p, int d, int idx) {
			super();
			this.p = p;
			this.d = d;
			this.idx = idx;
		}
	}
}
