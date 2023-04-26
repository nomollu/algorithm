import java.util.*;

class Solution
{
    static int row, col;
    static int[][] map;
    
    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    
    static boolean check(Point p, int depth){
        if(row <= p.i+depth || col <= p.j+depth ||
           map[p.i+depth][p.j+depth]==0) return false;
        
        for(int d=0; d<depth; d++){
            if(map[p.i+d][p.j]==0 || map[p.i][p.j+d]==0) return false;
        }
        
        return true;
    }
    
    public int solution(int [][]board)
    {
        map = board;
        row=board.length;
        col=board[0].length;
        Queue<Point> queue = new LinkedList<Point>();
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++) {
                if(board[i][j] == 1) queue.add(new Point(i, j));
            }
        }
        
        int depth = 0;        
        while(!queue.isEmpty()){
            depth++;
            int size = queue.size();
            for(int s=0; s<size; s++){
                Point p = queue.poll();
                if(check(p, depth)) queue.add(p);
            }
        }       
        
        return depth*depth;
    }
}
