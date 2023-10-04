package be.condorcet.projetapi3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ProjetApi3Application implements CommandLineRunner {
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String userid;
	@Value("${spring.datasource.password}")
	String password;

	public static void main(String[] args) {
		SpringApplication.run(ProjetApi3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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

		try(
				Statement stmt = dbConnect.createStatement();
				//représente une requête SQL
				ResultSet rs = stmt.executeQuery("select * from APIBUREAU ");
				//récupération des données à partir de la table client
				//ensemble des lignes répondant à la requête
		)
		{
			while (rs.next()) {
				String sigle = rs.getString("sigle");
				//ou rs.getString(2);
				String tel = rs.getString("tel");
				//ou rs.getString(3);
				int n = rs.getInt("id_bureau");
				//ou int n= rs.getInt(1) ;
				System.out.println(sigle + " " + tel + " " + n);
			}
		} catch (
				SQLException e) {
			System.out.println("erreur SQL " + e);
		} catch (Exception e) {
			System.out.println("erreur " + e);
		}
		try{
			dbConnect.close();
		} catch (SQLException e) {
			System.out.println("erreur de fermeture de connexion "+e);
		}
	}

}
