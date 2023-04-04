package klm;

public class Assignment {

    public static void main (String[] args) {
        char[][] airport = {
                {'.', '.', 'p', '.', '.', '.', '.'},
                {'p', '.', '.', '.', 'p', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', 'p', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.'},
                {'p', '.', '.', '.', '.', '.', 'p'}
        };

        int[] trucksPerRow = {3, 1, 1, 1, 0, 2, 1};
        int[] trucksPerCol = {2, 1, 1, 1, 1, 1, 2};

        int[][] diagonalDirections = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int[][] straightDirections = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        int[][] planes = {{0,2}, {1,0}, {1, 4}, {1, 6}, {2, 6}, {4,1}, {5, 3}, {6,0}, {6,6}};

        boolean solve = solvePuzzle(airport, planes, straightDirections, diagonalDirections, trucksPerRow, trucksPerCol, 0);

        System.out.println(solve);

        for (int i = 0; i < airport.length; i++) {
            System.out.print("{");
            for (int j = 0; j < airport[0].length; j++) {
                System.out.print(airport[i][j] + " ");
            }
            System.out.println("}");
        }
    }

    public static boolean solvePuzzle(char[][] airport, int[][] planes, int[][] straightDirections, int[][] diagonalDirections, int[] trucksPerRow, int[] trucksPerColumn, int planeIndex) {
        if (planeIndex == planes.length) {
            //we places all the trucks, and solved the puzzle
            return true;
        }
        //get the current plane
        int[] airplane = planes[planeIndex];
        int airplanePositionRow = airplane[0];
        int airplanePositionCol = airplane[1];

        // check if we can place a truck in any direct direction of the plane
        for (int i = 0; i < straightDirections.length; i++) {
            int possibleTruckPositionRow = airplanePositionRow + straightDirections[i][0];
            int possibleTruckPositionCol = airplanePositionCol + straightDirections[i][1];
            //check first if the position is valid (inside the airport)
            if (isLegalPosition(possibleTruckPositionRow, possibleTruckPositionCol, airport)) {
                //check if the truck can placed in that position
                if (isTruckPositionPossible(possibleTruckPositionRow, possibleTruckPositionCol, airport, diagonalDirections, trucksPerRow, trucksPerColumn)) {
                    //update the truck position and decrement the nr of trucks per row and column at that position
                    airport[possibleTruckPositionRow][possibleTruckPositionCol] = 't';
                    trucksPerRow[possibleTruckPositionRow] -= 1;
                    trucksPerColumn[possibleTruckPositionCol] -= 1;
                    //do the same for the next plane
                    if (solvePuzzle(airport, planes, straightDirections, diagonalDirections, trucksPerRow, trucksPerColumn, planeIndex + 1)) {
                        return true;
                    } else {
                        //dead end. the truck cannot be place. remove the truck and relax the truck numbers per row and column
                        airport[possibleTruckPositionRow][possibleTruckPositionCol] = '.';
                        trucksPerRow[possibleTruckPositionRow] += 1;
                        trucksPerColumn[possibleTruckPositionCol] += 1;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isTruckPositionPossible(int row, int col, char[][] board, int[][] diagonalDirections, int[] trucksPerRow, int[] trucksPerCol) {
        if (!isLegalPosition(row, col, board)) {
            return false;
        }
        if (board[row][col] != '.') {
            return false;
        }
        if (trucksPerRow[row] < 1 || trucksPerCol[col] < 1) {
            return false;
        }
        for (int d = 0; d < diagonalDirections.length; d++) {
            int[] dir = diagonalDirections[d];
            int diagonalRow = row + dir[0];
            int diagonalCol = col + dir[1];
            if (isLegalPosition(diagonalRow, diagonalCol, board)) {
                if (board[diagonalRow][diagonalCol] == 't') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isLegalPosition(int row, int col, char[][]board ) {
        if (row < 0 || col < 0) {
            return false;
        }
        if (row >= board.length || col >= board[0].length) {
            return false;
        }
        return true;
    }
}
