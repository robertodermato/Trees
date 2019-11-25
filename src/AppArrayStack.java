public class AppArrayStack {

    public static void main(String[] args) {

        ArrayStack pilha1 = new ArrayStack();

        pilha1.push(1);
        pilha1.push(2);
        pilha1.push(3);
        pilha1.push(4);

        System.out.println("Pilha1" + pilha1);

        ArrayStack pilha2 = pilha1.reverse();

        System.out.println("Pilha1" + pilha1);
        System.out.println("Pilha2" + pilha2);

    }

}
