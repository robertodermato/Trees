public class ArvoreRN {
    private Node raiz;
    private int qtd;

    public void setRaiz(Node no)
    {
        raiz = no;
        raiz.setCor("Negro");
    }

    public Node getRaiz()
    {
        return raiz;
    }

    public Node find(int key) {
        return pesquisar(raiz, key);
    }

    public Node pesquisar(Node no, int key) {
        //Verifica se o no não tem filho
        if (no.isExternal())
        {
            return no;
        }
        //Verifica se achou o no
        if (no.getElemento() == key)
        {
            return no;
        }
        //Verifica se o no que procura esta do lado esquerdo
        else if ((int) key < (int) no.getElemento())
        {
            return pesquisar(no.getFilhoE(), key);
        }
        //Verifica se o no que procura esta do lado direito
        else//((int)key > (int)no.getElemento())
        {
            return pesquisar(no.getFilhoD(), key);
        }
    }

    private Node buscarPai(Node no, int key) {
        //Verifica se o no não tem filho
        if (no.isExternal()) {
            return no;
        }
        //Verifica se o no que procura esta do lado esquerdo
        if ((int) key < (int) no.getElemento())
        {
            if(no.getFilhoE() == null)
                return no;
            else
                return buscarPai(no.getFilhoE(), key);
        }
        //Verifica se o no que procura esta do lado direito
        else
        {
            if(no.getFilhoD() == null)
                return no;
            else
                return buscarPai(no.getFilhoD(), key);
        }
    }

    public Node incluir(int key) {
        //Busca o pai do no que vai ser inserido
        Node pai = buscarPai(raiz, key);
        //Cria o novo no
        Node novo = new Node(pai, key);

        //Verifica se o novo no eh filho esquerdo do pai buscado
        if ((int) pai.getElemento() > (int) novo.getElemento())
            pai.setFilhoE(novo);
            //Verifica se o novo no eh filho direito do pai buscado
        else
            pai.setFilhoD(novo);
        qtd++;

        AtualizarCores(novo);

        return novo;
    }

    private Node AtualizarCores(Node no)
    {
        Node pai = no.getPai();
        if(pai.getCor().equals("Rubro"))
        {
            Node tio = null;
            //se pai é filho direito então tio é filho esquerdo de vô
            if(pai.ehFilhoD())
                tio = pai.getPai().getFilhoE();
            else
                tio = pai.getPai().getFilhoD();

            //tio eh negro
            if(tio == null || tio.getCor().equals("Negro"))
            {
                System.out.println("Incluir: Situação 3 no nó: " + no.getElemento());
                no = Rotacionar(no);
            }
            //se o tio eh rubro
            else
            {
                System.out.println("Incluir: Situação 2 no nó:" + no.getElemento());
                tio.setCor("Negro");
                pai.setCor("Negro");
                if(!pai.getPai().isRoot()){
                    pai.getPai().setCor("Rubro");
                    no = AtualizarCores(no.getPai().getPai());
                }
            }
        }

        return no;
    }

    private Node Rotacionar(Node no)
    {
        Node retorno = null;
        Node avo = no.getPai().getPai();
        Node pai = no.getPai();

        //Rotacao simples a direita
        if(no.ehFilhoE() && pai.ehFilhoE())
        {
            retorno = no.getPai();
            avo.setCor("Rubro");
            pai.setCor("Negro");

            RotacaoSimplesDireita(avo);
        }
        //Rotacao simples a esquerda
        else if(no.ehFilhoD() && pai.ehFilhoD())
        {
            retorno = no.getPai();
            avo.setCor("Rubro");
            pai.setCor("Negro");

            RotacaoSimplesEsquerda(avo);

        }
        //Rotacao dupla esquerda
        else if(pai.ehFilhoD())
        {
            retorno = no;
            avo.setCor("Rubro");
            no.setCor("Negro");

            RotacaoDuplaEsquerda(avo);
        }
        //Rotacao dupla direita
        else
        {
            retorno = no;
            avo.setCor("Rubro");
            no.setCor("Negro");

            RotacaoDuplaDireita(avo);
        }

        return retorno;
    }

    public Node Sucessor(Node no) {
        //Verifica se eh folha ou se nao tem filho esquerdo --> encontrou o menor filho do no a remover
        if (no.isExternal() || no.getFilhoE() == null)
            return no;
            //Continua a procura a apartir do filho esquerdo
        else
            return Sucessor(no.getFilhoE());
    }

    public Object remover(int key) {
        if(remover(raiz, key) != null)
            qtd--;
        return null;
    }

    public Object remover(Node root, int key) {
        //Busca a chave na arvore
        Node r = pesquisar(root, key);
        //Visite(r);
        //Verifica se o no esta na arvore
        if (r != null && ((int)r.getElemento() == (int)key)) {
            //verifica se o no eh folha
            //System.out.println("oii");
            if (r.isExternal()) {
                //System.out.println("e aqui");
                //No a ser removido eh negro (sucessor negro) entao segue para a situação 3
                if(r.getCor().equals("Negro")){
                    Situacao3(r);
                }
                //No a ser removido eh rubro então pode seguir normalmente

                //Removendo...
                //verifica se o no eh o filho direito
                if (r.ehFilhoD())
                {
                    //System.out.println("aqui");
                    r.getPai().setFilhoD(null);
                    r.setPai(null);
                }
                //o no eh filho esquerdo
                else
                {
                    //System.out.println("nao eh aqui");
                    r.getPai().setFilhoE(null);
                    r.setPai(null);
                }
            }
            //Verifica se o no a remover tem 1 filho e eh filho direito
            else if (r.getFilhoD() != null && r.getFilhoE() == null) {
                Node sucessor = r.getFilhoD();

                //Sucessor eh rubro
                //Acontece nada de mais

                //Sucessor eh negro
                if(sucessor.getCor().equals("Negro"))
                {
                    if(r.getCor().equals("Negro"))
                    {
                        Situacao3(sucessor);
                    }
                    else
                    {
                        sucessor.setCor("Rubro");
                        Situacao3(sucessor);
                    }
                }

                r.setElemento(sucessor.getElemento());
                //sucessor.setElemento(key);

                remover(r.getFilhoD(), sucessor.getElemento());
            }
            //Verifica se o no tem 1 filho e eh filho esquerdo
            else if (r.getFilhoE() != null && r.getFilhoD() == null) {
                Node sucessor = r.getFilhoE();

                //Sucessor eh rubro
                //Acontece nada de mais

                //Sucessor eh negro
                if(sucessor.getCor().equals("Negro"))
                {
                    if(r.getCor().equals("Negro"))
                    {
                        Situacao3(sucessor);
                    }
                    else
                    {
                        sucessor.setCor("Rubro");
                        Situacao3(sucessor);
                    }
                }
                r.setElemento(sucessor.getElemento());
                remover(r.getFilhoE(), sucessor.getElemento());
            }
            //O no a remover tem 2 filhos
            else
            {
                //Acha o sucessor
                Node herdeiro = Sucessor(r.getFilhoD());

                //Sucessor eh rubro
                //Acontece nada de mais
                //Sucessor eh negro
                if(herdeiro.getCor().equals("Negro"))
                {
                    //no negro e sucessor negro
                    //situação 3
                    if(r.getCor().equals("Negro"))
                    {
                        Situacao3(herdeiro);
                    }
                    //no rubro e sucessor negro
                    //situação 4
                    else
                    {
                        System.out.println("Situação 4");
                        herdeiro.setCor("Rubro");
                        Situacao3(herdeiro);
                    }
                }

                r.setElemento(herdeiro.getElemento());

                remover(r.getFilhoD(), herdeiro.getElemento());

            }
            return r;
        }
        return null;
    }

    public void Situacao3(Node no)
    {
        //caso 1
        if(no.getIrmao().getCor().equals("Rubro"))
        {
            System.out.println("Caso 1");
            if(no.getIrmao().ehFilhoD())
            {
                no.getIrmao().setCor("Negro");
                if(!no.getPai().isRoot())
                    no.getPai().setCor("Rubro");

                RotacaoSimplesEsquerda(no.getPai());
                Situacao3(no);
            }
        }
        //caso 2B
        else if(no.getPai().getCor().equals("Rubro"))
        {
            System.out.println("Caso 2B");
            no.getPai().setCor("Negro");
            no.getIrmao().setCor("Rubro");
        }
        //caso 3 para no esquerdo
        else if(no.ehFilhoE() && no.getIrmao().getFilhoE().getCor().equals("Rubro") && no.getIrmao().getFilhoD().getCor().equals("Negro"))
        {
            System.out.println("Caso 3");
            no.getIrmao().getFilhoE().setCor("Negro");
            no.getIrmao().setCor("Rubro");
            RotacaoSimplesDireita(no.getIrmao());
            Situacao3(no);
        }
        //caso 3 para no direito (espelhado)
        else if(no.ehFilhoE() && no.getIrmao().getFilhoD().getCor().equals("Rubro") && no.getIrmao().getFilhoE().getCor().equals("Negro"))
        {
            System.out.println("Caso 3'");
            no.getIrmao().getFilhoD().setCor("Negro");
            no.getIrmao().setCor("Rubro");
            RotacaoSimplesEsquerda(no.getIrmao());
            Situacao3(no);
        }
        //caso 4 para no esquerdo
        else if(no.ehFilhoE() && no.getIrmao().getFilhoD().getCor().equals("Rubro"))
        {
            System.out.println("Caso 4");
            no.getIrmao().setCor(no.getPai().getCor());
            no.getPai().setCor("Negro");
            no.getIrmao().getFilhoD().setCor("Negro");
            RotacaoSimplesEsquerda(no.getPai());
        }
        //caso 4 para no direito (espelhado)
        else if(no.ehFilhoD() && no.getIrmao().getFilhoE().getCor().equals("Rubro"))
        {
            System.out.println("Caso 4_");
            no.getIrmao().setCor(no.getPai().getCor());
            no.getPai().setCor("Negro");
            no.getIrmao().getFilhoE().setCor("Negro");
            RotacaoSimplesDireita(no.getPai());
        }
        //caso 2A
        else
        {
            System.out.println("Caso 2A");
            no.getIrmao().setCor("Rubro");
            Situacao3(no.getPai());
        }
    }

    public void RotacaoSimplesEsquerda(Node no) {
        System.out.println("Rotacao Simples Esquerda " + no.getElemento());

        Node netoE = null;

        //se necessario, atualiza a raiz
        if(no.isRoot())
            raiz = no.getFilhoD();

        //guarda o netoE e atualiza suas referencia para o pai
        if(no.getFilhoD().getFilhoE() != null) {
            netoE = no.getFilhoD().getFilhoE();
            netoE.setPai(no);
        }

        //Atualiza as referencias do filho direito do no
        no.getFilhoD().setPai(no.getPai());
        no.getFilhoD().setFilhoE(no);

        //Atualiza as referencias do pai do no se existir
        if(no.getPai() != null) {
            if(no.getElemento() > no.getPai().getElemento())
                no.getPai().setFilhoD(no.getFilhoD());
            else
                no.getPai().setFilhoE(no.getFilhoD());
        }

        //Atualiza as referencias do no
        no.setPai(no.getFilhoD());
        //if(netoE != null)
        no.setFilhoD(netoE);
        //else
        //   no.setFilhoD(null);

        //exibirArvore(raiz);
    }

    public void RotacaoSimplesDireita(Node no) {
        System.out.println("Rotacao Simples Direita " + no.getElemento());

        Node netoD = null;

        //se necessario, atuliza a raiz
        if(no.isRoot())
            raiz = no.getFilhoE();

        //guarda o netoD
        if(no.getFilhoE().getFilhoD() != null) {
            netoD = no.getFilhoE().getFilhoD();
            netoD.setPai(no);
        }

        //Atualiza as referencias do filho esquerdo do no
        no.getFilhoE().setPai(no.getPai());
        no.getFilhoE().setFilhoD(no);

        //Atualiza as referencias do pai do no, se existir
        if(no.getPai() != null){
            if(no.getElemento() > no.getPai().getElemento())
                no.getPai().setFilhoD(no.getFilhoE());
            else
                no.getPai().setFilhoE(no.getFilhoE());
        }

        //Atualiza as referencias do no
        no.setPai(no.getFilhoE());
        //if(netoD != null)
        no.setFilhoE(netoD);
        //else
        //    no.setFilhoE(null);

        //exibirArvore(raiz);
    }

    public void RotacaoDuplaEsquerda(Node no) {
        RotacaoSimplesDireita(no.getFilhoD());
        RotacaoSimplesEsquerda(no);
    }

    public void RotacaoDuplaDireita(Node no) {
        RotacaoSimplesEsquerda(no.getFilhoE());
        RotacaoSimplesDireita(no);
    }

    //Metodo que mostra as caracteristicas do no *OBS: Faz a verificações para não dar erro de referencia nula
    public void Visite(Node n) {
        System.out.print("Elemento:" + n.getElemento() + " Cor: " + n.getCor());
        if (!n.isRoot())
            System.out.print(" Pai:" + n.getPai().getElemento());

        if (n.getFilhoE() != null)
            System.out.print(" FilhoE:" + n.getFilhoE().getElemento());

        if (n.getFilhoD() != null)
            System.out.print(" FilhoD:" + n.getFilhoD().getElemento());

        System.out.println();
    }

    //Metodo para visualizar a arvore --> Algoritmo InOrder
    public void exibirArvore(Node n) {
        if (n.isInternal() && n.getFilhoE() != null) {
            exibirArvore(n.getFilhoE());
        }

        Visite(n);

        if (n.isInternal() && n.getFilhoD() != null) {
            exibirArvore(n.getFilhoD());
        }
    }

}
