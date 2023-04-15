/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;

/**
 *
 * @author alanm
 */
public class Casilla {
     private int x;
     private int y;
     private boolean Mina;
     private int NumsMinaAlrededor;
     private boolean Abierta;
     private boolean Bandera;

    public boolean isAbierta() {
        return Abierta;
    }

    public void setAbierta(boolean Abierta) {
        this.Abierta = Abierta;
    }
     
    

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getNumsMinaAlrededor() {
        return NumsMinaAlrededor;
    }

    public void setNumsMinaAlrededor(int NumsMinaAlrededor) {
        this.NumsMinaAlrededor = NumsMinaAlrededor;
    }
    public void IncrementarNumMinasAlrededor(){
        NumsMinaAlrededor++;
    }
            

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMina() {
        return Mina;
    }

    public void setMina(boolean flag) {
        this.Mina = flag;
    }

    public boolean isBandera() {
        return Bandera;
    }

    public void setBandera(boolean Bandera) {
        this.Bandera = Bandera;
    }
     
     
    
    
}
