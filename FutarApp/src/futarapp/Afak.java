/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futarapp;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public enum Afak {
           KiemeltKevezmenyes(1.05),
           Kedvezmenyes(1.18),
           Normal(1.27),
           Uzemanyag(1.4);
        
        private double Afa;
        
        private Afak(double Afa){
            this.Afa = Afa;
        }
        
        public double getAfa(){
            return Afa;
        }
}
