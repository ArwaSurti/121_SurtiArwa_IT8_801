/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author krishnaiya
 */
public class CartItem {
    private ProductMaster product;
    
    private int quantity;
    
    public CartItem(ProductMaster product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }

    public ProductMaster getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(ProductMaster product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalPrice(){
        return product.getPrice()*quantity;
    }
    
}
