/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pos.mvc;
import pos.mvc.view.CustomerView;
import pos.mvc.view.OrderFrame;
/**
 *
 * @author Nethmi
 */
public class PosMvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello");
        //new CustomerView().setVisible(true);
        new OrderFrame().setVisible(true);
        
    }
    
}
