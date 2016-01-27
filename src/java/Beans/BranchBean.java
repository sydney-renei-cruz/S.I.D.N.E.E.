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
public class BranchBean implements Serializable{
    private String branchNum;
    private String branchName;
    private String branchAddress;
    private String branchPhoneNum;
    
    public BranchBean(){
        branchNum = null;
        branchName = null;
        branchAddress = null;
        branchPhoneNum = null;
    }
    
    public String getBranchNum(){
	return branchNum;
    }

    public String getBranchName(){
	return branchName;
    }

    public String getBranchAddress(){
	return branchAddress;
    }

    public String getBranchPhoneNum(){
	return branchPhoneNum;
    }
    
    public void setBranchNum(String bnin){
        branchNum = bnin;  
    }
    public void setBranchName(String bnamin){
        branchName = bnamin;  
    }
    public void setBranchAddress(String baddin){
        branchAddress = baddin;  
    }
    public void setBranchPhoneNum(String bpin){
        branchPhoneNum = bpin;
    }
}
