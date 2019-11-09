/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas_aps.gui;

import br.com.aps.quarto.semestre.enumerated.TipoTela;
import br.com.aps.quarto.semestre.exception.ValorInvalidoException;
import br.com.aps.quarto.semestre.modelo.Usuario;
import br.com.aps.quarto.semestre.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author tsdev04
 */
public class CadastroUsuario extends javax.swing.JInternalFrame {

    private Usuario usuario;
    private TipoTela tipoTela;
    private UsuarioService service;
    private ListaUsuario listaUsuarioForm;
    
    /**
     * Creates new form CadastroUsuario
     */
    public CadastroUsuario() {
        setTipoTela(TipoTela.CADASTRO);
        
        initComponents();
    }
    
     public CadastroUsuario(Usuario usuario, ListaUsuario listaFaseForm) {
         setTipoTela(TipoTela.ALTERACAO);
         setUsuario(usuario);
         setListaUsuarioForm(listaFaseForm);
         
         initComponents();
         
         setValores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        txtSenha = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuário"));

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 15)); // NOI18N
        jLabel1.setText("Nome:");

        txtNome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 13)); // NOI18N
        txtNome.setSelectionColor(new java.awt.Color(0, 255, 0));

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 15)); // NOI18N
        jLabel2.setText("Nick:");

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 15)); // NOI18N
        jLabel3.setText("Senha:");

        txtNick.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 13)); // NOI18N
        txtNick.setSelectionColor(new java.awt.Color(0, 255, 0));

        btnSalvar.setFont(new java.awt.Font("Palatino Linotype", 1, 15)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 13)); // NOI18N
        txtSenha.setSelectionColor(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(152, 152, 152))
                            .addComponent(txtSenha)))
                    .addComponent(txtNome))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try{
            getValores();
            
            if(getTipoTela()==TipoTela.CADASTRO)
                getService().save(getUsuario());
            else
                getService().update(usuario);
            
            showMsg("Sucesso", "UsuÃ¡rio salvo com sucesso");
            
            if(getTipoTela()== TipoTela.ALTERACAO){
                getListaUsuarioForm().updateTable();
                dispose();
            }else{
                CadastroUsuario cadU = new CadastroUsuario();
                getParent().add(cadU);
                cadU.setVisible(true);
                dispose();
            }
        }
        catch(ValorInvalidoException e){
            showMsgErro("Erro ao salvar", e.getMessage());
        }
        catch(Exception e){
            showMsgErro("Erro ao salvar", "Causado por: "+ e.getMessage());
            if(getTipoTela()==TipoTela.CADASTRO)
            	setUsuario(new Usuario());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    public Usuario getUsuario() {
        if(usuario == null) usuario = new Usuario();
         
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
    }
    
     public UsuarioService getService() {
         if(service == null) service = new UsuarioService();
         return service;
    }

    public ListaUsuario getListaUsuarioForm() {
        return listaUsuarioForm;
    }

    public void setListaUsuarioForm(ListaUsuario listaUsuarioForm) {
        if(listaUsuarioForm == null)
            throw new ValorInvalidoException("O formulÃ¡rio ListaFase informado estÃ¡ nulo");
        this.listaUsuarioForm = listaUsuarioForm;
    }
     
     


    private void showMsgErro(String titulo, String msg){
        JOptionPane.showMessageDialog( // Caixa de mensagem
		null, // Janela da aplicaÃ§Ã£o (opcional, pode ser null)
		msg, // Mensagem
		titulo, // TÃ­tulo da caixa de mensagem
		JOptionPane.ERROR_MESSAGE // Ã�cone da caixa de mensagem
	);
    }
    
     private void showMsg(String titulo, String msg){
        JOptionPane.showMessageDialog( // Caixa de mensagem
		null, // Janela da aplicaÃ§Ã£o (opcional, pode ser null)
		msg, // Mensagem
		titulo, // TÃ­tulo da caixa de mensagem
		JOptionPane.INFORMATION_MESSAGE // Ã�cone da caixa de mensagem
	);
    }
    
    public void setValores(){
        txtNome.setText(getUsuario().getNmUsuario());
        txtNick.setText(getUsuario().getNickname());
        txtSenha.setText(getUsuario().getSenha());
    }
    
    public void getValores(){   
        checkUsuario();
        
        getUsuario().setNmUsuario(txtNome.getText());
        getUsuario().setNickname(txtNick.getText());
        getUsuario().setSenha(txtSenha.getText());
    }
    
    public void checkUsuario(){
        if(txtNome.getText().length()<=0)
            throw new ValorInvalidoException("Informe o nome do usuÃ¡rio");
        if(txtNick.getText().length()<=0)
            throw new ValorInvalidoException("Informe o nick do usuÃ¡rio");
        if(txtSenha.getText().length()<=0)
            throw new ValorInvalidoException("Informe o senha do usuÃ¡rio");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNick;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}