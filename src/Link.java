public class Link {
    private Link previous;
    private Link next;

    private Block data;

    public Link(Block data, Link previous) {
        this.previous = previous;
        this.data     = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
