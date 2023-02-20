// union-find
function solution(n, computers) {
    let parents = new Array(n);
    for(let i=0; i<n; i++){ //부모 자기 자신으로 세팅
        parents[i] = i;
    }
    
    const findParent = (num) => { // 부모찾기
        if(parents[num] === num){
            return num;
        }
        return parents[num] = findParent(parents[num]);
    }
    
    const union = (a,b) => { // 하나로 합치기
        let aRoot = findParent(a);
        let bRoot = findParent(b);
        if(aRoot !== bRoot){
            parents[bRoot] = aRoot;
        }
    }
    
    for(let i=0; i<n; i++){
        for(let j=0; j<n; j++){
            if(i === j) continue; //자기자신은 제외
            if(computers[i][j] === 1){ // 연결되어 있는 경우만 탐색
                union(i,j);
            }
        }
    }
    
    let set = new Set(); //중복제거하기 위해서
    for(const p of parents){
				// 위에서 for문을 돌며 부모를 찾았지만 최상단 부모까지 갱신되지 않은 경우를 제거해주기 위해 한번 더 부모 찾기
        const realP = findParent(p); 
        set.add(realP);
    }
    
    return set.size;
}

// dfs
function solution(n, computers) {
    let answer = 0;
    let visited = new Array(n).fill(false);
    
    const dfs = (i) => {
        visited[i] = true;
        
        for(let j=0; j<n; j++){
						// 자기 자신이 아니고 방문하지 않았고 연결되어 있을때
            if(i != j && !visited[j] && computers[i][j] === 1){
                dfs(j);
            }
        }
    }
    
    for(let i=0; i<n; i++){
        if(!visited[i]){
            dfs(i);
            answer++;
        }
    }
 
    return answer;
}
