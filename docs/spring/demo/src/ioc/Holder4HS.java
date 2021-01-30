package ioc;

public class Holder4HS {
    protected Holder4String holderString;
    public Holder4String getHolderString() { return holderString; }
    public void setHolderString(Holder4String holderString) { this.holderString = holderString; }
    @Override
    public String toString() { return String.format("Holder4HS{holderString=%s}", holderString); }
}
