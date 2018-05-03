package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.Entity;

@Entity
public class NewClientSalon extends NewClient{

    private boolean quotation;
    private boolean carRepurchase;
    private boolean finance;
    private boolean standard;

    public NewClientSalon() {
    }

    public boolean isQuotation() {
        return quotation;
    }

    public void setQuotation(boolean quotation) {
        this.quotation = quotation;
    }

    public boolean isCarRepurchase() {
        return carRepurchase;
    }

    public void setCarRepurchase(boolean carRepurchase) {
        this.carRepurchase = carRepurchase;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }
}
