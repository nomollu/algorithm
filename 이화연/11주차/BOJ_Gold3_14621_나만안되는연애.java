import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold3_14621_나만안되는연애 {
	static int N, W, parent[];
	static String[] univ;
	static Node[] node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학교 수
		W = Integer.parseInt(st.nextToken()); // 도로 수
		univ = new String[N + 1];
		parent = new int[N + 1];
		node = new Node[W];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			univ[i] = st.nextToken();
			parent[i] = i;
		}

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			node[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(node); // 거리 작은순서대로 정렬

		int cnt = 0;
		int sum = 0;
		for (Node n : node) {
			// 학교 성별이 다를때, 사이클 안생길때
			if (!univ[n.from].equals(univ[n.to]) && union(n.from, n.to)) {
				sum += n.d;
				if (++cnt == N - 1) {
					break;
				}
			}
		}
		System.out.println(cnt == N - 1 ? sum : -1);

	}

	static int findParent(int num) {
		if (num == parent[num])
			return num;
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

	static class Node implements Comparable<Node> {
		int from, to, d;

		public Node(int from, int to, int d) {
			super();
			this.from = from;
			this.to = to;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}

}
