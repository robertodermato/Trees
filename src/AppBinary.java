public class AppBinary {
        public static void main(String[] args) {
            BinaryTreeOfInteger arv = new BinaryTreeOfInteger();
            arv.addRoot(10);
            arv.addLeft(5, 10);
            arv.addRight(15, 10);
            arv.addLeft(2,5);
            arv.addRight(7,5);
            arv.addLeft(12,15);
            arv.addRight(17,15);

            System.out.println("Contagem total de elementos da árvore");
            System.out.println(arv.informaCount());

            System.out.println("Position Pré");
            System.out.println(arv.positionsPre());
            System.out.println("Position Pós");
            System.out.println(arv.positionsPos());
            System.out.println("Position Central");
            System.out.println(arv.positionsCentral());
            System.out.println("Position Width");
            System.out.println(arv.positionsWidth());

        }
}
