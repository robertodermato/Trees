
import java.util.EmptyStackException;

public class ArrayStack {

    private int topo;
    private Integer p[];

    public ArrayStack() {
        p = new Integer[100];
        topo = 0;
    }

    public int size() {
        return topo;
    }

    public void push(Integer e) {
        if (topo >= p.length) {
            throw new StackOverflowError();
        }
        p[topo] = e;
        topo++;
    }

    public Integer pop() {
        if (topo == 0) {
            throw new EmptyStackException();
        }
        topo--;
        Integer aux = p[topo];
        p[topo] = null;
        return aux;
    }

    public Integer top() {
        if (topo == 0) {
            throw new EmptyStackException();
        }
        return p[topo - 1];
    }

    public boolean isEmpty() {
        return (topo == 0);
    }

    public void clear() {
        p = new Integer[100];
        topo = 0;
    }

}