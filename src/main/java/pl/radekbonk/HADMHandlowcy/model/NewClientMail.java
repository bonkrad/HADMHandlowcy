package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.Entity;

@Entity
public class NewClientMail extends NewClient{

    private boolean response;
    private boolean standard;

    public NewClientMail() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }
}
