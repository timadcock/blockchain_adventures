public class Chain {

    private final Link head;
    private       Link tail;

    public Chain(Link head) {
        this.head = head;
        this.tail = this.head;
    }

    public void insert(Block newLink) {
        this.tail.setNext(new Link(newLink, tail));
        this.tail = this.tail.getNext();
    }

    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();

        Link current = this.head;
        int  order   = 0;
        while (current != null) {
            sb.append(order);
            sb.append(": ");
            order++;

            sb.append(current);
            current = current.getNext();
        }
        return sb.toString();
    }
}
