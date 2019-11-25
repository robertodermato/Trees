
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

    public String toString(){
        ArrayStack temp = new ArrayStack();
        String stack = "[";

        while (!isEmpty()){
            int a = pop();
            stack = stack + a + ", ";
            temp.push(a);
        }

        while (!temp.isEmpty()){
            push(temp.pop());
        }

        return stack;
    }

    //escreva um algoritmo para o método "Stack reverse (Stack s)".
    // Este algoritmo recebe uma pilha de inteiros (Stack) por parâmetro
    // e retorna uma nova pilha com o conteúdo da pilha recebida por parâmetro invertido.
    // Considere que ao final da execução do método a pilha passada por parâmetro não deve estar alterada.
    public ArrayStack reverse (){
        ArrayStack c = new ArrayStack();
        ArrayStack revertida = new ArrayStack();

        while (!isEmpty()) {
            int a = pop();
            c.push(a);
            revertida.push(a);
        }

        while(!c.isEmpty()){
            push(c.pop());
        }

        return revertida;
    }
}