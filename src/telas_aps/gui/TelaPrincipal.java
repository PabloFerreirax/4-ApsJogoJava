/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas_aps.gui;

import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pablo
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpAreaTrabalho = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal - GreenCode");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setResizable(false);

        javax.swing.GroupLayout jdpAreaTrabalhoLayout = new javax.swing.GroupLayout(jdpAreaTrabalho);
        jdpAreaTrabalho.setLayout(jdpAreaTrabalhoLayout);
        jdpAreaTrabalhoLayout.setHorizontalGroup(
            jdpAreaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        jdpAreaTrabalhoLayout.setVerticalGroup(
            jdpAreaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        getContentPane().add(jdpAreaTrabalho, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Cadastrar");

        jMenuItem1.setText("Fase");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Usuário");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Gerenciar");

        jMenuItem2.setText("Fase");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setText("Usuário");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Relatório");

        jMenuItem5.setText("Progresso jogador");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Desempenho fase");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CadastroFase cf = new CadastroFase();
        cf.setVisible(true);
        jdpAreaTrabalho.add(cf);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ListaFase lf = new ListaFase();
        lf.setVisible(true);
        jdpAreaTrabalho.add(lf);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         try{
            CadastroUsuario CadUs = new CadastroUsuario();
            jdpAreaTrabalho.add(CadUs);
            CadUs.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
            showMsgErro("Erro ao abrir tela", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try{
            ListaUsuario lu = new ListaUsuario();
            jdpAreaTrabalho.add(lu);
            lu.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
            showMsgErro("Erro ao abrir tela", e.getMessage());
        }      
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.showReportFase();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.showReportInJasperViewer();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

public void showReportInJasperViewer() {
        String sourceFile = "C:\\Users\\aps\\JaspersoftWorkspace\\ApsRelato\\Cherry.jasper";
        File f = new File("telas_aps.gui.Imagem/Cherry.jasper");
        //String sourceFile = f.getAbsolutePath();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Titulo informado");
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbteste", "postgres", "1");
            JasperPrint jp = JasperFillManager.fillReport(sourceFile, parametros, con);
            JasperViewer.viewReport(jp,false);
        } catch (JRException | ClassNotFoundException |
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}

public void showReportFase() {
    String sourceFile = "C:\\Users\\aps\\JaspersoftWorkspace\\ApsRelato\\Fase.jasper";
    File f = new File("telas_aps.gui.Imagem/Fase.jasper");
    //String sourceFile = f.getAbsolutePath();
    HashMap<String, Object> parametros = new HashMap<>();
    parametros.put("titulo", "Titulo informado");
    Connection con = null;
    try {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/dbteste", "postgres", "1");
        JasperPrint jp = JasperFillManager.fillReport(sourceFile, parametros, con);
        JasperViewer.viewReport(jp,false);
    } catch (JRException | ClassNotFoundException |
            SQLException ex) {
        ex.printStackTrace();
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
 
            }
        });
    }
    
    public class RedimensionarImagem {
        
        public ImageIcon redimensionar(JLabel jLabel, int xLargura, int yAltura){
       
            ImageIcon img = new ImageIcon (jLabel.getIcon().toString());  
            img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
       
            return img;
        }
    }

    private void showMsgErro(String titulo, String msg){
        JOptionPane.showMessageDialog( // Caixa de mensagem
		null, // Janela da aplicação (opcional, pode ser null)
		msg, // Mensagem
		titulo, // Título da caixa de mensagem
		JOptionPane.ERROR_MESSAGE // Ícone da caixa de mensagem
	);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JDesktopPane jdpAreaTrabalho;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/telas_aps/gui/Imagem/code.png")));
    }
}
