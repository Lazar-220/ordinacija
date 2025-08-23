/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.klijent.forme.LoginForma;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class LoginController {
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
        addActionListenerCheckBox();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void prijava(ActionEvent e) throws Exception {
                Fizijatar ulogovani=new Fizijatar();
                try{
                    String username=lf.getjTextField1().getText().trim();
                    String password=String.valueOf(lf.getjPasswordField1().getPassword()).trim();

                    Komunikacija.getInstance().Konekcija();
                    ulogovani=Komunikacija.getInstance().login(username,password);
                    
                }catch(Exception ex){
                   // ex.printStackTrace();
                    
                    if(ex instanceof IllegalArgumentException){
                        JOptionPane.showMessageDialog(lf, "Fizijatar je vec ulogovan!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Greska", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;

                    
                }
                
                    Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().otvoriGlavnuFormu(ulogovani);
                    lf.dispose();
                
            }
        });
        
    }
    private void addActionListenerCheckBox() {
        lf.addActionListenerCheckBox(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }

            private void check() {
                if(lf.getjCheckBox1().isSelected()){
                    lf.getjPasswordField1().setEchoChar((char)0);
                }
                else{
                    lf.getjPasswordField1().setEchoChar('•');
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
