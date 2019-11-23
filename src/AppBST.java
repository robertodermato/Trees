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
        //b.add(39);
        //b.add(40);
        System.out.println("Caminhamento central - ordem correta:");
        System.out.println(b.positionsCentral().toString());
        System.out.println("\nCaminhamento pré:");
        System.out.println(b.positionsPre());
        System.out.println(b.strTraversalPre());
        System.out.println("\nCaminhamento pós:");
        System.out.println(b.positionsPos());
        System.out.println("\nCaminhamento largura:");
        System.out.println(b.positionsWidth());

        System.out.println("\nNível do 15 é: " + b.level(15));
        System.out.println("Nível do 15 é: " + b.level(38));
        System.out.println("Nível do 15 é: " + b.level(23));
        System.out.println("Nível do 15 é: " + b.level(34));

        //System.out.println(b.removeBranch(9));
        //System.out.println(b.removeBranch(23));

        System.out.println("\nMaior é: " + b.getBiggest());

        System.out.println("\nAltura da árvore é: " + b.height());

        System.out.println("\nFolhas: " + b.countLeaves());
        System.out.println("Folhas Micael: " + b.countLeaves2());
        System.out.println("Folhas professora: " + b.countLeaves3());


        //b.remove(23);
        //b.remove(9);
        //b.remove(20);
        //b.remove(38);
        //b.remove(15);
        System.out.println("\nCaminhamento central - ordem correta após remoção:");
        System.out.println(b.positionsCentral().toString());

        BinarySearchTreeOfInteger nova = b.clone();

        System.out.println(nova.positionsCentral().toString());

        System.out.println(b.encontraPares());

    }
}
