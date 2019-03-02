package br.com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.webservice.connection.ConnectPostgres;
import br.com.webservice.model.Solicitacao;

public class SolicitacaoDAO {
	public boolean insert(Solicitacao solicitacao) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "INSERT INTO Solicitacao VALUES (?, ?, ?, ?, ?) ";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setLong(1, solicitacao.getRegOAB());
			ppStm.setString(2, solicitacao.getRequerente());
			ppStm.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ppStm.setString(4, solicitacao.getDescricao());
			ppStm.setString(5, solicitacao.getTipoAcao());
			
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
	
	public ArrayList<Solicitacao> getAllSolicitation(Long regOAB) {
		ArrayList<Solicitacao> arrayList = new ArrayList<Solicitacao>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery = " SELECT * FROM Solicitacao ";
			strQuery += " WHERE regOAB = ? ";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setLong(1, regOAB);
			rSet = ppStm.executeQuery();
			
			while (rSet.next()) {
				arrayList.add(new Solicitacao(regOAB, rSet.getString(2), rSet.getString(3), 
							rSet.getString(4), rSet.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;	
	}
}
