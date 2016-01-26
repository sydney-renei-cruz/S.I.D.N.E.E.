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
    
    private int BranchNum;
    private String ProductID;
    private float BranchDiscountRate;
    private int Stock;
    
    public ProductInventoryBean(int bn, String pid, float bdr, int st){
        BranchNum = bn;
        ProductID = pid;
        BranchDiscountRate = bdr;
        Stock = st;
    }

    public int getBranchNum(){
	return BranchNum;
    }
    
    public String getProductID(){
	return ProductID;
    }

    public float getBranchDiscountRate(){
	return BranchDiscountRate;
    }

    public int getStock(){
	return Stock;
    }
}
