import java.util.ArrayList;

public class BinaryTreeOfInteger {

    // Classe interna Node
    private static final class Node {
        public Node father;
        public Node left;
        public Node right;
        private Integer element;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }


    // Atributos da arvore binaria
    private int count; //total de elementos da arvore
    private Node root; //referencia para o nodo raiz da arvore


    // Metodos da arvore binaria
    public BinaryTreeOfInteger() {
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

    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(Integer element) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
    }

    public boolean addRoot(Integer element) {
        if (root != null) {
            return false;
        }
        Node node = new Node(element);
        root = node;
        count++;
        return true;
    }

    public boolean contains(Integer element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    private Node searchNodeRef(Integer element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.element.equals(element)) {
                res = target;
            } else {
                res = searchNodeRef(element, target.left);
                if (res == null) {
                    res = searchNodeRef(element, target.right);
                }
            }
        }
        return res;
    }


    ////////////////////////////////////////////////////
    // Implemente os metodos abaixo considerando os
    // comentarios javadoc.
    ////////////////////////////////////////////////////

    /**
     * Insere um elemento a esquerda de outro elemento (pai).
     * Caso ja tenha um elemento a esquerda do pai, ou caso o
     * elemento pai nao seja encontrado na arvore, a insercao
     * nao e realizada.
     *
     * @param element elemento a ser inserido
     * @param father  pai do elemento a ser inserido.
     * @return true se o elemento for inserido e false caso
     * contrario.
     */
    public boolean addLeft(Integer element, Integer father) {
        if (father == null || element == null) return false;
        Node aux = searchNodeRef(father, root);
        if (aux == null) return false;
        if (aux.left != null) return false;
        Node novoNodo = new Node(element);
        novoNodo.father = aux;
        aux.left = novoNodo;
        count++;
        return true;
    }

    /**
     * Insere um elemento a direita de outro elemento (pai).
     * Caso ja tenha um elemento a direita do pai, ou caso o
     * elemento pai nao seja encontrado na arvore, a insercao
     * nao e realizada.
     *
     * @param element elemento a ser inserido
     * @param father  pai do elemento a ser inserido.
     * @return true se o elemento for inserido e false caso
     * contrario.
     */
    public boolean addRight(Integer element, Integer father) {
        if (father == null || element == null) return false;
        Node aux = searchNodeRef(father, root);
        if (aux == null) return false;
        if (aux.right != null) return false;
        Node novoNodo = new Node(element);
        novoNodo.father = aux;
        aux.right = novoNodo;
        count++;
        return true;
    }

    /**
     * Conta o total de nodos da subarvore cuja raiz esta sendo passada por
     * parametro.
     *
     * @param n referencia para o nodo a partir do qual sera feita a contagem
     * @return
     */
    private int countNodes(Node n) {
        if (n == null || isEmpty()) return 0;
        Node aux = searchNodeRef(n.element, root);
        if (aux == null) return 0;

        int count = 1;

        count = count + countNodes(n.left);
        count = count + countNodes(n.right);

        return count;
    }

    public int informaCount() {
        Node aux = searchNodeRef(10, root);
        return countNodes(aux);
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento pre-fixada. Deve chamar um metodo auxiliar recursivo.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        if (isEmpty() == false) positionsPreAux(lista, root);
        return lista;
    }

    private void positionsPreAux(LinkedListOfInteger lista, Node nodo) {
        if (nodo != null) {
            lista.add(nodo.element);
            positionsPreAux(lista, nodo.left);
            positionsPreAux(lista, nodo.right);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento pos-fixada. Deve chamar um metodo auxiliar recursivo.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        if (isEmpty() == false) positionsPosAux(lista, root);
        return lista;
    }

    private void positionsPosAux(LinkedListOfInteger lista, Node nodo) {
        if (nodo != null) {
            positionsPosAux(lista, nodo.left);
            positionsPosAux(lista, nodo.right);
            lista.add(nodo.element);
        }
    }


    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento central. Deve chamar um metodo auxiliar recursivo.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        if (isEmpty() == false) positionsCentralAux(lista, root);
        return lista;
    }

    private void positionsCentralAux(LinkedListOfInteger lista, Node nodo) {
        if (nodo != null) {
            positionsCentralAux(lista, nodo.left);
            lista.add(nodo.element);
            positionsCentralAux(lista, nodo.right);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento em largura.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedListOfInteger positionsWidth() {
        LinkedListOfInteger largura = new LinkedListOfInteger();
        Queue<Node> fila = new Queue();

        fila.enqueue(root);

        while (!fila.isEmpty()) {
            if (fila.head().left != null) fila.enqueue(fila.head().left);
            if (fila.head().right != null) fila.enqueue(fila.head().right);
            largura.add(fila.dequeue().element);
        }

        return largura;
    }

    /**
     * Procura pelo elemento passado por parametro e retorna o seu pai.
     *
     * @param element elemento raiz da subarvore a ser removida.
     * @return true se o elemento passado por parametro for encontrado
     * e removido ou false caso contrario.
     */
    public boolean removeBranch(Integer element) {
        if (element == null) return false;

        Node aux = searchNodeRef(element, root);
        if (aux == null) return false;

        Node pai = aux.father;

        int removidos = countNodes(aux);

        if (pai.left == aux) pai.left = null;
        else pai.right = null;

        count = count - removidos;

        return true;
    }

    /**
     * Retorna uma string que contem todos os elementos da arvore na ordem de
     * caminhamento central. Deve chamar um metodo auxiliar recursivo.
     *
     * @return String com todos os elementos da arvore
     */
    public String strPositionsCentral() {
        String lista = "[";
        if (isEmpty() == false) lista = strPositionsCentralAux(lista, root);
        lista = lista + "]";
        lista = lista.replace(", ]", "]");
        return lista;
    }

    private String strPositionsCentralAux(String lista, Node nodo) {
        if (nodo != null) {
            lista = strPositionsCentralAux(lista, nodo.left);
            lista = lista + nodo.element + ", ";
            lista = strPositionsCentralAux(lista, nodo.right);
        }
        return lista;
    }

    //Tentativa 2
    public String strPositionsCentral2() {
        ArrayList<Integer> lista = new ArrayList<>();
        if (isEmpty() == false) strPositionsCentralAux2(lista, root);
        return lista + "";
    }

    private ArrayList<Integer> strPositionsCentralAux2(ArrayList<Integer> lista, Node nodo) {
        if (nodo != null) {
            strPositionsCentralAux2(lista, nodo.left);
            lista.add(nodo.element);
            strPositionsCentralAux2(lista, nodo.right);
        }
        return lista;
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
        Node aux = searchNodeRef(element, root);
        if (aux == null) return -1;
        int level = 0;

        while (aux.father != null) {
            level++;
            aux = aux.father;
        }

        return level;
    }

    /**
     * Retorna a altura da arvore. Deve chamar um metodo auxiliar recursivo.
     *
     * @return altura da arvore
     */
    public int height() {
        if (isEmpty()) return -1;
        return heightAux(root) - 1;
    }

    public int heightAux(Node node) {
        if (node == null)
            return 0;
        else {
            /* compute the depth of each subtree */
            int leftDepth = heightAux(node.left);
            int rightDepth = heightAux(node.right);

            /* use the larger one */
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }
    }

    public int heightMicael() {
        int h;
        if (root == null) {
            h = -1;
        } else {
            h = heightAuxMicael(root) - 1;
        }
        return h;
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

    public int height2() {
        if (isEmpty()) return -1;

        return heightAux2(root);
    }

    private int heightAux2(Node nodo) {
        if (nodo == null) return -1;
        return max(heightAux2(root.left), heightAux2(root.right)) + 1;
    }

    private int max(int a, int b) {
        if (a > b) return a;
        return b;
    }

    /**
     * Verifica se o elemento passado por parametro esta armazenado em
     * um nodo folha.
     *
     * @param element elemento a ser encontrado.
     * @return true se o elemento esta em um nodo folha, e false caso contrario.
     */
    public boolean isExternal(Integer element) {
        if (element == null) return false;
        Node aux = searchNodeRef(element, root);
        if (aux != null) {
            if (aux.left != null || aux.right != null) return false;
            return true;
        }
        return false;
    }

    /**
     * Verifica se o elemento passado por parametro esta armazenado em
     * um nodo interno ou galho.
     *
     * @param element elemento a ser encontrado.
     * @return true se o elemento esta em um nodo interno, e false caso contrario.
     */
    public boolean isInternal(Integer element) {
        if (element == null) return false;
        Node aux = searchNodeRef(element, root);
        if (aux != null) {
            if (aux.left != null || aux.right != null) return true;
            return false;
        }
        return false;
    }

    /**
     * Verifica se o elemento passado por parametro tem um filho na
     * subarvore da esquerda.
     *
     * @param element elemento a ser encontrado para verificar se possui filho
     *                a esquerda.
     * @return true se o elemento tem subarvore a esquerda, e false caso contrario.
     */
    public boolean hasLeft(Integer element) {
        if (isEmpty()) return false;
        if (element == null) return false;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return false;
        return (aux.left != null);
    }

    /**
     * Verifica se o elemento passado por parametro tem um filho na
     * subarvore da direita.
     *
     * @param element elemento a ser encontrado para verificar se possui filho
     *                a direita
     * @return true se o elemento tem subarvore a direita, e false caso contrario.
     */
    public boolean hasRight(Integer element) {
        if (isEmpty()) return false;
        if (element == null) return false;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return false;
        return (aux.right != null);
    }

    /**
     * Procura pelo elemento passado por parametro e retorna o seu filho da
     * subarvore da esquerda.
     *
     * @param element elemento a ser encontrado para retornar seu filho a esquerda.
     * @return referencia para o filho da subarvore da esquerda, ou null caso o
     * elemento passado por parametro nao seja encontrado ou caso ele nao tenha
     * filho na subarvore da esquerda.
     */
    public Integer getLeft(Integer element) {
        if (element == null) return null;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return null;
        if (aux.left == null) return null;
        return aux.left.element;
    }

    /**
     * Procura pelo elemento passado por parametro e retorna o seu filho da
     * subarvore da direita.
     *
     * @param element elemento a ser encontrado para retornar seu filho a direita.
     * @return referencia para o filho da subarvore da direita, ou null caso o
     * elemento passado por parametro nao seja encontrado ou caso ele nao tenha
     * filho na subarvore da direita.
     */
    public Integer getRight(Integer element) {
        if (element == null) return null;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return null;
        if (aux.right == null) return null;
        return aux.right.element;

    }

    /**
     * Procura pelo elemento passado por parametro e retorna o seu pai.
     *
     * @param element elemento a ser encontrado para retornar seu pai.
     * @return referencia para o pai, ou null caso o elemento passado por
     * parametro nao seja encontrado ou caso ele esteja na raiz.
     */
    public Integer getParent(Integer element) {
        if (element == null) return null;
        Node aux = searchNodeRef(element, root);
        if (aux == null) return null;
        if (aux.father == null) return null;
        return aux.father.element;
    }

    public int countLeaves() {
        return countLeavesAux(root);
    }


    private int countLeavesAux(Node target) {
        int count = 0;
        if (target.right == null && target.left == null) return 1;
        count = count + countLeavesAux(target.right);
        count = count + countLeavesAux(target.left);
        return count;
    }

    /*
    private int countBranchesAux(Node n){ - Micael
        int branchcount = 0;

        int leftbranchcount = 0;
        int rightbranchcount = 0;
        if(n.left != null){
            branchcount = 1;
            leftbranchcount = countBranchesAux(n.left);
        }
        if(n.right != null){
            branchcount = 1;
            rightbranchcount = countBranchesAux(n.right);
        }
        return branchcount + leftbranchcount + rightbranchcount;
    }
    */

    public int countBranches() {
        return countBranchesAux(root);
    }

    private int countBranchesAux(Node target) {
        int count = 0;
        if (target.right == null && target.left == null) return 0;
        else count++;
        count = count + countBranchesAux(target.right);
        count = count + countBranchesAux(target.left);
        return count;
    }

    public double calculaMedia() {
        if (isEmpty()) return 0;
        return calculaMediaAux(root) * 1.0 / size();
    }

    private double calculaMediaAux(Node nodo) {
        double soma = 0;
        if (nodo == null) return 0;

        soma = soma + nodo.element;
        if (nodo.left != null) soma = soma + calculaMediaAux(nodo.left);
        if (nodo.right != null) soma = soma + calculaMediaAux(nodo.right);

        return soma;
    }

    public Double calculaMedia2() {
        Double soma = 0.0;
        soma = calculaMediaAux2(root, soma);
        return soma * 1.0 / size();
    }

    private Double calculaMediaAux2(Node n, Double soma) {
        soma = soma + n.element;
        if (n.left != null) soma = calculaMediaAux2(n.left, soma);
        if (n.right != null) soma = calculaMediaAux2(n.right, soma);
        return soma;
    }

    //Escreva o algoritmo de um método boolean equals(Binary TreeOflnteger b)
    //para a classe Binary TreeOfInteger estudada em aula e apresentada abaixo.
    // Este algoritmo deve comparar o conteúdo da árvore com o conteúdo da árvore passada por parâmetro.
    // Se ambas tiverem exatamente o mesmo conteúdo (mesmos elementos nas mesmas posições).
    // o método retorna true, senão, retorna false.
    // Use apenas os atributos da classe BinaryTreeOfInteger na sua solução, isto é, nenhum outro método desta classe pode ser chamado.
    public boolean equals(BinaryTreeOfInteger b) {
        if (size() != b.count) return false;
        return equalsAux(b.root, root);
    }

    private boolean equalsAux(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a.left != null && b.left == null) return false;
        if (a.left == null && b.left != null) return false;
        if (a.right != null && b.right == null) return false;
        if (a.right == null && b.right != null) return false;
        if (a.element != b.element) return false;

        return true && equalsAux(a.left, b.left) && equalsAux(a.right, b.right);
    }

    //Escreva um método boolean isSorted() Este método verifica se o conteúdo desta lista
    // está ordenado em ordem crescente ou não. Se estiver ordenado é retornado true.
    // Caso contrário, é retornado false. Não podem ser chamados outros métodos desta classe. (2.0 pontos)
    public boolean isSorted() {
        return isSortedAux(root);
    }

    private boolean isSortedAux(Node n) {
        if (n.left != null) return isSortedAux(n.left);
        if (true) return true;
        if (n.right != null) return isSortedAux(n.right);
        return true;
    }

}
