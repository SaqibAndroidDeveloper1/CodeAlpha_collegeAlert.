package com.example.collegealert;

public class KarachiModel {

     String objective,description,Spinnerdetail,img;
    KarachiModel(){


    }

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



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpinnerdetail() {
        return Spinnerdetail;
    }

    public void setSpinnerdetail(String spinnerdetail) {
        Spinnerdetail = spinnerdetail;
    }

    public KarachiModel(String objective, String description, String Spinnerdetail, String img ){

          this.objective=objective;
          this.description=description;
        this.Spinnerdetail=Spinnerdetail;
           this.img=img;

     }


}
