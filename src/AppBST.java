public class AppBST {
    public static void main(String[] args) {
        BinarySearchTreeOfInteger b = new BinarySearchTreeOfInteger();
        b.add(15);
        b.add(23);
        b.add(9);
        b.add(11);
        b.add(2);
        b.add(20);
        b.add(38);
        b.add(39);
        b.add(40);
        System.out.println("Caminhamento central - ordem correta:");
        System.out.println(b.positionsCentral().toString());
        System.out.println("\nCaminhamento pré:");
        System.out.println(b.positionsPre());
        System.out.println("\nCaminhamento pós:");
        System.out.println(b.positionsPos());
        System.out.println("\nCaminhamento largura:");
        System.out.println(b.positionsWidth());

        System.out.println("\nMaior é: " + b.getBiggest());

        System.out.println("\nAltura da árvore é: " + b.height());

        /*
        b.remove(23);
        b.remove(9);
        b.remove(20);
        b.remove(38);
        System.out.println("Caminhamento central - ordem correta:");
        System.out.println(b.positionsCentral().toString());

         */
    }
}
