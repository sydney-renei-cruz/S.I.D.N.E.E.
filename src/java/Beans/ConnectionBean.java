/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author host
 */
public class ConnectionBean implements java.io.Serializable{
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement ps;
    private Connection conn;
    private boolean status;
    private Exception ex;
    
    public ConnectionBean(){
        rs = null;
        stmt = null;
        ps = null;
        conn = null;
        status = false;
    }
    
    public void setRS(ResultSet rin){
        rs = rin;
    }
    
    public void setStmt(Statement sin){
        stmt = sin;
    }
    
    public void setPS(PreparedStatement pin){
        ps = pin;
    }
    
    public void setConn(Connection cin){
        conn = cin;
    }
    
    public void setStatus(boolean stin){
        status = stin;
    }
    
    public void setEx(Exception ein){
        ex = ein;
    }
    
    
    public ResultSet getRS(){
        return rs;
    }
    
    public Statement getStmt(){
        return stmt;
    }
    
    public PreparedStatement getPS(){
        return ps;
    }
    
    public Connection getConn(){
        return conn;
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public Exception getEx(){
        return ex;
    }
    
    public boolean close(){
        boolean flag = false;
        try{
            if(rs!=null){
                rs.close();
                flag = true;
            }
            if(stmt!=null){
                stmt.close();
                flag = true;
            }
            if(ps!=null){
                ps.close();
                flag = true;
            }if(conn!=null){
                conn.close();
                flag = true;
            }
            return flag;
        }
        catch(Exception ex){
            return false;
        }
    }
}
