public class AppGeneral {

        public static void main(String[] args) {

            GeneralTreeOfInteger arv = new GeneralTreeOfInteger();

            arv.add(1, null);
            arv.add(2,1);
            arv.add(3,1);
            arv.add(4,2);
            arv.add(5,2);
            arv.add(6,2);

            System.out.println("Caminhamento em largura:");
            System.out.println(arv.positionsWidth());

            System.out.println("Caminhamento pré-fixado:");
            System.out.println(arv.positionsPre());

            System.out.println("Caminhamento pós-fixado:");
            System.out.println(arv.positionsPos());

            //Testando método level
            System.out.println("Nível do elemento 6: " + arv.level(6));
            System.out.println("Nível do elemento 1: " + arv.level(1));
            System.out.println("Nível do elemento 8: " + arv.level(8));

            System.out.println(arv.contaNodos(1));
            System.out.println(arv.contaNodos(2));
        }
}
