//Um Deque é uma estrutura de dados similar a uma fila que suporta inserção e remoção
//tanto em seu final, quanto em seu início, e possui os métodos listados abaixo. Implemente
//um deque usando duas pilhas como atributos para armazenar os seus elementos. Depois
//teste se a sua implementação está correta instanciando e manipulando um deque.

import java.util.ArrayList;

public class Deque {

    int tamanho;
    ArrayStack cabeca;
    ArrayStack cauda;

    public Deque() {
        tamanho = 0;
        cabeca = new ArrayStack();
        cauda = new ArrayStack();
    }

    //insere o elemento e no começo do deque.
    public void addFirst(Integer e) {
        cabeca.push(e);
        tamanho++;
    }

    //insere o elemento e no final do deque.
    public void addLast(Integer e) {
        cauda.push(e);
        tamanho++;
    }

    // remove e retorna o elemento e do início do deque, e ocorre um erro se o deque estiver vazio.
    public Integer removeFirst(){
        if (tamanho==0) throw new IllegalArgumentException("Deque vazio. Não é possível remover!");
        Integer e=null;
        int tam = tamanho;
        if (cabeca.size()>0) {
            e = cabeca.pop();
            tamanho--;
        }
        else {
            for (int i=0; i<tam;i++){
                cabeca.push(cauda.pop());
            }
            e = cabeca.pop();
            tamanho--;
            for(int i=0; i<tam-1; i++){
                cauda.push(cabeca.pop());
            }
        }
        return e;
    }

    // remove e retorna o elemento e do final do deque, e ocorre um erro se o deque estiver vazio.
    public Integer removeLast(){
        if (tamanho==0) throw new IllegalArgumentException("Deque vazio");
        Integer e=null;
        int tam = tamanho;
        if (cauda.size()>0) {
            e = cauda.pop();
            tamanho--;
        }
        else {
            for (int i=0; i<tam;i++){
                cauda.push(cabeca.pop());
            }
            e = cauda.pop();
            tamanho--;
            for(int i=0; i<tam-1; i++){
                cabeca.push(cauda.pop());
            }
        }
        return e;
    }

    // retorna, mas não remove, o primeiro elemento do deque, e ocorre um erro se o deque estiver vazio.
    public Integer getFirst(){
        if (tamanho==0) throw new IllegalArgumentException("Deque vazio. Não é possível acessar o deque.");
        Integer e=null;
        int tam = tamanho;
        if (cabeca.size()>0) {
            e = cabeca.top();
        }
        else {
            for (int i=0; i<tam;i++){
                cabeca.push(cauda.pop());
            }
            e = cabeca.top();

            for(int i=0; i<tam; i++){
                cauda.push(cabeca.pop());
            }
        }
        return e;

    }

    // retorna, mas não remove, o último elemento do deque, e ocorre um erro se o deque estiver vazio.
    public Integer getLast(){
        if (tamanho==0) throw new IllegalArgumentException("Deque vazio");
        Integer e=null;
        int tam = tamanho;
        if (cauda.size()>0) {
            e = cauda.top();
        }
        else {
            for (int i=0; i<tam;i++){
                cauda.push(cabeca.pop());
            }
            e = cauda.top();

            for(int i=0; i<tam; i++){
                cabeca.push(cauda.pop());
            }
        }
        return e;
    }

    //retorna o número de elementos da fila.
    public int size() {
        return tamanho;
    }

    //retorna true se a fila estiver vazia, e false caso contrário
    public boolean isEmpty() {
        return tamanho == 0;
    }


}