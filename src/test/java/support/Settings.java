package support;

import java.io.File;

public class Settings {
	private static Settings settings;
	private String projectPath;
	private String configurationProp;
	private String dataSource;
	private String dataXMLSource;
	private String resourcesDir;
	private String driverEXEDir;
	private String screenshotsDir;
	private String downloadDir;
	private Settings(){};

	/**
	 * 
	 * Method name  : getInstance
	 * Return types : Settings
	 * Description  :
	 */
	public static Settings getInstance(){
		if(settings==null){
			settings= new Settings();
		}
		return settings;
	}
	/**
	 * Method name  : setProjectPath
	 * Return types : void
	 * Description  :
	 */
	public void setProjectPath(){
		String projectPath = new File(System.getProperty("user.dir")).getAbsolutePath();
		this.projectPath=projectPath;
	}

	/**
	 * 
	 * Method name  : getProjectPath
	 * Return types : String
	 * Description  :
	 */
	public String getProjectPath() {
		if(projectPath==null){
			setProjectPath();
		}
		return projectPath;
	}
	/**
	 * Method name  : getConfigurationProp
	 * Return types : String
	 * Description  :
	 */
	public String getConfigurationProp() {
		if(configurationProp==null){
			setConfigurationProp();
		}
		return configurationProp;
	}

	/**
	 * Method name  : setConfigurationProp
	 * Return types : void
	 * Description  :
	 */
	public void setConfigurationProp() {
		this.configurationProp = getProjectPath()+System.getProperty("file.separator")+"configurations.properties";
	}
	/**
	 * 
	 * Method name  : getDataSource
	 * Return types : String
	 * Description  :
	 */
	public String getDataSource() {
		if(dataSource==null){
			setDataSource();
		}
		return dataSource;
	}
	/**
	 * 
	 * Method name  : setDataSource
	 * Return types : void
	 * Description  :
	 */
	public void setDataSource() {
		this.dataSource = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator");
	}

	
	/**
	 * 
	 * Method name  : getDataSource
	 * Return types : String
	 * Description  :
	 */
	public String getXMLDataSource() {
		if(dataXMLSource==null){
			setXMLDataSource();
		}
		return dataXMLSource;
	}
	/**
	 * 
	 * Method name  : setDataSource
	 * Return types : void
	 * Description  :
	 */
	public void setXMLDataSource() {
		this.dataXMLSource = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"XmlSources"+System.getProperty("file.separator");
	}

	
	/**
	 * 
	 * Method name  : getElementPropertiesDir
	 * Return types : String
	 * Description  :
	 */
	public String getResourcesDir() {
		if(resourcesDir==null){
			setResourcesDir();
		}
		return resourcesDir;
	}
	/**
	 * 
	 * Method name  : setElementPropertiesDir
	 * Return types : void
	 * Description  :
	 */
	public void setResourcesDir() {
		this.resourcesDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : getDriverEXEDir
	 * Return types : String
	 * Description  :
	 */
	public String getDriverEXEDir() {
		if(driverEXEDir==null){
			setDriverEXEDir();
		}
		return driverEXEDir;
	}

	/**
	 * 
	 * Method name  : setDriverEXEDir
	 * Return types : void
	 * Description  :
	 */
	public void setDriverEXEDir() {
		this.driverEXEDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"driver-exe"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : getReportDetailsDir
	 * Return types : String
	 * Description  :
	 */
	public String getScreenshotsDir() {
		if(screenshotsDir==null){
			setScreenshotsDir();
		}
		return screenshotsDir;
	}
	/**
	 * 
	 * Method name  : setReportDetailsDir
	 * Return types : void
	 * Description  :
	 */
	public void setScreenshotsDir() {
		this.screenshotsDir = getProjectPath()+System.getProperty("file.separator")+"screenshots"+System.getProperty("file.separator");
	}
	
	public void setDownloadFolder() {
		this.downloadDir = getProjectPath()+System.getProperty("file.separator")+"downloads"+System.getProperty("file.separator");
	}
	
	
	public String getDownloadFolder() {
		if(downloadDir==null){
			setDownloadFolder();
		}
		return downloadDir;
	}
}
