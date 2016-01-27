/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Beans.*;

/**
 *
 * @author host
 */
public class BeanUtils {
    public static ProductBean createProductBean(String productID, String productName, double MSRP, String description, double discountRate){
        ProductBean pb = new ProductBean();
        pb.setProductID(productID);
        pb.setProductName(productName);
        pb.setMSRP(MSRP);
        pb.setDiscountRate(discountRate);
        pb.setDescription(description);
        return pb;
    }
    public static ProductBean createPartialProductBean(String pID, String pName){
        ProductBean ppb = new ProductBean();
        ppb.setProductID(pID);
        ppb.setProductName(pName);
        return ppb;
    }
    
    public static BranchBean createBranchBean(String bNum, String bName, String bAdd, String bPNum){
        BranchBean bb = new BranchBean();
        bb.setBranchNum(bNum);
        bb.setBranchName(bName);
        bb.setBranchAddress(bAdd);
        bb.setBranchPhoneNum(bPNum);
        
        return bb;
    }
    
    public static ProductInventoryBean createProductInventoryBean(String branchNum, String productID, float branchDiscountRate, int stock){
        ProductInventoryBean pib = new ProductInventoryBean();
        pib.setBranchNum(branchNum);
        pib.setProductID(productID);
        pib.setBranchDiscountRate(branchDiscountRate);
        pib.setStock(stock);
        return pib;
    }
}
