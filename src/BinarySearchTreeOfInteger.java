

public class BinarySearchTreeOfInteger {

    // Classe interna privada
    private static final class Node {
        public Integer element;
        public Node father;
        public Node left;
        public Node right;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }


    // Atributos
    private int count; //contador do numero de nodos
    private Node root; //referencia para o nodo raiz


    // Metodos
    public BinarySearchTreeOfInteger() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    private Node searchNodeRef(Integer element, Node target) {
        int r;

        if (element == null || target == null) {
            return null;
        }

        r = target.element.compareTo(element);

        if (r == 0) {
            return target;
        } else if (r > 0) {
            return searchNodeRef(element, target.left);
        } else {
            return searchNodeRef(element, target.right);
        }
    }

    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public boolean contains(Integer element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    public boolean isRoot(Integer element) {
        if (root != null) {
            if (root.element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Conta o total de nodos da subarvore cuja raiz esta sendo passada por
     * parametro.
     *
     * @param n referencia para o nodo a partir do qual sera feita a contagem
     * @return total de elementos da subarvore
     */
    private int countNodes(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + countNodes(n.left) + countNodes(n.right);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore. Os elementos
     * sao colocados na lista seguindo um caminhamento prefixado.
     *
     * @return lista com os elementos da arvore na ordem prefixada
     */
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subarvore esquerda
            positionsPreAux(n.right, res); //Visita a subarvore direita
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore. Os elementos
     * sao colocados na lista seguindo um caminhamento pósfixado.
     *
     * @return lista com os elementos da arvore na ordem posfixada
     */
    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPosAux(n.left, res); //Visita a subarvore esquerda
            positionsPosAux(n.right, res); //Visita a subarvore direita
            res.add(n.element); //Visita o nodo
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore. Os elementos
     * sao colocados na lista seguindo um caminhamento central.
     *
     * @return lista com os elementos da arvore na ordem central
     */
    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsCentralAux(n.left, res); //Visita a subarvore esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subarvore direita
        }
    }

    /**
     * Procura pelo menor elemento da subarvore cuja raiz eh passada por
     * parametro,e retorna a referencia para o nodo no qual este elemento
     * esta armazenado.
     *
     * @param n referencia para a raiz da subarvore na qual deve ser
     *          feita a busca.
     * @return referencia para o Node que contem o menor elemento da
     * subarvore.
     */
    private Node smallest(Node n) { //da professora
        if (n == null) {
            return null;
        }
        if (n.left == null) {
            return n;
        }
        return smallest(n.left);
    }

    private Node smallest2(Node n) { //meu
        if (isEmpty() || n == null) return null;

        while (n.right != null) {
            n = n.right;
        }

        return n;
    }

    /**
     * Adiciona o elemento passado por parametro na arvore.
     *
     * @param element elemento a ser adicionado na arvore.
     */
    public void add(Integer element) {
        root = add(root, element, null);
        count++;
    }

    private Node add(Node n, Integer e, Node father) {
        if (n == null) {
            Node aux = new Node(e);
            aux.father = father;
            return aux;
        }
        if (n.element.compareTo(e) < 0) {
            n.right = add(n.right, e, n);
        } else {
            n.left = add(n.left, e, n);
        }
        return n;
    }


    ////////////////////////////////////////////////////
    // Implemente os métodos abaixo
    ////////////////////////////////////////////////////

    /**
     * Remove da arvore o elemento passado como parametro, mantendo as
     * propriedades da ABP.
     *
     * @param element elemento a ser removido.
     * @return true se achou o elemento e fez a remocao, e false caso
     * contrario.
     */

    public boolean remove(Integer element) {
        if (element == null) return false;
        if (isEmpty()) return false;

        Node aRemover = searchNodeRef(element, root);
        if (aRemover == null) return false;



        return false;
    }


    /* Meu remove
    public boolean remove(Integer element) {
        if (element == null) return false;
        Node aRemover = searchNodeRef(element, root);
        if (aRemover == null) return false;

        if (aRemover.element == aRemover.father.left.element) {
            Node menorDosMaiores = aRemover.right;

            while (menorDosMaiores != null && menorDosMaiores.left != null) {
                menorDosMaiores = menorDosMaiores.left;
            }

            if (menorDosMaiores == null) {
                aRemover.father.left = aRemover.left;
                return true;
            }

            aRemover.element = menorDosMaiores.element;
            menorDosMaiores.father.right = null;

            return true;
        }

        Node maiorDosMenores = aRemover.left;

        while (maiorDosMenores != null && maiorDosMenores.right != null) {
            maiorDosMenores = maiorDosMenores.right;
        }

        if (maiorDosMenores == null) {
            aRemover.father.right = aRemover.right;
            return true;
        }

        aRemover.element = maiorDosMenores.element;
        maiorDosMenores.father.left = null;

        return true;

    }
    */


    /**
     * Retorna o maior elemento armazenado na ABP.
     *
     * @return Integer o maior elemento da arvore.
     */
    public Integer getBiggest() {
        if (isEmpty()) return null;
        Node aux = root;

        while (aux.right != null) {
            aux = aux.right;
        }

        return aux.element;
    }

    /**
     * Retorna a altura da arvore. Deve chamar um metodo auxiliar recursivo.
     *
     * @return altura da arvore
     */
    public int height() {
        //Implemente este metodo (de preferencia de forma recursiva)
        if (isEmpty()) return -1;
        return heightAux(root);
    }

    public int heightAux(Node target) {
        int height = 0;
        int heightLeft = 0;
        int heightRight = 0;

        if (target.left == null && target.right == null) return 1;

        if (target.left != null) heightLeft = height + heightAux(target.left);
        if (target.right != null) heightRight = height + heightAux(target.right);

        if (heightLeft > heightRight) height = 1 + heightLeft;
        else height = 1 + heightRight;

        return height;
    }

    private int heightAuxMicael(Node aux) {
        int hAux = 1;
        int hAuxL = 0;
        int hAuxR = 0;
        if (aux.left != null) {
            hAuxL = heightAuxMicael(aux.left);
        }
        if (aux.right != null) {
            hAuxR = heightAuxMicael(aux.right);
        }

        if (hAuxL > hAuxR) {
            hAux = hAux + hAuxL;
        } else {
            hAux = hAux + hAuxR;
        }
        return hAux;
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento em largura.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        LinkedListOfInteger lista = new LinkedListOfInteger();

        if (isEmpty()) return null;

        fila.enqueue(root);

        while (!fila.isEmpty()) {
            if (fila.head().left != null) fila.enqueue(fila.head().left);
            if (fila.head().right != null) fila.enqueue(fila.head().right);
            lista.add(fila.dequeue().element);
        }

        return lista;
    }


    /**
     * Retorna o elemento que eh o filho a direita do elemento
     * passado por parametro.
     *
     * @param element do qual se quer saber quem eh o filho a direita.
     * @return fiho da direita do elemento passado por parametro.
     */
    public Integer getRight(Integer element) {
        if (element == null) return null;
        if (isEmpty()) return null;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return null;
        return aux.right.element;
    }

    public Integer getLeft(Integer element) {
        Integer res = null;
        if (isEmpty()) return null;
        Node nAux = searchNodeRef(element, root);
        if (nAux != null) {
            if (nAux.left != null) {
                res = nAux.left.element;
            }
        }
        return res;
    }

    /**
     * Retorna o elemento que eh o pai do elemento passado por
     * parametro.
     *
     * @param element do qual se quer saber quem eh o pai.
     * @return pai do elemento passado por parametro.
     */
    public Integer getParent(Integer element) {
        if (element == null) return null;
        if (isEmpty()) return null;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return null;
        if (aux.father != null) return aux.father.element;
        return null;
    }

    /**
     * Remove um galho da árvore. A raiz deste galho eh o nodo que contem
     * o elemento passado por parâmetro (element). Caso "element" nao esteja
     * na arvore, nao eh feita a remocao e o metodo retorna false.
     *
     * @param element raiz da subarvore que deve ser removida
     * @return true se houve a remocao do galho, false caso contrario.
     */
    public boolean removeBranch(Integer element) {
        if (element == null) return false;
        if (isEmpty()) return false;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return false;
        int filhos = countNodes(aux);

        if (aux.father == null) {
            root = null;
            count = 0;
            return true;
        }

        if (aux.father.left!=null && aux.father.left.element == element) {
            aux.father.left = null;
            count = count - filhos;
            return true;
        }

        aux.father.right = null;
        count = count - filhos;
        return true;
    }

    /**
     * Retorna o nivel do nodo no qual esta armazenado o elemento
     * passadado por parametro.
     *
     * @param element o elemento que se quer saber o nivel.
     * @return o nivel do nodo onde esta o elemento, ou -1 se nao
     * encontrou o elemento.
     */
    public int level(Integer element) {
        if (element == null) return -1;
        if (isEmpty()) return -1;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return -1;

        int level = 0;

        while (aux.father!=null){
            aux=aux.father;
            level++;
        }

        return level;
    }

    /**
     * Retorna uma string que contem todos os elementos da arvore na ordem de
     * caminhamento pre-fixada. Chamar um metodo auxiliar recursivo.
     *
     * @return String com todos os elementos da arvore
     */
    public String strTraversalPre() {
        return strTraversalPre(root);
    }

    private String strTraversalPre(Node n) {
        String res = "";
        // Implemente este metodo
        return res;
    }

    /**
     * Retorna uma string que contem todos os elementos da arvore na ordem de
     * caminhamento pos-fixada. Chamar um metodo auxiliar recursivo.
     *
     * @return String com todos os elementos da arvore
     */
    public String strTraversalPos() {
        return strTraversalPos(root);
    }

    private String strTraversalPos(Node n) {
        String res = "";
        // Implemente este metodo
        return res;
    }

    /**
     * Retorna uma string que contem todos os elementos da arvore na ordem de
     * caminhamento central. Chamar um metodo auxiliar recursivo.
     *
     * @return String com todos os elementos da arvore
     */
    public String strTraversalCentral() {
        return strTraversalCentral(root);
    }

    private String strTraversalCentral(Node n) {
        String res = "";
        // Implemente este metodo
        return res;
    }

    /**
     * Retorna uma copia da arvore.
     *
     * @return BinarySearchTreeOfInteger com uma copia desta ABP.
     */
    @Override
    public BinarySearchTreeOfInteger clone() {
        return null;
    }

}
