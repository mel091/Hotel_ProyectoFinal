package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controllers.RentasController;
import models.Habitacion;
import models.HabitacionesModel;
public class HabitacionView extends JPanel {
	private RentasController renta;
	HabitacionesModel habi=new HabitacionesModel();
	public HabitacionView(Habitacion habitacion, int y, JFrame frame)
	{

		setBackground(new Color(0, 72, 103));
		setBounds(28, y, 1120, 144);
		setLayout(null);
		
		ImageIcon icon = HabitacionesModel.blobToImageIcon(habitacion.getImagen());
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (icon != null) {
                    g.drawImage(icon.getImage(), 0,0,257,122, null);
                }
            }
        };
		panelImagen.setLayout(null);
		panelImagen.setOpaque(true);
		panelImagen.setBackground(new Color(214, 252, 254));
		panelImagen.setBounds(10, 11, 257, 122);
		add(panelImagen);
		
		JPanel infopan = new JPanel();
		infopan.setBackground(new Color(255, 255, 255));
		infopan.setBounds(277, 11, 833, 122);
		add(infopan);
		infopan.setLayout(null);
		
		//JLabel nomHabitacion = new JLabel("Nombre de la habitación");
		JLabel nomHabitacion = new JLabel(habitacion.getNombre());
		nomHabitacion.setHorizontalAlignment(SwingConstants.LEFT);
		nomHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		nomHabitacion.setBounds(10, 8, 450, 35);
		infopan.add(nomHabitacion);
		
		JLabel tipoHabitacion = new JLabel(habitacion.getTipo());
		tipoHabitacion.setHorizontalAlignment(SwingConstants.LEFT);
		tipoHabitacion.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		tipoHabitacion.setBounds(10, 37, 343, 35);
		infopan.add(tipoHabitacion);
		
		JLabel tamaño = new JLabel(habitacion.getTam());
		tamaño.setHorizontalAlignment(SwingConstants.LEFT);
		tamaño.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		tamaño.setBounds(10, 64, 343, 35);
		infopan.add(tamaño);
		
		JLabel desc = new JLabel(habitacion.getDescripcion());
		desc.setHorizontalAlignment(SwingConstants.LEFT);
		desc.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		desc.setBounds(10, 95, 343, 29);
		infopan.add(desc);
		
		JLabel desde = new JLabel("Desde");
		desde.setHorizontalAlignment(SwingConstants.LEFT);
		desde.setFont(new Font("Palatino Linotype", Font.PLAIN, 15));
		desde.setBounds(763, 9, 49, 35);
		infopan.add(desde);
		
		JLabel desde_1 = new JLabel("$");
		desde_1.setHorizontalAlignment(SwingConstants.LEFT);
		desde_1.setFont(new Font("Palatino Linotype", Font.PLAIN, 15));
		desde_1.setBounds(655, 37, 18, 35);
		infopan.add(desde_1);
		
		JLabel cantidad = new JLabel("3200.00");
		cantidad.setHorizontalAlignment(SwingConstants.LEFT);
		cantidad.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		cantidad.setBounds(668, 45, 104, 47);
		infopan.add(cantidad);
		
		JLabel usd = new JLabel("USD");
		usd.setHorizontalAlignment(SwingConstants.LEFT);
		usd.setFont(new Font("Palatino Linotype", Font.PLAIN, 15));
		usd.setBounds(773, 53, 39, 35);
		infopan.add(usd);
		
		JLabel leyenda = new JLabel("por noche, incluidos los impuestos");
		leyenda.setHorizontalAlignment(SwingConstants.LEFT);
		leyenda.setFont(new Font("Palatino Linotype", Font.PLAIN, 15));
		leyenda.setBounds(583, 89, 250, 35);
		infopan.add(leyenda);
		
		JButton btnHab1 = new JButton();
		btnHab1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("hab1");
				frame.dispose();
				renta = new RentasController();
				renta.crear();
			}
		});
		btnHab1.setBounds(0, 0, 1120, 144);
		btnHab1.setBorderPainted(false);
		btnHab1.setContentAreaFilled(false);
		add(btnHab1);
		
	}
	
}
