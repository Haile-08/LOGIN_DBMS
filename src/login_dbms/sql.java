package login_dbms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class sql extends JFrame {
      JLabel userlogin            = new JLabel();
      JLabel username             = new JLabel();
      JLabel password             = new JLabel();
      
      JTextField userfill         = new JTextField();
      JPasswordField passwordfill = new JPasswordField();
      
      JButton sumit           = new JButton();
      
      Statement stmt = null;
      
      Connection con = null;
      
      PreparedStatement pst = null;
      
      ResultSet rs = null;
    
      sql(){
      
     userlogin .setText("USER LOGIN");
     userlogin .setBounds(170,150, 250, 20);
     userlogin .setFont(new Font("Serif",Font.PLAIN,25));
     add( userlogin);
     
     username.setText("USERNAME");
     username.setBounds(100, 220, 150, 40);
     add(username);
     username.setFont(new Font("Serif",Font.PLAIN,19));
     
     password.setText("PASSWORD");
     password.setBounds(100, 270, 150, 40);
     add(password);
     password.setFont(new Font("Serif",Font.PLAIN,19));
     
     userfill.setBounds(250, 230, 120, 27);
     add(userfill);
     
     passwordfill.setBounds(250, 280, 120, 27);
     add(passwordfill);
     
     sumit.setText("SUBMIT");
     sumit.setFont(new Font("Serif",Font.PLAIN,19));
     sumit.setBackground(Color.YELLOW);
     sumit.setBounds(180, 340, 110, 35);
     add(sumit);
     
     
     
     
     
     setTitle("Covid19 Tracer");
     setSize(500,600);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     getContentPane().setBackground(Color.yellow);
     setLayout(null);
     setVisible(true);
     
     sumit.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                    
                try {
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:Covid19.db");
                    System.out.println("database opend");
                    stmt = con.createStatement();
                    String sq="SELECT * FROM USER WHERE NAME =? AND PASSWORD=?";
                    pst = con.prepareStatement(sq);
                    pst.setString(1, userfill.getText());
                    pst.setString(2, passwordfill.getText());
                    
                     rs = pst.executeQuery();
                     if(rs.next()){
                         JOptionPane.showMessageDialog(null, "Username and Password Correct");
                         setVisible(false); //closes login page
                     }
                     else{
                        JOptionPane.showMessageDialog(null, "Username and Password Incorrect");
                     }
                    stmt.executeUpdate(sq);
                    stmt.close();
                    con.close();
         
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Username and Password Incorrect");
               }
     
            }
        });
     
     
      
      }
    
    
}
