package com.pascal.sensortestapp.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SensorData {
    /* Cette classe represente un objet json au format java. C'est la conversion d'un JSON en JAVA

    //Exemple:
         //   En Json
            [ {
            "valeur 1": " 10"
            "valeur 2" : "20"
            },
            {...
            }
            ]

            //en java

            public nomDeClasse {
                private valeur1 =10;
                private valeur 2 =20;

                //pas de constructeur
                //Getters
                string getValeur1(){
                    return valeur1;
                    }
                string {

                }
                //Setters
            }
    *
     */
    @SerializedName("#group")
    @Expose
    private String group;
    @SerializedName("false")
    @Expose
    private String _false;
    @SerializedName("false__1")
    @Expose
    private String false1;
    @SerializedName("true")
    @Expose
    private String _true;
    @SerializedName("true__1")
    @Expose
    private String true1;
    @SerializedName("false__2")
    @Expose
    private String false2;
    @SerializedName("false__3")
    @Expose
    private String false3;
    @SerializedName("true__2")
    @Expose
    private String true2;
    @SerializedName("true__3")
    @Expose
    private String true3;
    @SerializedName("true__4")
    @Expose
    private String true4;
    @SerializedName("true__5")
    @Expose
    private String true5;
    @SerializedName("true__6")
    @Expose
    private String true6;
    @SerializedName("true__7")
    @Expose
    private String true7;
    @SerializedName("true__8")
    @Expose
    private String true8;
    @SerializedName("true__9")
    @Expose
    private String true9;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFalse() {
        return _false;
    }

    public void setFalse(String _false) {
        this._false = _false;
    }

    public String getFalse1() {
        return false1;
    }

    public void setFalse1(String false1) {
        this.false1 = false1;
    }

    public String getTrue() {
        return _true;
    }

    public void setTrue(String _true) {
        this._true = _true;
    }

    public String getTrue1() {
        return true1;
    }

    public void setTrue1(String true1) {
        this.true1 = true1;
    }

    public String getFalse2() {
        return false2;
    }

    public void setFalse2(String false2) {
        this.false2 = false2;
    }

    public String getFalse3() {
        return false3;
    }

    public void setFalse3(String false3) {
        this.false3 = false3;
    }

    public String getTrue2() {
        return true2;
    }

    public void setTrue2(String true2) {
        this.true2 = true2;
    }

    public String getTrue3() {
        return true3;
    }

    public void setTrue3(String true3) {
        this.true3 = true3;
    }

    public String getTrue4() {
        return true4;
    }

    public void setTrue4(String true4) {
        this.true4 = true4;
    }

    public String getTrue5() {
        return true5;
    }

    public void setTrue5(String true5) {
        this.true5 = true5;
    }

    public String getTrue6() {
        return true6;
    }

    public void setTrue6(String true6) {
        this.true6 = true6;
    }

    public String getTrue7() {
        return true7;
    }

    public void setTrue7(String true7) {
        this.true7 = true7;
    }

    public String getTrue8() {
        return true8;
    }

    public void setTrue8(String true8) {
        this.true8 = true8;
    }

    public String getTrue9() {
        return true9;
    }

    public void setTrue9(String true9) {
        this.true9 = true9;
    }
}
