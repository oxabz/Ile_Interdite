package Project.util;

public class Vector2 {
    public int x,y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 v){
        return new Vector2(this.x+v.x,this.y+v.y);
    }

    public Vector2 add(int x, int y){
        return new Vector2(this.x+x,this.y+y);
    }
}
