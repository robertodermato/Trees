public class AppARN {

    public static void main(String[] args) {

        ArvoreRubroNegra <MagicCard> listaCartas = new ArvoreRubroNegra<>();

        MagicCard card1 = new MagicCard("Dragão de Shiva", 57.50);
        MagicCard card2 = new MagicCard("Feiticeiro Pródigo", 12.40);

        listaCartas.add(card1);
        listaCartas.add(card2);



    }

}
