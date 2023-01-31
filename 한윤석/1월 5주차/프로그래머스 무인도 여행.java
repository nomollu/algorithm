static int R, C;
static char map [][];
static boolean check [][];
static int d[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
static ArrayList<Integer> islands = new ArrayList<>();

public int[] solution(String[] maps) {
  R = maps.length;
  C = maps[0].length();
  map = new char [R][C];
  check = new boolean [R][C];

  for(int i=0; i<R; i++) for(int j=0; j<C; j++) map[i][j] = maps[i].charAt(j);

  for(int i=0; i<R; i++) {
    for(int j=0; j<C; j++) {
      if(!check[i][j] && map[i][j] != 'X') bfs(i, j);
    }
  }

  Collections.sort(islands);
  int answer [];
  if(islands.size() > 0) {
    answer = new int [islands.size()];
    int cnt = 0;
    for(int i : islands) answer[cnt++] = i; 
  }else answer = new int [] {-1};

  return answer;
}

static void bfs(int sr, int sc) {
  Queue<int []> q = new LinkedList<>();
  q.add(new int [] {sr, sc});
  check[sr][sc] = true;
  int sum = map[sr][sc] - '0';

  while(!q.isEmpty()) {
    int pos [] = q.poll();

    for(int i=0; i<4; i++) {
      int nr = pos[0] + d[i][0];
      int nc = pos[1] + d[i][1];

      if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'X' || check[nr][nc]) continue;

      check[nr][nc] = true;
      q.add(new int [] {nr, nc});
      sum += map[nr][nc] - '0';
    }
  }

  islands.add(sum);
}
