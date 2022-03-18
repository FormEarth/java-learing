package com.example.demo.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CodeGenerate {

	final static String template_path = "C:/Users/Administrator/Desktop/";
	final static String default_charset = "UTF-8";
	final static String code_path = "D:/java workspace/qiyuan_projects/snmp-mb/trunk/src/main/java/com/helios/snmp/modules/core/controller/";

	public static void gengrateController(String tableName, String className, String humpName) throws IOException {
		String template = readText();
		String text_new = template.replaceAll("table_name", tableName);
		text_new = text_new.replaceAll("class_name", className);
		text_new = text_new.replaceAll("hump_name", humpName);
		String fileName = writeTxt(text_new, className + "Controller.java");
		System.out.println("file path:"+fileName);
	}

	public static String readText() throws IOException {
		String pathname = template_path + "template.java";
		String fileContent = "";
		File f = new File(pathname);
		if (f.isFile() && f.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(f), default_charset);
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent += line+'\n';
			}
			read.close();
		}
		return fileContent;
	}

	public static String writeTxt(String value, String fileName) throws IOException {
		FileWriter fw = null;
		File f = null;
		try {
			f = new File(code_path + fileName);
			fw = new FileWriter(f, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(new String(value.getBytes(), default_charset));
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}
	
	String underscoreTocamel(String underscore) {
		return null;
	}

	public static void main(String[] args) throws IOException {

		String tableName = "sub_config_system";
		String className = "SubConfigSystem";
		String firstChar = String.valueOf(className.charAt(0));
		String humpName = firstChar.toLowerCase() + className.substring(1);
		System.out.println(humpName);
		gengrateController(tableName, className, humpName);

	}
	 
	//dev_config_modem  DeviceConfigModem
	//dev_config_net    DeviceConfigNet
	//dev_config_site   DeviceConfigSite
	//dev_config_snmp   DeviceConfigSnmp
	//dev_config_system DeviceConfigSystem
	//dev_config_vpn    DeviceConfigVPN

	//installation_config_alarm  InstallationConfigAlarm
	//installation_config_site   InstallationConfigSite
	//installation_config_system InstallationConfigSystem
	
	//sub_config_advanced  				SubConfigAdvanced
	//sub_config_channel   				SubConfigChannel
	//sub_config_digital_optical_module	SubConfigDigitalOpticalModule
	//sub_config_index					SubConfigIndex
	//sub_config_alarm             		SubConfigAlarm
	//sub_config_system					SubConfigSystem
	
}
