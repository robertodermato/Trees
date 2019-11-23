public class LinkedQueue {

    private class Node {
        public Integer element;
        public Node next;
        public Node(Integer e) {
            element = e;
            next = null;
        }
    }

    // Atributos da classe Fila (LinkedQueue)
    private int count;
    private Node head;
    private Node tail;

    public LinkedQueue() {
        count = 0;
        head = null;
        tail = null;
    }

    public void enqueue(Integer element) {
        Node n = new Node(element);
        if (count==0) {
            head = n;
        }
        else {
            tail.next = n;
        }
        tail = n;
        count++;
    }

    public Integer dequeue() {
        if (count == 0)
            throw new RuntimeException("Fila vazia!"); // Erro
        Integer aux = head.element;
        head = head.next;
        count--;
        return aux;
    }

    public Integer head() {
        if (count == 0)
            throw new RuntimeException("Fila vazia!"); // Erro
        return head.element;
    }

    public boolean isEmpty() {
        return (count==0);
    }

    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public String toString() {
        String fila = "";

        for (int i=0; i<count; i++){
            int a = dequeue();
            fila = fila + a + ", ";
            enqueue(a);
        }

        return fila;
    }

    //Implemente um método chamado furaFila que recebe uma fila e um elemento por
    //parâmetro e coloca o elemento no início da Fila.
    public LinkedQueue furaFila(LinkedQueue fila, Integer e){
        LinkedQueue novaFila = new LinkedQueue();
        novaFila.enqueue(e);
        int tamanho = fila.size();

        for (int i=0; i<tamanho;i++){
            novaFila.enqueue(fila.dequeue());
        }

        return novaFila;

    }

}