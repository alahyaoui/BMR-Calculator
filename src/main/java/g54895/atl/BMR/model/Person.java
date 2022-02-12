/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.BMR.model;

import g54895.atl.BMR.model.Activity;

/**
 *
 * @author ayoub
 */
public class Person {

    private int height;
    private int weight;
    private int age;
    private boolean sexe; //0 for a woman ,1 for a man
    private Activity activity;

    /**
     * Simple constructor of Person.
     *
     * @param taille
     * @param poids
     * @param age
     * @param sexe
     * @param activity
     */
    public Person(int height, int weight, int age, boolean sexe, Activity activity) {
        if (height > 0 && weight > 0 && age > 0) {
            this.height = height;
            this.weight = weight;
            this.age = age;
            this.sexe = sexe;
            this.activity = activity;
        } else {
            throw new IllegalArgumentException("Veuillez ne pas entrez de valeur nuls ou negatives !!!");
        }
    }

    /**
     * Simple default constructor of Person.
     */
    public Person() {
        this(175, 70, 18, false, Activity.SEDENTARY);
    }

    /**
     * Simple getter of Taille.
     *
     * @return taille
     */
    public int getHeight() {
        return height;
    }

    /**
     * Simple getter of Poids
     *
     * @return poids
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Simple getter of Age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Simple getter of sexe, false is not a man true otherwise
     *
     * @return sexe
     */
    public boolean isMan() {
        return sexe;
    }

    /**
     * Simple getter of Activity
     *
     * @return
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Simple setter of Taille.
     *
     * @param height
     */
    public void setHeight(int taille) {
        this.height = taille;
    }

    /**
     * Simple setter of weight.
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = this.weight;
    }

    /**
     * Simple setter of age.
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Simple setter of gender, 0 for woman , 1 for man.
     *
     * @param sexe
     */
    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    /**
     * Simple setter of the person's activity.
     *
     * @param activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
