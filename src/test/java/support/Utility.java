package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 
 * @author VNatarajan
 *
 */
public class Utility {

	private Properties properties;
	private Settings settings = Settings.getInstance();
	String resourcesPath=settings.getResourcesDir();
	private static Utility utility;
	private Utility(){};

	public static Utility getInstance(){
		if(utility==null){
			utility= new Utility();
		}
		return utility;
	}

	/**
	 * 
	 * @param testId
	 * @param runtimeDataName
	 * @param runtimeValue
	 */
	public synchronized void setRuntimeData(String testId,String runtimeDataName,String runtimeValue){

		FileOutputStream fileOut=null;

		try{
			File file = new File(resourcesPath+"RuntimeValues.properties");
			fileOut = new FileOutputStream(file);
			if(properties==null){
				properties = new Properties();
			}
			properties.setProperty(testId+"_"+runtimeDataName, runtimeValue);
		}catch(Exception e){
			e.printStackTrace();
			properties.setProperty(testId+"_"+runtimeDataName, "NOT SET");

		}finally{
			try{
				properties.store(fileOut, "Runtime Test Data update");
				fileOut.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param referenceTestId
	 * @param runtimeDataName
	 * @return
	 */
	
	public synchronized String getRuntimeData(String referenceTestId,String runtimeDataName){
		
		File file = new File(resourcesPath+"RuntimeValues.properties");
		String propValue="PROP NOT FOUND";
		try{
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			propValue=properties.getProperty(referenceTestId+"_"+runtimeDataName);
			fileInput.close();
			return propValue;
		}catch(Exception e){
			return propValue;
		}
	}
	
}