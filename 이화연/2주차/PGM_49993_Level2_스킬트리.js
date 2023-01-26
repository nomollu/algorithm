function solution(skill, skill_trees) {
    let answer = 0;
    for(let i=0; i<skill_trees.length; i++){
        const str = skill_trees[i].split("").filter((item) => skill.includes(item));
        let flag = true;
        str.forEach((item, idx) => {
            if(item !== skill.substring(idx, idx+1)){
                flag = false;
            }
        });
        if(flag){
            answer++;
        }
    }
    return answer;
}
