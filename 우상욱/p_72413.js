// 2021 KAKAO BLIND RECRUITMENT 합승 택시 요금
function solution(n, s, a, b, fares) {
    let dist = Array.from(Array(n + 1), (v, k) => new Array(n + 1).fill(Infinity));
    for(let i = 0; i <= n; i++) dist[i][i] = 0;
    
    for(let edge of fares) {
        [A, B, W] = edge;
        
        dist[A][B] = W;
        dist[B][A] = W;
    }
    
    for(let k = 1; k <= n; k++) {
        for(let i = 1; i <= n; i++) {
            for(let j = 1; j <= n; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
    
    let ans = dist[s][a] + dist[s][b];
    for(let i = 1; i <= n; i++) {
        ans = Math.min(ans, dist[s][i] + dist[i][a] + dist[i][b]);
    }

    return ans;
}