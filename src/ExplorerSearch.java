import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following numbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too

        int[] start = explorerLocator(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);

    }//end reachableArea

    public static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        int r = current[0];
        int c = current[1];

        if (r < 0 || r >= island.length || c < 0 || c >= island[0].length) return 0;
        if (visited[r][c]) return 0;
        if (island[r][c] != 0 && island[r][c] != 1) return 0;

        visited[r][c] = true;
        int count = 1; 

        //counting possibleMoves
        List<int[]> neighbors = possibleMoves(island, current);

        for (int[] neighbor : neighbors) {

            count += reachableArea(island, neighbor, visited);

        }//end for

        return count;

    }//end reachableAreaHelper


    public static int[] explorerLocator(int[][] island) {

        int count = 0;
        int[] start = null;

        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {

                if (island[r][c] == 0) {

                    count++;

                    if (count == 1) {

                        start = new int[]{r, c};

                    }//end if       
                }//end if
            }//end inner for
        }//end outer for

    if (count == 0) throw new IllegalArgumentException("No starting location!");
    if (count > 1) throw new IllegalArgumentException("Multiple starting locations!");

    return start;
        
    }//end explorerLocator


    public static List<int[]> possibleMoves(int[][] island, int[] current) {

        // 1 = yes go through, anything else is not walkable
        
        List<int[]> moves = new ArrayList<>();

        int curR = current[0];
        int curC = current[1];
        
        // check up
        int newR = curR - 1;
        int newC = curC;
        if (newR >= 0 && island[newR][newC] == 1) {
            moves.add(new int[]{newR, newC});
        }

        // check down
        newR = curR + 1;
        newC = curC;
        if (newR < island.length && island[newR][newC] == 1) {
            moves.add(new int[]{newR, newC});
        }

        // check left
        newR = curR;
        newC = curC - 1;
        if (newC >= 0 && island[newR][newC] == 1) {
            moves.add(new int[]{newR, newC});
        }

        // check right
        newR = curR;
        newC = curC + 1;
        if (newC < island[newR].length && island[newR][newC] == 1) {
            moves.add(new int[]{newR, newC});
        }

        return moves;

    }//end possible moves


}//end file
