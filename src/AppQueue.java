public class AppQueue {

    public static void main(String[] args) {

        LinkedQueue q1 = new LinkedQueue();
        LinkedQueue q2 = new LinkedQueue();

        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);

        q2.enqueue(4);
        q2.enqueue(5);
        q2.enqueue(6);
        q2.enqueue(7);
        q2.enqueue(8);

        LinkedQueue q = merge (q1, q2);


        System.out.println(q);
        System.out.println(q1);
        System.out.println(q2);;

    }

    public static LinkedQueue merge (LinkedQueue fila1, LinkedQueue fila2){
        LinkedQueue novaFila = new LinkedQueue();

        int loop = fila1.size();
        if (fila2.size()<fila1.size()) loop=fila2.size();
        int a, b, c;

        for (int i=0; i<loop; i++){

                a = fila1.dequeue();
            System.out.println("a " + a);
                novaFila.enqueue(a);
                fila1.enqueue(a);

                b = fila2.dequeue();
            System.out.println("b " + b);
                novaFila.enqueue(b);
                fila2.enqueue(b);

        }

        int loop2 = Math.abs(fila1.size()-fila2.size());
        System.out.println(loop2);


        if (fila1.size()>fila2.size()){
            for (int m=0; m<loop2; m++){
                c = fila1.dequeue();
                System.out.println("c " + c);
                novaFila.enqueue(c);
                fila1.enqueue(c);
            }
        }

        else {
            for (int m=0; m<loop2; m++){
                c = fila2.dequeue();
                System.out.println(" c " + c);
                novaFila.enqueue(c);
                fila2.enqueue(c);
            }
        }

        return novaFila;
    }

}
