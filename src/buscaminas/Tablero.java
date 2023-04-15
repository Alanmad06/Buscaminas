/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class Tablero {

    private Casilla[][] Casillas;

    private int numFilas;
    private int numColumnas;
    private int Minas;
    private int Banderas;
    
    private Consumer<List<Casilla>> PartidaPerdida;
    private Consumer<Casilla> CasillaAbierta;
    private Consumer<Casilla> CasillaBandera;
    private Consumer PartidaGanada;

    public Tablero(int numFilas, int numColumnas, int Minas , int Banderas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.Minas = Minas;
        this.Banderas = Banderas;
        InicializarCasillas();
    }

    public int getBanderas() {
        return Banderas;
    }

    public void setBanderas(int Banderas) {
        this.Banderas = Banderas;
    }
    
    

    public void InicializarCasillas() {
        Casillas = new Casilla[this.numFilas][this.numColumnas];

        for (int i = 0; i < Casillas.length; i++) {
            for (int j = 0; j < Casillas[i].length; j++) {

                Casillas[i][j] = new Casilla(i, j);
            }

        }
        GeneracionMinas();

    }
    public void EventoParaPartidaGanada(int posFila , int posCol){
        
        List<Casilla> BanderasPos = new LinkedList();
         List<Casilla> MinasPos = new LinkedList();
         int contIguales=0;
        
        if(Casillas[posFila][posCol].isBandera()){
        Casillas[posFila][posCol].setBandera(false);
        setBanderas(getBanderas()+1);
        }else{
        Casillas[posFila][posCol].setBandera(true);
        setBanderas(getBanderas()-1);
        }
        
        
    CasillaBandera.accept(this.Casillas[posFila][posCol]);
        if (getBanderas() == 0) {
              
                for (int i = 0; i < Casillas.length; i++) {
                    for (int j = 0; j < Casillas[i].length; j++) {

                        if (Casillas[i][j].isBandera()) {

                            BanderasPos.add(Casillas[i][j]);

                        }

                    }

                }
            
           
               
                for (int i = 0; i < Casillas.length; i++) {
                    for (int j = 0; j < Casillas[i].length; j++) {

                        if (Casillas[i][j].isMina()) {

                            MinasPos.add(Casillas[i][j]);

                        }

                    }

                }
            
            
            for(int i = 0 ; i< MinasPos.size();i++){
                
                    if((MinasPos.get(i).getX() == BanderasPos.get(i).getX()) && (MinasPos.get(i).getY() == BanderasPos.get(i).getY())){
                        contIguales++;
                        if(contIguales==Minas){
                            PartidaGanada.accept(null);
                            System.out.println("Ganaste");
                    return ;
                        }
                    
                    
                
                }
                
                
                    
                
            }
        }

       
            
            
        
    }
    public void AbrirCasilla(int posFila , int posCol){
        Casillas[posFila][posCol].setAbierta(true);
    }
            
    public void EventosCasillas(int posFila , int posCol){
        
        CasillaAbierta.accept(this.Casillas[posFila][posCol]);
       
        if(this.Casillas[posFila][posCol].isMina()){
            List<Casilla> MinasPos = new LinkedList();
            for (int i = 0; i < Casillas.length; i++) {
            for (int j = 0; j < Casillas[i].length; j++) {

                if (Casillas[i][j].isMina()) {
                    
                    MinasPos.add(Casillas[i][j]);

                }

            }

        }
            PartidaPerdida.accept(MinasPos);
        }else if(this.Casillas[posFila][posCol].getNumsMinaAlrededor()==0){
            List<Casilla> CasillasAlrededor = obtenerNumMinasAlrededor(posFila,posCol);
            for(Casilla Casilla1 : CasillasAlrededor){
                if(!Casilla1.isAbierta()){
                Casilla1.setAbierta(true);
                EventosCasillas(Casilla1.getX() , Casilla1.getY());
            }
            }
            
            
            
        }
        
        
        
        
    }
    
    

    public void GeneracionMinas() {

        int MinasGen = 0;
        while (MinasGen != Minas) {
            int posTempFila = (int) (Math.random() * Casillas.length);
            int posTempCol = (int) (Math.random() * Casillas[0].length);
            if (!Casillas[posTempFila][posTempCol].isMina()) {
                Casillas[posTempFila][posTempCol].setMina(true);
                MinasGen++;
            }

        }
ActNumsMinaAlrededor();
    }

    public void ActNumsMinaAlrededor() {

        for (int i = 0; i < Casillas.length; i++) {
            for (int j = 0; j < Casillas[i].length; j++) {

                if (Casillas[i][j].isMina()) {
                    List<Casilla> MinasAlrededor = obtenerNumMinasAlrededor(i,j);
                    MinasAlrededor.forEach((c)->c.IncrementarNumMinasAlrededor());

                }

            }

        }
    }

    public List<Casilla> obtenerNumMinasAlrededor(int posFila, int posCol) {
        List<Casilla> ListaCasillas = new LinkedList();

        for (int i = 0; i < 8; i++) {
            int TempPosFila = posFila;
            int TempPosCol = posCol;
            switch (i) {
                case 0: TempPosFila--;
                    break;
                case 1: TempPosFila--;TempPosCol++;
                    break;
                case 2:TempPosFila--;TempPosCol--;
                    break;
                case 3: TempPosCol--;
                    break;
                case 4: TempPosFila++;
                    break;
                case 5:TempPosFila++;TempPosCol++;
                    break;
                case 6:TempPosFila++;TempPosCol--;
                    break;
                case 7:TempPosCol++;
                    break;

            }
            if(TempPosFila>=0 && TempPosFila<this.Casillas.length  && TempPosCol>=0 && TempPosCol <this.Casillas[0].length){
                ListaCasillas.add(this.Casillas[TempPosFila][TempPosCol]);
            }

        }
return ListaCasillas;
    }

    public void Imprimir() {
        for (int i = 0; i < Casillas.length; i++) {
            for (int j = 0; j < Casillas[i].length; j++) {

                System.out.print(Casillas[i][j].isMina() ? "*" : "0");

            }
            System.out.println("");

        }

    }
    
    public void ImprimirCasillasAlrededor() {
        for (int i = 0; i < Casillas.length; i++) {
            for (int j = 0; j < Casillas[i].length; j++) {

                System.out.print(Casillas[i][j].getNumsMinaAlrededor());

            }
            System.out.println("");

        }

    }

    /**
     * @param PartidaPerdida the PartidaPerdida to set
     */
    public void setPartidaPerdida(Consumer<List<Casilla>> PartidaPerdida) {
        this.PartidaPerdida = PartidaPerdida;
    }

    /**
     * @param CasillaAbierta the CasillaAbierta to set
     */
    public void setCasillaAbierta(Consumer<Casilla> CasillaAbierta) {
        this.CasillaAbierta = CasillaAbierta;
    }

    void setCasillaAbierta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @param PartidaGanada the PartidaGanada to set
     */
    public void setCasillaBandera(Consumer<Casilla> PartidaGanada) {
        this.CasillaBandera = PartidaGanada;
    }

    /**
     * @param PartidaGanada the PartidaGanada to set
     */
    public void setPartidaGanada(Consumer PartidaGanada) {
        this.PartidaGanada = PartidaGanada;
    }

}
