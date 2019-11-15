public class Node {
    private int elemento;
    private Node pai;
    private Node filhoE;
    private Node filhoD;
    private String cor;
    private Boolean duplo;

    public Node() {
    }

    public Node(Node pai, int elemento) {
        this.pai = pai;
        this.elemento = elemento;
        this.cor = "Rubro";
        this.duplo = false;
    }

    public Node getIrmao()
    {
        if(this.ehFilhoD())
            return pai.getFilhoE();
        else
            return pai.getFilhoD();
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public Node getFilhoE() {
        return filhoE;
    }

    public void setFilhoE(Node filhoE) {
        this.filhoE = filhoE;
    }

    public Node getFilhoD() {
        return filhoD;
    }

    public void setFilhoD(Node filhoD) {
        this.filhoD = filhoD;
    }

    //Retorna se o No é raiz (não tem pai)
    public boolean isRoot()
    {
        return this.pai == null;
    }

    public boolean hasFilhoE(){
        return this.filhoE != null;
    }

    public boolean hasFilhoD(){
        return this.filhoD != null;
    }

    //Retorna se o No é interno (tem filho)
    public boolean isInternal()
    {
        return this.filhoE != null || this.filhoD != null;
    }
    //Retorna se o No é externo (não tem filho)
    public boolean isExternal()
    {
        return this.filhoD == null && this.filhoE == null;
    }

    //Retorna se o No é filho esquerdo
    public boolean ehFilhoE()
    {
        return this.elemento <= this.pai.getElemento();
    }

    //Retorna se o No é filho direito
    public boolean ehFilhoD()
    {
        return this.elemento >= this.pai.getElemento();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getDuplo() {
        return duplo;
    }

    public void setDuplo(Boolean duplo) {
        this.duplo = duplo;
    }

}