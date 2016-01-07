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
    private String Description;

       public ProductBean (String pid, String pName, Double m, String desc){
           productID = pid;
           productName = pName;
           MSRP = m;
           Description = desc;
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
            return Description;
}
}
