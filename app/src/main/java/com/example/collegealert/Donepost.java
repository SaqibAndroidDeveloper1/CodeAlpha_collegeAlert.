package com.example.collegealert;

public class Donepost {
    String  objective, description,Spinnerdetail,img;


    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpinnerdetail() {
        return Spinnerdetail;
    }

    public void setSpinnerdetail(String spinnerdetail) {
        Spinnerdetail = spinnerdetail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Donepost(String obj, String desc, String spinnerdetail, String img) {

        this.objective = obj;
        this.description = desc;

        this.Spinnerdetail=spinnerdetail;
        this.img=img;


    }

}
