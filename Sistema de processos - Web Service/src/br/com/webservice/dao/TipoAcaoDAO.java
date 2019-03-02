package br.com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.webservice.connection.ConnectPostgres;
import br.com.webservice.model.TipoAcao;

public class TipoAcaoDAO {
	
	public ArrayList<TipoAcao> getAllTypes() {
		ArrayList<TipoAcao> listAllTypes = new ArrayList<TipoAcao>();
		try {
			Connection conn = ConnectPostgres.getConnection();;
			PreparedStatement ppStm;
			ResultSet rSet;
			String strQuery;
			
			strQuery = " SELECT * FROM TipoAcao ";
			ppStm = conn.prepareStatement(strQuery);
			rSet = ppStm.executeQuery();
			
			while (rSet.next()) {
				listAllTypes.add(new TipoAcao(rSet.getString(1), rSet.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAllTypes;
	}
}
