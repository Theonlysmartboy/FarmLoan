package com.cybene.farmloan.utils.items;

public class Detail {
    private int id, status,idno;
    private String name, ppn, spn, email,bt,loc,mrev,created_at;

    public Detail(int id, String name, String ppn, String spn, String email, int idno, String bt, String loc, String mrev, String created_at, int status) {
        this.id = id;
        this.name = name;
        this.ppn = ppn;
        this.spn = spn;
        this.email = email;
        this.idno = idno;
        this.bt = bt;
        this.loc = loc;
        this.mrev = mrev;
        this.created_at = created_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public int getIdno() {
        return idno;
    }

    public String getName() {
        return name;
    }

    public String getPpn() {
        return ppn;
    }

    public String getSpn() {
        return spn;
    }

    public String getEmail() {
        return email;
    }

    public String getBt() {
        return bt;
    }

    public String getLoc() {
        return loc;
    }

    public String getMrev() {
        return mrev;
    }

    public String getCreated_at() {
        return created_at;
    }
}
