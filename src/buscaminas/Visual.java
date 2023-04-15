/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buscaminas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class Visual extends javax.swing.JFrame {

   private int numFilas=0;
    private int numCol=0;
   private  int Minas = 0;
   private int Banderas =0;
    
    JButton[][] botones;
    Tablero Tablero;

     
    public int getNumFilas() {
        return numFilas;
    }

    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public int getMinas() {
        return Minas;
    }

    /**
     * Creates new form Visual
     */
    public void setMinas(int Minas) {    
        this.Minas = Minas;
    }

    public Visual() {
        initComponents();
        InicializarControles();
        CrearTablero();
        repaint();
        
       
    }

    public int getBanderas() {
        return Banderas;
    }

    public void setBanderas(int Banderas) {
        this.Banderas = Banderas;
    }
    
    
    public void CrearTablero(){
        
       
        Tablero = new Tablero(getNumFilas(),getNumCol(),getMinas(),getBanderas());
        Tablero.setPartidaPerdida(new Consumer<List<Casilla>>(){
            @Override
            public void accept(List<Casilla> t) {
            ImageIcon icono = new ImageIcon("src/Img/bomba.png");
                for(Casilla casillaconMina :t ){
                    
                    botones[casillaconMina.getX()][casillaconMina.getY()].setIcon(null);
                    botones[casillaconMina.getX()][casillaconMina.getY()].setIcon(icono);
                    
                }
                   for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                botones[i][j].setEnabled(false);
                Tablero.AbrirCasilla(i, j);
            }}
               
            }
           
            
        });
        Tablero.setCasillaAbierta(new Consumer<Casilla> (){
            @Override
            public void accept(Casilla t) {
               botones[t.getX()][t.getY()].setEnabled(false);
                botones[t.getX()][t.getY()].setText(t.getNumsMinaAlrededor()==0 ?"" :t.getNumsMinaAlrededor()+"");
            }
            
        });
        Tablero.setCasillaBandera(new Consumer<Casilla> (){
            @Override
            public void accept(Casilla t) {
                ImageIcon icono = new ImageIcon("src/Img/bandera.png");
                if(botones[t.getX()][t.getY()].getIcon()!=null){
                    botones[t.getX()][t.getY()].setIcon(null);
                }else{
               
               botones[t.getX()][t.getY()].setIcon(icono);
                }
            }
            
        });
        Tablero.setPartidaGanada(new Consumer(){
            @Override
            public void accept(Object t) {
                 for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                botones[i][j].setEnabled(false);
                Tablero.AbrirCasilla(i, j);
            }}
                 JOptionPane.showMessageDialog(PanelJuego, "GANASTE");
            }
            
        });
         
        
    }
    public void InicializarControles(){
        
        botones = new JButton[numFilas][numCol];
        
         for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                botones[i][j]= new JButton();
                 botones[i][j].setName(i+","+j);
                  botones[i][j].setBorder(null);
                  if(i==0 && j==0){
                      
                      botones[i][j].setBounds(25, 25, 30, 30);
                      
                      
                  }
                  else if(i==0 && j!=0){
                      
                      botones[i][j].setBounds(botones[i][j-1].getX()+botones[i][j-1].getWidth(), 25, 30, 30);
                      
                      
                  }
                 else{
                      
                      botones[i][j].setBounds(botones[i-1][j].getX(),botones[i-1][j].getY()+botones[i-1][j].getWidth() , 30, 30); 
                     
                      
                  }
                  botones[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    
                       
                        botonClick(e);
                    }
                
            });
                  
                  botones[i][j].addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        
        if (e.getButton() == MouseEvent.BUTTON1) {
            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            FlagClick(e);
        }
    }
});
                  PanelJuego.add(botones[i][j]);
                

            }
          

        }
        
    }
    public void botonClick(ActionEvent e){
        
       
        JButton btn =(JButton)e.getSource();
        String[] coorBtn=btn.getName().split(",");
        
        int posFila = Integer.parseInt(coorBtn[0]);
        int posCol = Integer.parseInt(coorBtn[1]);  
        
        
        Tablero.EventosCasillas(posFila, posCol);
        
        
    }
    
    public void FlagClick(MouseEvent e){
        
       
        JButton btn =(JButton)e.getSource();
        String[] coorBtn=btn.getName().split(",");
        int posFila = Integer.parseInt(coorBtn[0]);
        int posCol = Integer.parseInt(coorBtn[1]);
        Tablero.EventoParaPartidaGanada(posFila, posCol);
      
        
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelTop = new javax.swing.JPanel();
        ComboBDificultad = new javax.swing.JComboBox<>();
        Jugar = new javax.swing.JButton();
        Panel = new javax.swing.JPanel();
        PanelJuego = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelTop.setBackground(new java.awt.Color(153, 153, 153));
        PanelTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelTop.setForeground(new java.awt.Color(255, 153, 0));

        ComboBDificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facil", "Normal", "Dificil" }));
        ComboBDificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBDificultadActionPerformed(evt);
            }
        });

        Jugar.setText("Jugar");
        Jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTopLayout = new javax.swing.GroupLayout(PanelTop);
        PanelTop.setLayout(PanelTopLayout);
        PanelTopLayout.setHorizontalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboBDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addComponent(Jugar)
                .addGap(18, 18, 18))
        );
        PanelTopLayout.setVerticalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTopLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jugar))
                .addContainerGap())
        );

        getContentPane().add(PanelTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, -1));

        Panel.setBackground(new java.awt.Color(51, 51, 51));

        PanelJuego.setBackground(new java.awt.Color(153, 153, 153));
        PanelJuego.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout PanelJuegoLayout = new javax.swing.GroupLayout(PanelJuego);
        PanelJuego.setLayout(PanelJuegoLayout);
        PanelJuegoLayout.setHorizontalGroup(
            PanelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );
        PanelJuegoLayout.setVerticalGroup(
            PanelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(PanelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBDificultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBDificultadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBDificultadActionPerformed

    public void SetJuego(int Filas , int Col , int Minas){
        QuitarJuego();
        setNumFilas(Filas);
           setNumCol(Col);
           setMinas(Minas);
           setBanderas(getMinas());
          repaint();
    }
    
    public void QuitarJuego(){
        if(botones!=null){
            
            for(int i = 0 ; i < botones.length;i++){
                for(int j = 0 ; j < botones[i].length;j++){
                    if(botones[i][j]!=null){
                        PanelJuego.remove(botones[i][j]);
                    }
                }
            }
        }
        
    }
    
    private void JugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JugarActionPerformed
        // TODO add your handling code here:
        
        if(ComboBDificultad.getSelectedIndex()==0){
          SetJuego(10,10,10);
        }
         if(ComboBDificultad.getSelectedIndex()==1){
            SetJuego(10,15,20);
        }
        if(ComboBDificultad.getSelectedIndex()==2){
            SetJuego(10,20,30);
        }
         
        InicializarControles();
        CrearTablero();
    }//GEN-LAST:event_JugarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBDificultad;
    private javax.swing.JButton Jugar;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel PanelJuego;
    private javax.swing.JPanel PanelTop;
    // End of variables declaration//GEN-END:variables
}
