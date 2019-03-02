package br.com.webservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.webservice.connection.ConnectPostgres;
import br.com.webservice.model.Processo;
import br.com.webservice.model.Solicitacao;

public class ProcessoDAO {
	public ArrayList<Processo> getAllProcess(String usuario) {
		ArrayList<Processo> userRank = new ArrayList<Processo>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery = " SELECT numero, requerente, requerido, regOAB, dataInicio, dataFinal, status, tipoAcao, descricao FROM Processo ";
			strQuery += " WHERE requerente = ? ";
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, usuario);
			rSet = ppStm.executeQuery();
			
			while (rSet.next()) {
				userRank.add(new Processo(rSet.getLong(1), rSet.getString(2), rSet.getString(3), 
							rSet.getLong(4), rSet.getString(5), rSet.getString(6), rSet.getInt(7), 
							rSet.getString(8), rSet.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRank;	
	}
	
	public ArrayList<Processo> getAllProcessAdv(Long regOAB) {
		ArrayList<Processo> userRank = new ArrayList<Processo>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery = " SELECT numero, requerente, requerido, regOAB, dataInicio, dataFinal, status, tipoAcao, descricao FROM Processo ";
			strQuery += " WHERE regOAB = ? ";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setLong(1, regOAB);
			rSet = ppStm.executeQuery();
			
			while (rSet.next()) {
				userRank.add(new Processo(rSet.getLong(1), rSet.getString(2), rSet.getString(3), 
							rSet.getLong(4), rSet.getString(5), rSet.getString(6), rSet.getInt(7), 
							rSet.getString(8), rSet.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRank;	
	}
	
	public boolean insert(Processo processo) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "INSERT INTO Processo VALUES (?, ?, ?, ?,'" + processo.getDataInicial() + "', NULL" + " , ?, ?, ?) ";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setLong(1, processo.getNumero());
			ppStm.setString(2, processo.getRequerente());
			ppStm.setString(3, processo.getRequerido());
			ppStm.setLong(4, processo.getRegAdvogado());
			
			//SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
	        //Date parsed = (Date)format.parse(processo.getDataInicial());
	        //java.sql.Date sql = new java.sql.Date(parsed.getTime());
			
			//ppStm.setDate(5, new java.sql.Date( (java.util.Date) (new SimpleDateFormat("dd-mm-yyyy").parse(processo.getDataInicial()))));
			//ppStm.setDate(6, (java.sql.Date) (new SimpleDateFormat("dd-mm-yyyy")).parse(""));
			ppStm.setInt(5, processo.getStatus());
			ppStm.setString(6, processo.getTipo());
			ppStm.setString(7, processo.getDescricao());
			
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
}
