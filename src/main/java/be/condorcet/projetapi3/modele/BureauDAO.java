package be.condorcet.projetapi3.modele;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class BureauDAO {
    String url ;
    String userid;

    String password;

    public BureauDAO(@Value("${spring.datasource.url}")String url, @Value("${spring.datasource.username}") String userid, @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.userid = userid;
        this.password = password;
    }

    public List<Bureau> readall() throws Exception{
        List<Bureau> lc = new ArrayList<>();
        Connection dbConnect=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnect = DriverManager.getConnection(url, userid, password);
            // connexion avec le login et le password
            // et récupération d'un objet connection
        }
        catch (Exception e){
            System.out.println("erreur : "+e);
            System.exit(0);
        }
        try(PreparedStatement pstm = dbConnect.prepareStatement("select * from APIBUREAU")) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idbur= rs.getInt("ID_BUREAU");
                String sigle = rs.getString("SIGLE");
                String tel = rs.getString("TEL");
                lc.add(new Bureau(idbur, sigle, tel));
            }
            if(lc.isEmpty())throw new Exception("aucun bureaux");
            return lc;

        } catch (Exception e) {
            throw new Exception("Erreur de lecture " + e.getMessage());
        }
    }
}

