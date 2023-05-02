package 플로이드_와샬;

import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static class Node{
        int num, weightSum;

        public Node(int num, int weightSum){
            this.num = num;
            this.weightSum = weightSum;
        }
    }

    static int[][] adjMatrix;

    // n : 지점의 개수, s : 출발 지점
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 입력
        adjMatrix = new int[n][n];
        for(int i = 0 ; i < fares.length ; i++){
            int from = fares[i][0] - 1;
            int to = fares[i][1] - 1;
            int weight = fares[i][2];
            adjMatrix[from][to] = weight;
            adjMatrix[to][from] = weight;
        }

        // 플로이드 와샬
        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(s, 0)); // 출발 지점 큐에 추가
        boolean[] visited = new boolean[n];
        visited[s] = true;

        int ans = 99999;
        boolean[] flag = new boolean[2];
        while(!queue.isEmpty()){
            Node now = queue.poll();

            for(int i = 0 ; i < n ; i++){
                int newWeightSum = now.weightSum + adjMatrix[now.num][i];
                if(i != now.num && !visited[i]){
                    // 큐에 추가
                    queue.add(new Node(i, newWeightSum));
                    visited[i] = true;
                }

                // a 또는 b에 도착했을 경우
                if(i == a || i == b){
                    flag[i == a ? 0 : 1] = true; // 도착 처리
                }

                // 모두 도착했을 경우
                if(flag[0] && flag[1]){
                    ans = Math.min(ans, newWeightSum);
                }
            }
        }

        return ans;
    }
}
