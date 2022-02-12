/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.BMR.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.scene.control.TextField;

/**
 * Class Calculator is a calculator of a person's BMR and calories
 *
 * @author ayoub
 */
public final class Calculator implements BMRFacade {

    private Person person;
    private double bmr;
    private double calories;

    private PropertyChangeSupport pcs;

    /**
     * Simple constructor of Calulator.
     *
     * @param person
     */
    public Calculator() {
        this.person = new Person();
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * update the person's BMR by multiplying the person's attributes with
     * numbers.
     *
     * @return bmr.
     */
    private void updateBMR() {
        if (person.isMan()) {
            bmr = 13.7 * person.getWeight() + 5 * person.getHeight() - 6.8 * person.getAge() + 66;
        } else {
            bmr = 9.6 * person.getWeight() + 1.8 * person.getHeight() - 4.7 * person.getAge() + 655;
        }
    }

    /**
     * Computes the bmr of the given datas.
     *
     * @param weight
     * @param height
     * @param age
     * @param sexe
     * @return bmr
     */
    public double computeBMR(int weight, int height, int age, boolean sexe) {
        double bmr = 0;
        if (sexe) {
            bmr = 13.7 * weight + 5 * height - 6.8 * age + 66;
        } else {
            bmr = 9.6 * weight + 1.8 * height - 4.7 * age + 655;
        }
        return bmr;
    }

    /**
     * update person's required minimum calories for a day by multiplying the
     * BMR with an number depending on the person's lifestyle.
     *
     * @return calories
     */
    private void updateCalories() {
        if (bmr > 0) {
            switch (person.getActivity()) {
                case SEDENTARY:
                    calories = bmr * 1.2;
                    break;
                case NOT_ACTIVE:
                    calories = bmr * 1.375;
                    break;
                case ACTIVE:
                    calories = bmr * 1.55;
                    break;
                case STRONG_ACTIVE:
                    calories = bmr * 1.725;
                    break;
                case EXTREMELY_ACTIVE:
                    calories = bmr * 1.9;
                    break;
                default:
                    calories = bmr * 1.2;
            }
        }
    }

    /**
     * computes the calories per day of the given datas.
     *
     * @param bmr
     * @return calories
     */
    public double computeCalories(double bmr) {
        double calories = 0;
        if (bmr > 0) {
            switch (person.getActivity()) {
                case SEDENTARY:
                    calories = bmr * 1.2;
                    break;
                case NOT_ACTIVE:
                    calories = bmr * 1.375;
                    break;
                case ACTIVE:
                    calories = bmr * 1.55;
                    break;
                case STRONG_ACTIVE:
                    calories = bmr * 1.725;
                    break;
                case EXTREMELY_ACTIVE:
                    calories = bmr * 1.9;
                    break;
                default:
                    calories = bmr * 1.2;
            }
        }
        return calories;
    }

    /**
     * Sets the data of the person
     *
     * @param height
     * @param weight
     * @param age
     * @param sexe
     * @param activity
     */
    public void setData(int height, int weight, int age, boolean sexe, Activity activity) {
        this.person.setActivity(activity);
        this.person.setAge(age);
        this.person.setHeight(height);
        this.person.setSexe(sexe);
        this.person.setWeight(weight);
    }

    /**
     * Method change , changes the state of the observers
     */
    public void change() {
        var oldBMR = bmr;
        var oldCalories = calories;

        //Updates the bmr and the calories
        updateBMR();
        updateCalories();

        pcs.firePropertyChange("Bmr", oldBMR, this.bmr);
        pcs.firePropertyChange("Calories", oldCalories, this.calories);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

}
