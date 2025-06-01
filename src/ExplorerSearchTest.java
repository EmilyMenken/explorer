import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {

//tests for explorerLocator
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    @Test
    public void testReachableAreaOopsAllMountains() {
        int[][] island = {
            {2, 2, 2},
            {2, 2, 2},
            {2, 2, 2}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.reachableArea(island);
        });
        assertEquals("No starting location!", exception.getMessage());
    }

    @Test
    public void testReachableAreaStartOnBottom() {
        int[][] island = {
            {3, 1, 1},
            {2, 2, 1},
            {1, 0, 1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(6, actual);
    }

   @Test
    public void testReachableAreaMultipleStartingLocations() {
        int[][] island = {
            {2, 2, 2},
            {2, 0, 2},
            {0, 2, 2}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.reachableArea(island);
        });
        assertEquals("Multiple starting locations!", exception.getMessage());
    }

    @Test
    public void testReachableAreaOnlyDiagonalOpen() {
    int[][] island = {
        {0, 2},
        {2, 1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual); 
    }


//tests for explorerLocator
    @Test
    public void testExplorerLocatorNoStartingLocation() {
        int[][] island = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorerLocator(island);
        });
        assertEquals("No starting location!", exception.getMessage());
    }

    @Test
    public void testExplorerLocatorCenter() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] expected = {1, 1};
        assertArrayEquals(expected, ExplorerSearch.explorerLocator(island));
    }

    @Test
    public void testExplorerLocatorTopLeft() {
        int[][] island = {
            {0, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int[] expected = {0, 0};
        assertArrayEquals(expected, ExplorerSearch.explorerLocator(island));
    }

    @Test
    public void testExplorerLocatorMiddleRight() {
        int[][] island = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 1, 1}
        };
        int[] expected = {1, 2};
        assertArrayEquals(expected, ExplorerSearch.explorerLocator(island));
    }

    
//tests for possibleMoves
    @Test
    public void testPossibleMovesAllOkDirections() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }

    @Test
    public void testPossibleMovesNoOkDirections() {
        int[][] island = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }

    @Test
    public void testPossibleMovesOnlyRight() {
        int[][] island = {
            {0, 1, 1}
        };
        int[] location = {0, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,2"));
    }

    @Test
    public void testPossibleMovesSomeBlocked() {
        int[][] island = {
            {1, 1, 1},
            {0, 0, 0},
            {1, 1, 1}
        };
        int[] location = {2, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.contains("2,0")); // left
        assertTrue(moveSet.contains("2,2")); // right
        assertFalse(moveSet.contains("1,1")); // up blocked
    }

    @Test
    public void testPossibleMovesNoPossibleMoves() {
        int[][] island = {
            {0}
        };
        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }






    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}//end file
