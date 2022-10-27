package views;

import java.awt.EventQueue;

import javax.swing.JDialog;

import models.DAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 534, 318);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(31, 27, 26, 14);
		getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(56, 24, 53, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnBuscar.setBounds(119, 23, 89, 23);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(31, 75, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(87, 72, 378, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(31, 123, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(75, 120, 138, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(271, 123, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(327, 120, 138, 20);
		getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnAdicionar.setBounds(175, 209, 89, 23);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		btnEditar.setBounds(289, 209, 89, 23);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(402, 209, 89, 23);
		getContentPane().add(btnExcluir);

	}//fim do construtor
	
	DAO dao = new DAO();
	
	/**
	 * Metodo responsavel pelo status
	 */
	
	private void status() {
		//System.out.println("teste do status");
		try {
			//Abrir a conexão
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("erro de conexão");
			} else {
				System.out.println("banco conectado");
			}
			//Fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} //FIM DO STATUS
	
	
	/**
	 * Pesquisa do usuario por id
	 */
	
	
	private void pesquisarUsuario() {
		//System.out.println("Teste do botão pesquisar");
		String read = "select * from usuarios where id = ?";
		
		try {
			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setString(1, txtId.getText());
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				txtUsuario.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
			}else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}//fim do metodo pesquisar usuario
	
	void adicionarContato() {
		
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");
			
			txtUsuario.requestFocus();
		}else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!");
			
			txtLogin.requestFocus();
		}else {
			String create = "insert into usuarios (usuario, login, senha) value (?,?,?)";
		
		
		try {
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, txtSenha.getText());
			
			int confirma = pst.executeUpdate();
			
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
				limpar();
			}else {
				
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	} //FIM DO METODO ADICIONAR USUARIO
	
	private void excluirUsuario() {
		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "ATENÇÃO!",
													JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION){
			String delete = "delete from usuarios where id = ?; ";
			
			try {
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				
				int confirmaExcluir = pst.executeUpdate();
				
				if(confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
				}
				
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} // FIM DO METODO EXCLUIR
	
	private void alterarUsuario() {
		
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");
			
			txtUsuario.requestFocus();
			
		
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!");
			
			txtLogin.requestFocus();
		} else {
			String update = "update usuarios set usuario = ?, login = ?, senha = ? where id = ?;";
			
			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(update);
				
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtId.getText());
				
				int confirma = pst.executeUpdate();
				
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato Atualizado com sucesso!");
					
					limpar();
				}
				
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} //FIM DO METODO ALTERAR
	
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JTextField txtSenha;
	
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
	}
}//fim do código
