package ioc;

public class Holder4String {
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Holder4String() { this.name = "None"; }
    public Holder4String(String name) { this.name = name; }
    @Override
    public String toString() { return String.format("StringHolder{name='%s'}", this.name); }
}
