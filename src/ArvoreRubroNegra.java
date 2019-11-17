/*
Design Decisions:
-----------------
I chose to use the sentinel instead of regular null pointers because it makes
removeFixup() easier and more efficient.  Every RedBlackNode instantiated has
all of it's pointers pointed to nil.  The root at all times will have it's
parent pointer to nil. The remove and delete algorithm's are based on the
course textbook and so are the leftRotate(RedBlackNode x) and
rightRotate(RedBlackNode y) functions.
After an insertion of an element using insert(), we always call insertFixup()
to ensure that red-black properties are maintained.  While when deleteing, we
only call deleteFixup when a certain condition( x == BLACK) is true.
Since we are only concerned with deleting the key from the tree, we will begin
our delete(RedBlackNode v) function with a call to search(v.key) which will
ensure us that we are deleting the correct node.
I have implemented the numSmaller(int) and numGreater(int) functions by keeping
track of how many elements are to the left (numLeft) and to the right (numRight)
of each node.  They both contain the number of elements to the left or right of
a given node, not including that node itself.
This value is updated when a node is inserted and maintained by the functions
leftRotateFixup(RedBlackNode) and rightRotateFixup(RedBlackNode) which update
these variables when a rotation occurs. This value is also updated during the
deletion of a node by the function called fixNodeData(RedBlackNode, int).
My size() function checks the size of the roots numLeft and numRight variables,
adds them and adds one to return the answer.  This operation is performed in
O(1) time.
In the program, I am checking for the case where a particular RedBlackNode has
a pointer pointing to nil, since this operation is very common, I have a
function called isNil(RedBlackNode), which returns a boolean value of whether
the argument is nil or not.  I have chosen my search(int key) function to be
iterative when it easily could have been recursive because the textbook
mentions that an iterative search is always faster than a recursive one.
Duplicate RedBlackNodes are thought of as being slightly greater than its
counterpart with the same key.  The insert() function takes care of this
by having to cases in it's while loop, one for < and one for =>.  The
function fixNodeData() takes care of this during deletion as also having two
cases.
I have chosen to represent, RED as the integer value 1 and BLACK as the integer
value 0. Both these are declared as final in this class' instance variables.
These values are assigned to the 'color' variable.
*/

// Inclusions

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArvoreRubroNegra<T extends Comparable<T>> {

    // Raiz inicializada com nil
    private NodoRubroNegro<T> nil = new NodoRubroNegro<T>();
    private NodoRubroNegro<T> root;

    public ArvoreRubroNegra() {
        root = nil;
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    // @param: x, nodo sobre o qual será executada uma rotação para esquerda
    private void leftRotate(NodoRubroNegro<T> x) {

        // chama leftRotateFixup() que faz update em numLeft e numRight
        leftRotateFixup(x);

        // faz o left rotate
        NodoRubroNegro<T> y;
        y = x.right;
        x.right = y.left;

        //checa de y.left exsite e faz mudanças necessárias
        if (!isNil(y.left))
            y.left.parent = x;
        y.parent = x.parent;

        //pai do x é nulo
        if (isNil(x.parent))
            root = y;

            // x é o filho da esquerda
        else if (x.parent.left == x)
            x.parent.left = y;

            // x é o filho da direita
        else
            x.parent.right = y;

        // termina o leftRotate
        y.left = x;
        x.parent = y;
    }


    // @param: x, Nodo sobre o qual o leftRotate é executado.
    // Esse método faz um update nos valores de numLeft e numRight que serão afetados pelo leftRotate.
    private void leftRotateFixup(NodoRubroNegro x) {

        // Caso 1: Somente x, x.right e x.right.right sempre são não nil.
        if (isNil(x.left) && isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 0;
            x.right.numLeft = 1;
        }

        // Caso 2: x.right.left também existe em adição ao Caso 1
        else if (isNil(x.left) && !isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 2 + x.right.left.numLeft +
                    x.right.left.numRight;
        }

        // Caso 3: x.left existe em adição ao Caso 1
        else if (!isNil(x.left) && isNil(x.right.left)) {
            x.numRight = 0;
            x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

        }
        // Caso 4: x.left e x.right.left existem em adição ao Caso 1
        else {
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
                    x.right.left.numLeft + x.right.left.numRight;
        }

    }


    // @param: x, nodo sobre o qual o rightRotate é executado
    // Atualiza os valores de numLeft e numRight afetados pela rotação.
    private void rightRotate(NodoRubroNegro<T> y) {

        // chama rightRotateFixup para ajustar os valores de numRight e numLeft
        rightRotateFixup(y);

        // faz a rotação
        NodoRubroNegro<T> x = y.left;
        y.left = x.right;

        // checa se x.right existe
        if (!isNil(x.right))
            x.right.parent = y;
        x.parent = y.parent;

        // y.parent é nil
        if (isNil(y.parent))
            root = x;

            // y é o filho da direita
        else if (y.parent.right == y)
            y.parent.right = x;

            // y é o filho da esquerda
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

    }


    // @param: y, nodo sobre o qual o righRotate é executado.
    // Atualiza os valores de numLeft e numRight afetaods pela rotação
    private void rightRotateFixup(NodoRubroNegro y) {

        // Caso 1: Somente y, y.left e y.left.left existem.
        if (isNil(y.right) && isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        }

        // Caso 2: y.left.right também existe
        else if (isNil(y.right) && !isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 2 + y.left.right.numRight +
                    y.left.right.numLeft;
        }

        // Caso 3: y.right também existe em adição ao caso1
        else if (!isNil(y.right) && isNil(y.left.right)) {
            y.numLeft = 0;
            y.left.numRight = 2 + y.right.numRight + y.right.numLeft;

        }

        // Caso 4: y.right e y.left.right existem
        else {
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 3 + y.right.numRight +
                    y.right.numLeft +
                    y.left.right.numRight + y.left.right.numLeft;
        }

    }


    public void add(T novoNodo) {
        add(new NodoRubroNegro<T>(novoNodo));
    }

    // @param: novoNodo, the node to be inserted into the Tree rooted at root
    // Inserts novoNodo into the appropriate position in the RedBlackTree while
    // updating numLeft and numRight values.
    private void add(NodoRubroNegro<T> novoNodo) {

        // Cria uma referência para raiz e inicializa um nodo como mil
        NodoRubroNegro<T> y = nil;
        NodoRubroNegro<T> x = root;

        // Enquanto não se chega ao final da árvore continue vendo onde colocar o nonoNodo
        while (!isNil(x)) {
            y = x;

            // if z.key is < than the current key, go left
            if (novoNodo.key.compareTo(x.key) < 0) {

                // Update x.numLeft as z is < than x
                x.numLeft++;
                x = x.left;
            }

            // else z.key >= x.key so go right.
            else {

                // Update x.numGreater as z is => x
                x.numRight++;
                x = x.right;
            }
        }
        // y will hold z's parent
        novoNodo.parent = y;

        // Depending on the value of y.key, put z as the left or
        // right child of y
        if (isNil(y))
            root = novoNodo;
        else if (novoNodo.key.compareTo(y.key) < 0)
            y.left = novoNodo;
        else
            y.right = novoNodo;

        // Initialize z's children to nil and z's color to red
        novoNodo.left = nil;
        novoNodo.right = nil;
        novoNodo.color = NodoRubroNegro.RED;

        // Call insertFixup(z)
        insertFixup(novoNodo);

    }// end insert(RedBlackNode z)


    // @param: novoNodo, the node which was inserted and may have caused a violation
    // of the RedBlackTree properties
    // Fixes up the violation of the RedBlackTree properties that may have
    // been caused during insert(z)
    private void insertFixup(NodoRubroNegro<T> novoNodo) {

        NodoRubroNegro<T> y = nil;
        // While there is a violation of the RedBlackTree properties..
        while (novoNodo.parent.color == NodoRubroNegro.RED) {

            // If z's parent is the the left child of it's parent.
            if (novoNodo.parent == novoNodo.parent.parent.left) {

                // Initialize y to z 's cousin
                y = novoNodo.parent.parent.right;

                // Case 1: if y is red...recolor
                if (y.color == NodoRubroNegro.RED) {
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    y.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    novoNodo = novoNodo.parent.parent;
                }
                // Case 2: if y is black & z is a right child
                else if (novoNodo == novoNodo.parent.right) {

                    // leftRotaet around z's parent
                    novoNodo = novoNodo.parent;
                    leftRotate(novoNodo);
                }

                // Case 3: else y is black & z is a left child
                else {
                    // recolor and rotate round z's grandpa
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    rightRotate(novoNodo.parent.parent);
                }
            }

            // If z's parent is the right child of it's parent.
            else {

                // Initialize y to z's cousin
                y = novoNodo.parent.parent.left;

                // Case 1: if y is red...recolor
                if (y.color == NodoRubroNegro.RED) {
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    y.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    novoNodo = novoNodo.parent.parent;
                }

                // Case 2: if y is black and z is a left child
                else if (novoNodo == novoNodo.parent.left) {
                    // rightRotate around z's parent
                    novoNodo = novoNodo.parent;
                    rightRotate(novoNodo);
                }
                // Case 3: if y  is black and z is a right child
                else {
                    // recolor and rotate around z's grandpa
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    leftRotate(novoNodo.parent.parent);
                }
            }
        }
        // Color root black at all times
        root.color = NodoRubroNegro.BLACK;

    }// end insertFixup(RedBlackNode z)

    // @param: node, a RedBlackNode
    // @param: node, the node with the smallest key rooted at node
    public NodoRubroNegro<T> getSmallest(NodoRubroNegro<T> node) {

        // while there is a smaller key, keep going left
        while (!isNil(node.left))
            node = node.left;
        return node;
    }// end treeMinimum(RedBlackNode node)


    // @param: x, a RedBlackNode whose successor we must find
    // @return: return's the node the with the next largest key
    // from x.key
    public NodoRubroNegro<T> treeSuccessor(NodoRubroNegro<T> x) {

        // if x.left is not nil, call treeMinimum(x.right) and
        // return it's value
        if (!isNil(x.left))
            return getSmallest(x.right);

        NodoRubroNegro<T> y = x.parent;

        // while x is it's parent's right child...
        while (!isNil(y) && x == y.right) {
            // Keep moving up in the tree
            x = y;
            y = y.parent;
        }
        // Return successor
        return y;
    }// end treeMinimum(RedBlackNode x)


    // @param: z, the RedBlackNode which is to be removed from the the tree
    // Remove's z from the RedBlackTree rooted at root
    public void remove(NodoRubroNegro<T> v) {

        NodoRubroNegro<T> z = searchRefNode(v.key);

        // Declare variables
        NodoRubroNegro<T> x = nil;
        NodoRubroNegro<T> y = nil;

        // if either one of z's children is nil, then we must remove z
        if (isNil(z.left) || isNil(z.right))
            y = z;

            // else we must remove the successor of z
        else y = treeSuccessor(z);

        // Let x be the left or right child of y (y can only have one child)
        if (!isNil(y.left))
            x = y.left;
        else
            x = y.right;

        // link x's parent to y's parent
        x.parent = y.parent;

        // If y's parent is nil, then x is the root
        if (isNil(y.parent))
            root = x;

            // else if y is a left child, set x to be y's left sibling
        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;

            // else if y is a right child, set x to be y's right sibling
        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;

        // if y != z, trasfer y's satellite data into z.
        if (y != z) {
            z.key = y.key;
        }

        // Update the numLeft and numRight numbers which might need
        // updating due to the deletion of z.key.
        fixNodeData(x, y);

        // If y's color is black, it is a violation of the
        // RedBlackTree properties so call removeFixup()
        if (y.color == NodoRubroNegro.BLACK)
            removeFixup(x);
    }// end remove(RedBlackNode z)


    // @param: y, the RedBlackNode which was actually deleted from the tree
    // @param: key, the value of the key that used to be in y
    private void fixNodeData(NodoRubroNegro<T> x, NodoRubroNegro<T> y) {

        // Initialize two variables which will help us traverse the tree
        NodoRubroNegro<T> current = nil;
        NodoRubroNegro<T> track = nil;


        // if x is nil, then we will start updating at y.parent
        // Set track to y, y.parent's child
        if (isNil(x)) {
            current = y.parent;
            track = y;
        }

        // if x is not nil, then we start updating at x.parent
        // Set track to x, x.parent's child
        else {
            current = x.parent;
            track = x;
        }

        // while we haven't reached the root
        while (!isNil(current)) {
            // if the node we deleted has a different key than
            // the current node
            if (y.key != current.key) {

                // if the node we deleted is greater than
                // current.key then decrement current.numRight
                if (y.key.compareTo(current.key) > 0)
                    current.numRight--;

                // if the node we deleted is less than
                // current.key thendecrement current.numLeft
                if (y.key.compareTo(current.key) < 0)
                    current.numLeft--;
            }

            // if the node we deleted has the same key as the
            // current node we are checking
            else {
                // the cases where the current node has any nil
                // children and update appropriately
                if (isNil(current.left))
                    current.numLeft--;
                else if (isNil(current.right))
                    current.numRight--;

                    // the cases where current has two children and
                    // we must determine whether track is it's left
                    // or right child and update appropriately
                else if (track == current.right)
                    current.numRight--;
                else if (track == current.left)
                    current.numLeft--;
            }

            // update track and current
            track = current;
            current = current.parent;

        }

    }


    // @param: x, o filho do nodo deletado pelo remove
    // Restaura as propriedades da ARN que podem ter sido violadas durante o remove
    private void removeFixup(NodoRubroNegro<T> x) {

        NodoRubroNegro<T> w;


        while (x != root && x.color == NodoRubroNegro.BLACK) {

            // se x é o pai do filho da esquerda
            if (x == x.parent.left) {

                // w vira o irmão de x
                w = x.parent.right;

                // Caso 1, w é rubro
                if (w.color == NodoRubroNegro.RED) {
                    w.color = NodoRubroNegro.BLACK;
                    x.parent.color = NodoRubroNegro.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                // Caso 2, ambos filhos de w são negros
                if (w.left.color == NodoRubroNegro.BLACK &&
                        w.right.color == NodoRubroNegro.BLACK) {
                    w.color = NodoRubroNegro.RED;
                    x = x.parent;
                } else {
                    // Caso 3, filho da diretia de w é negro
                    if (w.right.color == NodoRubroNegro.BLACK) {
                        w.left.color = NodoRubroNegro.BLACK;
                        w.color = NodoRubroNegro.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Caso 4, w é ngro e seu filho da direita é vermelho
                    w.color = x.parent.color;
                    x.parent.color = NodoRubroNegro.BLACK;
                    w.right.color = NodoRubroNegro.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            // se x é o filho da direita
            else {

                // deixa w como irmão da esquerda
                w = x.parent.left;

                // Caso 1, w é rubro
                if (w.color == NodoRubroNegro.RED) {
                    w.color = NodoRubroNegro.BLACK;
                    x.parent.color = NodoRubroNegro.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                // Caso 2, ambos filhos de w são negros
                if (w.right.color == NodoRubroNegro.BLACK &&
                        w.left.color == NodoRubroNegro.BLACK) {
                    w.color = NodoRubroNegro.RED;
                    x = x.parent;
                } else {
                    // Caso 3, filho da esquerda de w é negro
                    if (w.left.color == NodoRubroNegro.BLACK) {
                        w.right.color = NodoRubroNegro.BLACK;
                        w.color = NodoRubroNegro.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    // Caso 4, w é negro e seu filho da esquerda é vermelho
                    w.color = x.parent.color;
                    x.parent.color = NodoRubroNegro.BLACK;
                    w.left.color = NodoRubroNegro.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        // x fica negro para garantir as propriedades da ARN
        x.color = NodoRubroNegro.BLACK;
    }


    // @param: key, a key de cujo nodo estamos procurando
    // @return: retorna um nodo com a key. Se não encontrar retorn nulo
    public NodoRubroNegro<T> searchRefNode(T key) {

        // Inicializa com a raiz para percorrer toda árvore
        NodoRubroNegro<T> current = root;

        while (!isNil(current)) {

            if (current.key.equals(key))
                return current;

            else if (current.key.compareTo(key) < 0)
                current = current.right;

            else
                current = current.left;
        }

        return null;
    }

    // @param: key, qualquer objeto com Comparable
    // @return: retorna o número de elementos maiores que key
    public int numGreater(T key) {
        return findNumGreater(root, key);
    }

    // @param: key, qualquer objeto com Comparable
    // @return: retorna o número de elementos menores que key
    public int numSmaller(T key) {
        return findNumSmaller(root, key);
    }

    // @param node, a raiz da árvore
    // @param key, a key que desejamos comparar
    // @return: retorna o número de nodos maiores que key
    public int findNumGreater(NodoRubroNegro<T> node, T key) {

        if (isNil(node))
            return 0;

            // se a key for menor que a node.key, então todos elementos da direita são maiores
            // adiciona esses elementos e procura na esquerda
        else if (key.compareTo(node.key) < 0)
            return 1 + node.numRight + findNumGreater(node.left, key);

            // se a key for maior que node.key, então procura na direita
        else
            return findNumGreater(node.right, key);

    }

    /**
     * retorna uma lista organizada de keys maiores que key. Tamanho da lista não será maior que maxReturned
     *
     * @param key         Key a ser procurada
     * @param maxReturned número máximo de resultados
     * @return retorna a lista com keys maiores que key
     */
    public List<T> getGreaterThan(T key, Integer maxReturned) {
        List<T> list = new ArrayList<T>();
        getGreaterThan(root, key, list);
        return list.subList(0, Math.min(maxReturned, list.size()));
    }


    private void getGreaterThan(NodoRubroNegro<T> node, T key,
                                List<T> list) {
        if (isNil(node)) {
            return;
        } else if (node.key.compareTo(key) > 0) {
            getGreaterThan(node.left, key, list);
            list.add(node.key);
            getGreaterThan(node.right, key, list);
        } else {
            getGreaterThan(node.right, key, list);
        }
    }

    // @param: node, a raiz da árvore
    // @param key, key a ser comparada
    // @return: retorna o número de nodos menores que key
    public int findNumSmaller(NodoRubroNegro<T> node, T key) {

        if (isNil(node)) return 0;

        else if (key.compareTo(node.key) <= 0)
            return findNumSmaller(node.left, key);

            // se key for maior que node.key, todos os elementos a esquerda são menores e são adicionados
            // daí procura na direirta
        else
            return 1 + node.numLeft + findNumSmaller(node.right, key);

    }

    // @param: node, nodo a ser checado se é nil
    // @return: retorna true se for nil, caso contrário retorna false
    private boolean isNil(NodoRubroNegro node) {
        return node == nil;
    }

    // @return: retorna o tamanho da árvore
    public int size() {

        // soma os elementos da esquerda e os da direita, mais a raiz
        return root.numLeft + root.numRight + 1;
    }

    // @return: retorna true se a árvore está vazia, ou seja se size() é zero, caso contrário retorna false
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem de
     * caminhamento central. Deve chamar um metodo auxiliar recursivo.
     *
     * @return LinkedListOfInteger lista com os elementos da arvore
     */
    public LinkedList positionsCentral() {
        LinkedList lista = new LinkedList();
        if (isEmpty() == false) positionsCentralAux(lista, root);
        return lista;
    }

    private void positionsCentralAux(LinkedList lista, NodoRubroNegro nodo) {
        if (nodo != null && nodo.key != null) {
            positionsCentralAux(lista, nodo.left);
            lista.add(nodo.key);
            positionsCentralAux(lista, nodo.right);
        }
    }
}
