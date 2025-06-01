import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
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







}//end file
