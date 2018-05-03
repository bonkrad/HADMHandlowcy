package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.Entity;

@Entity
public class NewClientTelephone extends NewClient{

    private boolean quotation;
    private boolean meeting;
    private boolean standard;

    public NewClientTelephone() {
    }

    public boolean isQuotation() {
        return quotation;
    }

    public void setQuotation(boolean quotation) {
        this.quotation = quotation;
    }

    public boolean isMeeting() {
        return meeting;
    }

    public void setMeeting(boolean meeting) {
        this.meeting = meeting;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }
}
