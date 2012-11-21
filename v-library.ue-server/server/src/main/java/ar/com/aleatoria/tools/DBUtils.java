package ar.com.aleatoria.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.NoSuchColumnException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import ar.com.aleatoria.utils.exceptions.LoadConfigurationFailException;
import ar.com.aleatoria.utils.exceptions.LoadFixtureFileFailException;


public class DBUtils {
	public static String FIXTURE_PATH = "src/main/resources/fixtures/";
	
	/**
	 * Gets the DB connection taking the db configuration from our jdbc file 
	 * @return IDatabaseConnection connection to execute the operations over the configured DB
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DatabaseUnitException
	 */
	 public static IDatabaseConnection getDBConnection() throws LoadConfigurationFailException{
		// Read properties file.
		IDatabaseConnection connection=null;
		 try{
			 Properties properties = new Properties();
			 properties.load(new FileInputStream("src/main/resources/env/jdbc.properties"));
			 Class.forName(properties.getProperty("jdbc.driver"));
			 Connection jdbcConnection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.user"), properties.getProperty("jdbc.password"));
			 jdbcConnection.createStatement().execute("set search_path to '" + properties.getProperty("hibernate.default_schema") + "'");
			 connection = new DatabaseConnection(jdbcConnection);
		 }catch (Exception e) {
			 throw new LoadConfigurationFailException();
		}
		return connection;
	}
	 
	/**
	 * @param fileName
	 * @return The dataSet with the fixture (of the given filename) data loaded 
	 * @throws DataSetException
	 * @throws FileNotFoundException
	 */
	public static FlatXmlDataSet getDataSet(String fileName) throws LoadFixtureFileFailException{
		FlatXmlDataSet dataSet=null;
		try{
			dataSet=new FlatXmlDataSetBuilder()
				.build(new FileInputStream(FIXTURE_PATH + fileName + ".xml"));
		}catch (Exception e) {
			throw new LoadFixtureFileFailException();
		}
		return dataSet;
	}

	public static void updateCurrentSequences(IDatabaseConnection connection,
			IDataSet build) throws DataSetException, SQLException {
		String [] tableNames = build.getTableNames();
		for (int i = 0; i<tableNames.length; i++){
			try{
				build.getTable(tableNames[i]).getTableMetaData().getColumnIndex("id");
				String tableName= "vlib." + tableNames[i];
				String sequenceTableName = tableName+"_id_seq";
				String query = "SELECT setval('"+sequenceTableName+ "', (SELECT MAX(t.id) FROM "+ tableName + " t))";
				System.out.println(query);
				connection.getConnection().createStatement().
				execute(query);
			} catch (NoSuchColumnException e){System.out.println("Notice: the table "+ tableNames[i] + "doesn't have id column");}
		}
	}
	
}
