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


            //System.out.println(arv.informaCount());
            System.out.println(arv.strPositionsCentral());
            System.out.println(arv.positionsCentral());
        }
}
