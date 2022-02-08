package com.example.e_wasteapp;

public class Product_info {
    String type_p,user_name,condition,ph_no,brand,model;
    int id,quantity;
    double price;

    public Product_info(){

    }

    public Product_info(String type_p,String user_name,String condition,String ph_no,String brand,String model,int quantity,int id,double price){
        this.type_p=type_p;
        this.user_name=user_name;
        this.condition=condition;
        this.ph_no=ph_no;
        this.brand=brand;
        this.model=model;
        this.quantity=quantity;
        this.id=id;
        this.price=price;
    }

    public String getUser_name(){
        return user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPh_no() {
        return ph_no;
    }

    public String getType_p(){
        return type_p;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getModel() {
        return model;
    }

    public String getCondition() {
        return condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public void setUser_name(String user_name){
        this.user_name=user_name;
    }

    public void setType_p(String type_p){
        this.type_p=type_p;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
