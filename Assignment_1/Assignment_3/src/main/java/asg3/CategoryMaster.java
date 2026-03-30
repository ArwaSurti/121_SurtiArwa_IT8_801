/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asg3;

/**
 *
 * @author krishnaiya
 */
public class CategoryMaster {
    Integer category_id;
    String category_name;
    
    public CategoryMaster(int cid,String cname){
        this.category_id=cid;
        this.category_name=cname;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }
    
}
