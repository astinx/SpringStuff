package tools;

import java.io.FileOutputStream;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import utils.exceptions.LoadConfigurationFailException;

public class DumpDB {

	/**
	 * @param args
	 * @throws Exception
	 * @param [0] Fixture filename 
	 * @usage "full" Dump the db data to full.xml fixture file 
	 */
	
	
	public static void main(String[] args) throws Exception {
		try {
			String fileName = args[0];
		    IDatabaseConnection connection = DBUtils.getDBConnection();
			IDataSet fullDataSet = connection.createDataSet();
			FlatXmlDataSet.write(fullDataSet,
					new FileOutputStream(DBUtils.FIXTURE_PATH  + fileName + ".xml"));
			System.out.println("El fixture "+fileName+" se ha generado exitosamente en "+DBUtils.FIXTURE_PATH);
		}
		catch (LoadConfigurationFailException e) {
    		System.out.println("No se pudo cargar la configuración");
		}catch (Exception e) {
    		System.out.println("No se pudo guardar los datos de DB en el archivo de fixtures");
		}

	}
}
