function solution(maps) {
  var answer = Number.MAX_SAFE_INTEGER;
  const R = maps.length;
  const C = maps[0].length;
  let r = 0;
  let c = 0;
  const d = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1],
  ];
  let visit = Array(maps.length)
    .fill(Number.MAX_SAFE_INTEGER)
    .map(() =>
      Array(maps[0].length)
        .fill(Number.MAX_SAFE_INTEGER)
        .map(() => Array(2))
    );
  let q = [];

  for (let i = 0; i < maps.length; i++) {
    for (let j = 0; j < maps[i].length; j++) {
      if (maps[i][j] === "S") {
        r = i;
        c = j;
      }
    }
  }

  visit[r][c][0] = 0;
  q.push([r, c, 0, 0]); // r, c, 스텝 수, 레버 여부

  while (q.length !== 0) {
    const p = q[0];
    q.splice(0, 1);

    if (maps[p[0]][p[1]] === "L") {
      visit[p[0]][p[1]][0] = p[2];
      visit[p[0]][p[1]][1] = p[2];
      p[3] = 1;
    } else if (maps[p[0]][p[1]] === "E" && p[3] === 1) {
      answer = Math.min(answer, p[2]);
      continue;
    }

    for (let dir of d) {
      const nr = p[0] + dir[0];
      const nc = p[1] + dir[1];

      if (
        nr < 0 ||
        nc < 0 ||
        nr >= R ||
        nc >= C ||
        visit[nr][nc][p[3]] <= visit[p[0]][p[1]][p[3]] + 1 ||
        maps[nr][nc] === "X"
      )
        continue;

      visit[nr][nc][p[3]] = visit[p[0]][p[1]][p[3]] + 1;
      q.push([nr, nc, visit[p[0]][p[1]][p[3]] + 1, p[3]]);
    }
  }

  if (answer === Number.MAX_SAFE_INTEGER) answer = -1;

  return answer;
}

console.log(solution(["SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"]));
