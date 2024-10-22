package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Ventana_elegirbutaca extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel pnorte,psur;
	private JTable tabla;
	private DefaultTableModel modelotabla;
	private JScrollPane scroll;

	public Ventana_elegirbutaca() {
		psur = new JPanel();
		pnorte = new JPanel();
		
		modelotabla = new DefaultTableModel();
		tabla = new JTable(modelotabla);

		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if (value instanceof ImageIcon) {
					JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER); 
            		tabla.setRowHeight(row, 50);
                    return label;
				}
				if (value.equals("")&&column!=0) {
					c.setBackground(Color.black);
				}
				else {
					c.setBackground(Color.white);
				}
				return c;
			}
		});
		scroll = new JScrollPane(tabla);
		
		String [] lista = {"COLUMNA\\FILA","1","2","3","4","5","6","7","8","9","10"};
		modelotabla.setColumnIdentifiers(lista);
		cargarTabla();
		
		getContentPane().add(scroll,BorderLayout.CENTER);
		getContentPane().add(psur,BorderLayout.SOUTH);
		getContentPane().add(pnorte,BorderLayout.NORTH);
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void cargarTabla() {
		Object[] fila1 = {"","","","","","","","","","",""};
		modelotabla.addRow(fila1);
		for (int i = 0; i < 8; i++) {
	        ImageIcon img = new ImageIcon("resource/images/asiento.jpg");
			Image imagenOriginal = img.getImage();
	        Image imagenRedimensionada = imagenOriginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	        ImageIcon imgredimensionada = new ImageIcon(imagenRedimensionada);
	        
			Object[] fila = {i+1,imgredimensionada,imgredimensionada,imgredimensionada,
					imgredimensionada,imgredimensionada,imgredimensionada,imgredimensionada
					,imgredimensionada,imgredimensionada,imgredimensionada};
			modelotabla.addRow(fila);
		}
	}
	
	
}
