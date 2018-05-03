package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class NewClient extends DummyModel {

    private int interest;
    private int marketing;

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getMarketing() {
        return marketing;
    }

    public void setMarketing(int marketing) {
        this.marketing = marketing;
    }
}
