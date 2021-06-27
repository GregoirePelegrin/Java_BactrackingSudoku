import java.util.ArrayList;

public class Path {
    private ArrayList<Cell> path;
    private int size;

    public Path(int _size) throws Exception {
        this.size = _size;

        this.path = new ArrayList<>();

        Cell initialCell = Main.cells.get(0);
        if(!initialCell.populate() || !initialCell.nextStep()) throw new Exception("The first cell can't be initialized");
        this.path.add(initialCell);
    }

    public Cell get(int index){
        return this.path.get(index);
    }
    public int getSize(){
        return this.path.size();
    }
    public void pop(){
        this.path.remove(this.path.size()-1).reinitialize();
        if(!this.path.get(this.path.size()-1).nextStep()) this.pop();
    }
    public boolean update(){
        Cell cell = this.path.get(this.path.size()-1);
        if(cell.getColumn() == Main.SIZE_GRID-1 && cell.getRow() == Main.SIZE_GRID-1){
            return cell.nextStep();
        } else {
            Cell nextCell = Main.cells.get(cell.getRow()*Main.SIZE_GRID + cell.getColumn() + 1);
            if(!nextCell.populate()) return cell.nextStep();
            nextCell.nextStep();
            this.path.add(nextCell);
            return true;
        }
    }
}
