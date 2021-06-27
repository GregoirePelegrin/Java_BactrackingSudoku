import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int SIZE_GRID;

    public static ArrayList<Cell> cells;
    public static Path path;

    public static void display(){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<Main.cells.size(); i++){
            Cell currCell = Main.cells.get(i);
            if(currCell.getRealValue() == null) result.append(currCell.getTestValue()).append(" ");
            else result.append(currCell.getRealValue()).append(" ");
            if(currCell.getColumn()+1 != Main.SIZE_GRID && (currCell.getColumn()+1) % Math.sqrt(Main.SIZE_GRID) == 0) result.append("| ");
            else if(currCell.getColumn()+1 == Main.SIZE_GRID && currCell.getRow()+1 != Main.SIZE_GRID &&
                    (currCell.getRow()+1) % Math.sqrt(Main.SIZE_GRID) == 0)
                result.append("\n---------------------");
            if(currCell.getColumn()+1 == Main.SIZE_GRID) result.append("\n");
        }
        System.out.println(result);
    }
    public static void setup() throws Exception{
        Main.cells = new ArrayList<>();

        File file = new File("Sudoku.csv");
        Scanner scanner = new Scanner(file);
        ArrayList<String> data = new ArrayList<>();
        while(scanner.hasNext()) data.add(scanner.nextLine().replace(",", ""));

        Main.SIZE_GRID = data.size();

        int tempSize = (int) Math.sqrt(Main.SIZE_GRID);
        for(int row=0; row<Main.SIZE_GRID; row++){
            for(int column=0; column<Main.SIZE_GRID; column++){
                int mat = (int)((float)row/tempSize)*tempSize + (int)((float)column/tempSize);
                int currValue = (int) (data.get(row).charAt(column)-48);
                if(currValue == 0) Main.cells.add(new Cell(column, mat, row));
                else Main.cells.add(new Cell(column, mat, row, currValue));
            }
        }

        Main.path = new Path(Main.SIZE_GRID);
    }
    public static void solve() throws Exception{
        boolean solved = false;

        while(!solved){
            if(!Main.path.update()){
                solved = true;
                for(Cell cell : Main.cells) {
                    if (cell.getRealValue() == null && cell.getTestValue().equals(0)) {
                        solved = false;
                        Main.path.pop();
                        break;
                    }
                }
                if(Main.path.getSize() == 0) break;
            }
        }

        if(!solved) throw new Exception("Sudoku impossible to finish");
    }

    public static void main(String[] args) {
        try{
            Main.setup();
            Main.solve();
            Main.display();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
