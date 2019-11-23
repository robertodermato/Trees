public class AppDeque {

    public static void main(String[] args) {

        Deque deck = new Deque();

        deck.addFirst(2);
        deck.addFirst(1);
        deck.addLast(3);
        imprimeDeque(deck);

        deck.removeFirst();
        imprimeDeque(deck);

        deck.removeFirst();
        imprimeDeque(deck);

        deck.removeFirst();
        imprimeDeque(deck);
    }

    public static void imprimeDeque(Deque deck) {
        System.out.print("Deque: topo do deque é ");
        System.out.println(deck.getFirst());
    }


}