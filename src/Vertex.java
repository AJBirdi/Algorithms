
public class Vertex {
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null){
            return false;
        }
        Vertex obj = (Vertex) object;
        if(this.id == obj.id) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}