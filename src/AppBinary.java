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
            arv.addLeft(1,2);
            arv.addRight(3,2);
            arv.addLeft(6,7);
            arv.addRight(8,7);
            arv.addLeft(11,12);
            arv.addRight(13,12);
            arv.addLeft(16,17);
            arv.addRight(18,17);

            System.out.println("Contagem total de elementos da árvore: " + arv.informaCount());
            System.out.println("Contagem de folhas: " + arv.countLeaves());
            System.out.println("Contagem de galhos: " + arv.countBranches() + "\n");

            System.out.println("Position Pré");
            System.out.println(arv.positionsPre());
            System.out.println("Position Pós");
            System.out.println(arv.positionsPos());
            System.out.println("Position Central");
            System.out.println(arv.positionsCentral());
            System.out.println("Position Central Str");
            System.out.println(arv.strPositionsCentral());
            System.out.println(arv.strPositionsCentral2());
            System.out.println("Position Width");
            System.out.println(arv.positionsWidth());

            System.out.println("\nNível do 10 é: " + arv.level(10));
            System.out.println("Nível do 15 é: " + arv.level(15));
            System.out.println("Nível do 12 é: " + arv.level(12));
            System.out.println("Nível do 08 é: " + arv.level(8));
            System.out.println("Nível do 20 é: " + arv.level(20));

            arv.removeBranch(5);
            System.out.println("\nContagem total de elementos da árvore após remoção: " + arv.informaCount());

            System.out.println("\nAltura da árvore é: " + arv.height());

            System.out.println(arv.isExternal(16));
            System.out.println(arv.isInternal(12));


            BinaryTreeOfInteger arv2 = new BinaryTreeOfInteger();
            arv2.addRoot(10);
            arv2.addLeft(5, 10);
            arv2.addRight(15, 10);
            arv2.addLeft(3,5);
            arv2.addRight(7,5);

            System.out.println(arv2.calculaMedia());
            System.out.println(arv2.calculaMedia2());

            BinaryTreeOfInteger arv3 = new BinaryTreeOfInteger();
            arv3.addRoot(10);
            arv3.addLeft(5, 10);
            arv3.addRight(15, 10);
            arv3.addLeft(3,5);
            arv3.addRight(6,5);

            System.out.println("Arv2 e arv3 são iguais? " + arv2.equals(arv3));

        }
}
