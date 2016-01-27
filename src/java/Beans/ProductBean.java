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
public class ProductBean implements Serializable{
    
    private String productID;
    private String productName;
    private  Double MSRP;
    private String description;
    private Double discountRate;
    
    public ProductBean (){
        productID = null;
        productName = null;
        description = null;
    }
       
    public ProductBean (String pid, String pName){
       productID = pid;
       productName = pName;
    }
       
    public String getProductID(){
        return productID;
    }
    public String getProductName(){
        return productName;
    }
    public  Double getMSRP(){
        return MSRP;
    }
    public String getDescription(){
         return description;
    }
    public Double getDiscountRate(){
         return discountRate;
    }
        
    public void setProductID(String pidin){
        productID = pidin;
    }
    public void setProductName(String pnin){
        productName = pnin;
    }
    public void setMSRP(Double msin){
        MSRP = msin;
    }
    public void setDescription(String descin){
        description = descin;
    }
    public void setDiscountRate(Double discin){
        discountRate = discin;
    }
}
