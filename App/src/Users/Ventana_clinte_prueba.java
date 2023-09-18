package Users;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class Ventana_clinte_prueba extends JFrame{
    public Ventana_clinte_prueba(){
        this.setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Hotel La Huaca");
        this.setMinimumSize(new Dimension(200,200));

        this.getContentPane().setBackground(Color.PINK );

        setLocationRelativeTo(null);
        IniciarComponentes();
    }
    
    private void IniciarComponentes(){
        JPanel panel = new JPanel();

        this.getContentPane().add(panel);
        panel.setBackground(Color.LIGHT_GRAY);
    };
}
