package br.com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.webservice.connection.ConnectPostgres;
import br.com.webservice.model.Advogado;
import br.com.webservice.model.AdvogadoRank;
import br.com.webservice.model.Pessoa;
import br.com.webservice.model.Usuario;

public class AdvogadoDAO {

	public boolean autenticar(Usuario user) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			
			strQuery = "SELECT * FROM Advogado WHERE login = ? AND senha = ? ";
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, user.getLogin());
			ppStm.setString(2, user.getSenha());
			return ((ppStm.executeQuery()).next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Advogado> getAllAdvogados() {
		ArrayList<Advogado> listAllAdvogados = new ArrayList<Advogado>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery = " SELECT * FROM Advogado ";
			ppStm = conn.prepareStatement(strQuery);
			rSet = ppStm.executeQuery();
			
			while (rSet.next()) {
				listAllAdvogados.add(new Advogado(rSet.getString(1), rSet.getString(2),
										rSet.getString(3), rSet.getString(4), rSet.getString(5), 
										rSet.getString(6), rSet.getLong(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAllAdvogados;
	}
	
	public ArrayList<AdvogadoRank> getRank(String Area) {
		ArrayList<AdvogadoRank> listRank = new ArrayList<AdvogadoRank>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery =  "SELECT DISTINCT advogado.regOAB, advogado.nome, ProcCont.count FROM advogado "
					 +  "INNER JOIN (SELECT regOAB, count(*) As count "
					 +	"	    FROM Processo ";
			if(!Area.equals("")) {
				strQuery += "	WHERE tipoAcao = '" + Area + "' "; 
			}
			strQuery +=  "	    GROUP BY regOAB) As ProcCont "
					  +  "ON advogado.regOAB = ProcCont.regOAB "
					  +  "ORDER BY ProcCont.count DESC ";
			
			ppStm = conn.prepareStatement(strQuery);
			rSet = ppStm.executeQuery();
			
			int i = 0;
			while ( rSet.next() && i <= 10) {
				if(listRank.isEmpty() || (!listRank.isEmpty() && 
						listRank.get(listRank.size() - 1).getQtdeProc() !=  rSet.getInt(3))) i++;
				listRank.add(new AdvogadoRank(rSet.getLong(1), rSet.getString(2),
						rSet.getInt(3), i));  	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRank;	
	}
	
	public Advogado getAdvogado(String login) {
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			String strQuery;
			ResultSet rSet;
			
			strQuery  = "SELECT * FROM Advogado ";
			strQuery += "WHERE login = ?";
			
			ppStm = conn.prepareStatement(strQuery);
			ppStm.setString(1, login);
			
			rSet  = ppStm.executeQuery();
			if ( rSet.next() ) return (new Advogado(rSet.getString(1), 
					rSet.getString(2),
					rSet.getString(3),
					rSet.getString(4),
					rSet.getString(5),
					rSet.getString(6),
					rSet.getLong(7)));
			
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