import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




public class Test extends javax.swing.JFrame {
    
    public Test(){
        initComponents();
        Connect();
        LoadQuestions();
    }
    
    String uname;
    
    public Test(String name){
        initComponents();
        Connect();
        LoadQuestions();
        this.uname = name;
        JOptionPane.showMessageDialog(this,uname+" welcome ");
        txtlabel.setText(uname);
    }
    
    int answercheck = 0;
    int marks = 0;
    
    String cor = null;
    String answer = null;
    Statement stat;

    
    public boolean answerCheck(){
        
        String answerAnswer = "";
        if(r1.isSelected()){
            answerAnswer = r1.getText();
        }
        else if(r2.isSelected()){
            answerAnswer = r2.getText();
        }
        else if(r3.isSelected()){
            answerAnswer = r3.getText();
        }
        else if(r4.isSelected()){
            answerAnswer = r4.getText();
        }
        
        if(answerAnswer.equals(cor) && (answer == null || !answer.equals(cor))){
            marks = marks + 1;
        }
        
        if(!answerAnswer.equals("")){
            try{
                String query = "UPDATE question SET given_answer = ? WHERE question = ?";
                pst = con.prepareStatement(query);
                pst.setString(1, answerAnswer);
                pst.setString(2, jLabel2.getText());
                pst.execute();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            
            return true;
        }
        return false;
    }
    
    private void NullAllGivenAnswers(){
        
        try{
            
            String query = "UPDATE question SET given_answer = ?";
            pst = con.prepareStatement(query);
            pst.setString(1,null);
            pst.execute();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private boolean AlreadyAnswered(){
        
        try{
            
            String query = "SELECT given_answer FROM question WHERE question = '"+jLabel2.getText()+"'";
            stat = con.prepareStatement(query);
            ResultSet res = stat.executeQuery(query);
            
            while(res.next()){
                answer = res.getString("given_answer");
                if(answer == null){
                    return false;
                }
                break;
            }
            
            if(r1.getText().equals(answer)){
                r1.setSelected(true);
            }
            else if(r2.getText().equals(answer)){
                r2.setSelected(true);
            }
            else if(r3.getText().equals(answer)){
                r3.setSelected(true);
            }
            else if(r4.getText().equals(answer)){
                r4.setSelected(true);
            }
        }
        catch (SQLException ex){
            System.out.println("Exception caught");
            ex.printStackTrace();
        }
        
        return true;
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/etest", "root", "");
        }
        catch(ClassNotFoundException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getBill(){
        int billno =  1;
        
        try {
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select max(id) from question");
            rs.next();
            billno = rs.getInt(1);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(question.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billno;
    }
    
    
    public void LoadQuestions(){
        
        String query = "select * from question";
        Statement stat = null;
        
        try {
            
            stat = con.createStatement();
            rs = stat.executeQuery(query);
            
            while(rs.next()){

                txtmarks.setText(Integer.toString(getBill()));
                jLabel2.setText(rs.getString(2));
                r1.setText(rs.getString(3));
                r2.setText(rs.getString(4));
                r3.setText(rs.getString(5));
                r4.setText(rs.getString(6));
                cor = rs.getString(7);
                
                break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        txtmarks = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("Online Test 2021");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Question");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(r1);
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(r2);

        buttonGroup1.add(r3);
        r3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(r4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(r4)
                    .addComponent(r3)
                    .addComponent(r2)
                    .addComponent(r1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(r1)
                .addGap(18, 18, 18)
                .addComponent(r2)
                .addGap(26, 26, 26)
                .addComponent(r3)
                .addGap(18, 18, 18)
                .addComponent(r4)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton1.setText("Next");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtmarks.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtmarks.setText("0");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Total marks");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtmarks)
                                .addGap(94, 94, 94)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(101, 101, 101))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(331, 331, 331))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtmarks))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r1ActionPerformed

    private void r3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(answerCheck()){
            
            try{
                
                if(rs.next()){
                    jLabel2.setText(rs.getString("question"));
                    r1.setText(rs.getString(3));
                    r2.setText(rs.getString(4));
                    r3.setText(rs.getString(5));
                    r4.setText(rs.getString(6));
                    
                    cor = rs.getString(7);
           
                    buttonGroup1.clearSelection();

                }
                
                else {
                    int totalMarks = getBill();
                    JOptionPane.showMessageDialog(this, "Marks obtained : " + marks + " out of " + totalMarks);

                    
                    try{
                        String query = "UPDATE student SET marks = ? WHERE name = ?";
                                            
                        pst = con.prepareStatement(query);
                        pst.setInt(1, marks);
                        pst.setString(2, uname);
                        pst.execute();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    
                    this.hide();
                    
                }
                
            }
            
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked


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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JLabel txtlabel;
    private javax.swing.JLabel txtmarks;
    // End of variables declaration//GEN-END:variables
}
