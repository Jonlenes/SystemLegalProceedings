package br.com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.webservice.connection.ConnectPostgres;
import br.com.webservice.model.Usuario;
import br.com.webservice.model.Pessoa;

public class PessoaDAO {
	public boolean autenticar(Usuario user) {
		try {
			Connection conn = ConnectPostgres.getConnection();
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "SELECT * FROM Pessoa WHERE login = ? AND senha = ?";
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, user.getLogin());
			ppStm.setString(2, user.getSenha());
			return ((ppStm.executeQuery()).next());
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insert(Pessoa pessoa) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "INSERT INTO Pessoa VALUES(?, ?, ?, ?, ?, ?) ";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, pessoa.getLogin());
			ppStm.setString(2, pessoa.getSenha());
			ppStm.setString(3, pessoa.getNome());
			ppStm.setString(4, pessoa.getTelefone());
			ppStm.setString(5, pessoa.getEmail());
			ppStm.setString(6, pessoa.getEndereco());
			
			ppStm.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean update(Pessoa pessoa) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "UPDATE Pessoa SET senha = ? , nome = ?, telefone = ?, email = ?, endereco = ?"
					+ " WHERE login = ?"
					+ " AND NOT EXISTS("
					+ "		SELECT * FROM Advogado "
					+ "		WHERE login = ?)";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, pessoa.getSenha());
			ppStm.setString(2, pessoa.getNome());
			ppStm.setString(3, pessoa.getTelefone());
			ppStm.setString(4, pessoa.getEmail());
			ppStm.setString(5, pessoa.getEndereco());
			ppStm.setString(6, pessoa.getLogin());
			ppStm.setString(7, pessoa.getLogin());
			
			ppStm.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Pessoa getPessoa(String login) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			ResultSet rSet;
			
			strQuery  = "SELECT * FROM Pessoa ";
			strQuery += "WHERE login = ?";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, login);
			
			rSet  = ppStm.executeQuery();
			if ( rSet.next() ) return (new Pessoa(rSet.getString(1), 
					rSet.getString(2),
					rSet.getString(3),
					rSet.getString(4),
					rSet.getString(5),
					rSet.getString(6)));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
