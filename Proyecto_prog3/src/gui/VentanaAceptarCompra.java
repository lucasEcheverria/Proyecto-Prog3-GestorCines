package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Cliente;
import domain.Entrada;

public class VentanaAceptarCompra extends JFrame {
	private static final long serialVersionUID = 1L;

	private JCheckBox checkBox;
	private JPanel pcentro,psur;
	private JButton btnconfirmar;
	private JFrame vActual,vCarrito;
	
	public VentanaAceptarCompra(Cliente c, JFrame vC){
		vActual = this;
		vCarrito = vC;
		
		psur = new JPanel();
		pcentro = new JPanel();
        pcentro.setLayout(new BoxLayout(pcentro, BoxLayout.Y_AXIS));
		
        for (Entrada entrada: c.getCarrito_de_compra().keySet()) {
			checkBox = new JCheckBox(entrada.getTitulo_peli(), false);
			pcentro.add(checkBox);
		}
        
		btnconfirmar = new JButton("Confirmar");
		btnconfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres comprar estas entradas?");		
				if (result == JOptionPane.OK_OPTION) {
					if (checkBox.isSelected()) {
						for (Entrada entrada: c.getCarrito_de_compra().keySet()) {
							if (entrada.getTitulo_peli().equals(checkBox.getText())) {
								c.getCarrito_de_compra().remove(entrada);
							}
						}
						
					}
					vActual.dispose();
					vCarrito.setVisible(true);
				}
			}
		});
		
		getContentPane().add(pcentro,BorderLayout.CENTER);
		getContentPane().add(psur,BorderLayout.SOUTH);
		
		psur.add(btnconfirmar);
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(300,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
	}
	
	

}
