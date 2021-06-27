import java.util.ArrayList;

public class Cell {
    private int column;
    private int matrix;
    private int row;
    private ArrayList<Integer> toTest;
    private Integer realValue;
    private Integer testValue;

    public Cell(int _column, int _matrix, int _row){
        this.column = _column;
        this.matrix = _matrix;
        this.row = _row;

        this.toTest = new ArrayList<>();
        this.realValue = null;
        this.testValue = 0;
    }
    public Cell(int _column, int _matrix, int _row, int _value){
        this.column = _column;
        this.matrix = _matrix;
        this.row = _row;
        this.realValue = _value;

        this.toTest = new ArrayList<>();
        this.testValue = 0;
    }

    public boolean nextStep(){
        if(this.toTest.size() == 0) return false;
        this.testValue = this.toTest.remove(0);
        return true;
    }
    public boolean populate(){
        if(this.realValue != null){
            this.toTest.add(this.realValue);
            return true;
        }
        for(int i=1; i<=Main.SIZE_GRID; i++) this.toTest.add(i);
        for(Cell cell : Main.cells){
            if(cell.getColumn() == this.column || cell.getMatrix() == this.matrix || cell.getRow() == this.row){
                this.toTest.remove(cell.getRealValue());
                this.toTest.remove(cell.getTestValue());
            }
        }
        return this.toTest.size() != 0;
    }
    public void reinitialize(){
        if(this.realValue == null){
            this.toTest = new ArrayList<>();
            this.testValue = 0;
        }
    }

    public int getColumn() {
        return this.column;
    }
    public int getMatrix() {
        return this.matrix;
    }
    public int getRow() {
        return this.row;
    }
    public Integer getRealValue() {
        return this.realValue;
    }
    public Integer getTestValue() {
        return this.testValue;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Cell(" + this.row + "," +
                this.column + "," + this.realValue + "," + this.testValue + ",[");
        for(Integer poss : this.toTest) result.append(poss).append(",");
        result.append("])");
        return result.toString();
    }
}
