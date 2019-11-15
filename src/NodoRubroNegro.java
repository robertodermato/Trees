public class NodoRubroNegro<T extends Comparable<T>> {

    // cores possiveis
    public static final int BLACK = 0;
    public static final int RED = 1;

    // a chave para cada nodo
    public T key;

    NodoRubroNegro<T> parent;
    NodoRubroNegro<T> left;
    NodoRubroNegro<T> right;

    // número de elementos à esquerda de cada nodo
    public int numLeft = 0;
    // número de elementos à direita de cada nodo
    public int numRight = 0;

    public int color;

    //construtor para poder construir os nodos folhas nil
    NodoRubroNegro() {
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    //construtor para colocar os objetos
    NodoRubroNegro(T key) {
        this();
        this.key = key;
    }
}



