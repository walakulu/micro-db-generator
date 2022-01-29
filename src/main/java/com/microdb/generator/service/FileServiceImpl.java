package com.microdb.generator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

	@Override
	public int saveToFile(String fileName, String dataString) {
		try {

			File file = new File(fileName);
			// make sure parent dir exists (else created)
			file.getParentFile().mkdir();

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(dataString);
			bw.newLine();
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("File Save Failure..!");
			throw new IllegalStateException("File save failure");
		}

		return 1;
	}

	@Override
	public String readFileContent(String fileName) {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line);
			}
		}
		catch (IOException e) {
			throw new IllegalStateException("File content read failure :" + fileName);
		}
		return resultStringBuilder.toString();
	}

	@Override
	public File readFileFromClasspath(String fileName) {

		File mappingFile;
		try {
			mappingFile = new ClassPathResource(fileName).getFile();
		}
		catch (IOException e) {
			LOGGER.error("Fail to read YAML mapping file..!", e);
			throw new IllegalStateException("Classpath file read failure :" + fileName);
		}

		return mappingFile;
	}

}
