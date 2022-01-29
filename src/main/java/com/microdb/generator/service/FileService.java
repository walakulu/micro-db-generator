package com.microdb.generator.service;

import java.io.File;

public interface FileService {

	int saveToFile(String fileName, String dataString);

	// give file name with folder path
	String readFileContent(String fileName);

	// give file name only ( since we load it from classpath)
	File readFileFromClasspath(String fileName);

}
