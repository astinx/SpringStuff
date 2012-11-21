package tools;


import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import utils.exceptions.LoadConfigurationFailException;
import utils.exceptions.LoadFixtureFileFailException;

public class LoadFixture {

	/**
	 * Before run this app first time:
	 *   1 drop cascade all tables
	 *   2 Restart tomcat for create empty tables
	 *   3 Check the fixture xml content is sorted, to sort move the dependecy elements to top of file. 
	 *   4 Right click in LoadFixture.java > run configuration > java Application > right clik > new > configure arguments > save conf 
	 *   5 Run this app as java app (alt+shift+x+j).
	 *   
	 *  ** After first Time skip steps 3 and 4
	 *   
	 *  ** For large fixture files this could spend several minutes
	 * 
	 * @param args
	 * @throws Exception
	 * @param [0] Insert mode ("insert"=inserts the fixture data) or "clean_insert"=drop DB and inserts the fixture data)
	 * @param [1] Fixture filename 
	 * @usage "insert full" for load the fixture full.xml in insert mode (don't drop db). Tables must be ordered by foraign key dependencies.
	 * 
	 */
	
	public static void main(String[] args) throws Exception {
		
		try {
		    IDatabaseConnection connection = DBUtils.getDBConnection();
			String mode = args[0];
			String fileName = args[1];
			FlatXmlDataSet build = DBUtils.getDataSet(fileName);
			
			if (mode.equals("insert")) {
				DatabaseOperation.INSERT.execute(connection, build);
			} else if (mode.equals("clean_insert")) {
				DatabaseOperation.CLEAN_INSERT.execute(connection, build);
			} else {
				throw new Exception();
			}
			DBUtils.updateCurrentSequences(connection, build);
			System.out.println("El fixture se ha cargado en la base de datos exitosamente");
			
		}catch (LoadConfigurationFailException e) {
    		System.out.println("No se pudo cargar la configuración");
		}catch (LoadFixtureFileFailException e) {
    		System.out.println("No se pudo cargar los datos del fixture en DB");
		}


	}
}
