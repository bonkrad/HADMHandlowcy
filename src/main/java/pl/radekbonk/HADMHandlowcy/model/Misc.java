package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class Misc extends DummyModel{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
