/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author host
 */



public class ProductInventoryBean implements Serializable{
    
    private String branchNum;
    private String productID;
    private float branchDiscountRate;
    private int stock;

    public ProductInventoryBean(){
        branchNum = null;
        productID = null;
    }
    
    public String getBranchNum(){
	return branchNum;
    }
    public String getProductID(){
	return productID;
    }
    public float getBranchDiscountRate(){
	return branchDiscountRate;
    }
    public int getStock(){
	return stock;
    }
    
    public void setBranchNum(String bnumin){
        branchNum = bnumin;
    }
    public void setProductID(String pidin){
        productID = pidin;
    }
    public void setBranchDiscountRate(float bdrin){
        branchDiscountRate = bdrin;
    }
    public void setStock(int stkin){
        stock = stkin;
    }
}
