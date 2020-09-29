package ioc;

public class Holder4Object {
    protected Object object;
    public Object getObject() { return object; }
    public void setObject(Object object) { this.object = object; }
    public Holder4Object() { this.object = null; }
    public Holder4Object(Object object) { this.object = object; }
    @Override
    public String toString() { return String.format("ObjectHolder{object=%s}", this.object); }

}
