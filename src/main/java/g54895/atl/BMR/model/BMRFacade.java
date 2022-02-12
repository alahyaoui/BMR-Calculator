/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.BMR.model;

/**
 *
 * @author ayoub
 */
public interface BMRFacade {

    /**
     * Method computeBMR computes the BMR of the given values.
     * @param weight
     * @param height
     * @param age
     * @param sexe
     * @return bmr an integer 
     */
    public double computeBMR(int weight, int height, int age, boolean sexe);
    
    /**
     * Setter of the person's data.
     *
     * @param height
     * @param weight
     * @param age
     * @param sexe
     * @param activity
     */
    public void setData(int height, int weight, int age, boolean sexe, Activity activity);
}
