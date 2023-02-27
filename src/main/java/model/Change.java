package model;

public class Change<Produit1, Produit2> {

    private Produit1 produit1;
    private Produit2 produit2;

    public Change(Produit1 produit1, Produit2 produit2) {
        this.produit1 = produit1;
        this.produit2 = produit2;
    }

    public Produit1 getProduit1() {
        return produit1;
    }

    public Produit2 getProduit2() {
        return produit2;
    }

}
