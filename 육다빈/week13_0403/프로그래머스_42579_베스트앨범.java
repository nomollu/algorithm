import java.util.*;

class Solution {
    static class Song implements Comparable<Song>{
        int idx, genreCnt, songCnt;
        Song(int idx, int genreCnt, int songCnt){
            this.idx = idx;
            this.genreCnt = genreCnt;
            this.songCnt = songCnt;
        }
        @Override
        public int compareTo(Song o){ // 장르 재생 수 > 곡 재생 수 > 곡 인덱스 순서로 정렬
            if(this.genreCnt == o.genreCnt){
                if(this.songCnt == o.songCnt) return this.idx - o.idx;
                else return o.songCnt - this.songCnt;
            }else return o.genreCnt - this.genreCnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int size = plays.length;

        // 1. 장르 재생 수 누적
        Map<String, Integer> genreList = new HashMap<String, Integer>();
        for(int i=0; i<size; i++){
            if(!genreList.containsKey(genres[i])) genreList.put(genres[i], plays[i]);
            else genreList.replace(genres[i], genreList.get(genres[i])+plays[i]);
        }

        // 2. 우선순위대로 곡 정렬
        PriorityQueue<Song> queue = new PriorityQueue<Song>();
        for(int i=0; i<size; i++){
            queue.add(new Song(i, genreList.get(genres[i]), plays[i]));
        }
        
        // 3. 곡 수록하기
        List<Integer> answer = new ArrayList<Integer>();
        int genreCnt=0, songCnt=0, genreSize = genreList.size();
        
        while(!queue.isEmpty() || genreCnt<genreSize){
            Song now = queue.poll();
            answer.add(now.idx);
            
            if(++songCnt==2 || queue.isEmpty() || queue.peek().genreCnt!=now.genreCnt){
                ++genreCnt;
                songCnt = 0;
                
                while(!queue.isEmpty()){ // 해당 장르에 남은 곡들 버리기
                    if(queue.peek().genreCnt==now.genreCnt) queue.poll();
                    else break;
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
