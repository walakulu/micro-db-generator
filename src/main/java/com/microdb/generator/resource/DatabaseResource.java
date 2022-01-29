package com.microdb.generator.resource;

import com.microdb.generator.dto.CreateDatabaseRequestDto;
import com.microdb.generator.dto.CreateDatabaseResponseDto;
import com.microdb.generator.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/databases")
public class DatabaseResource {

	@Autowired
	DatabaseService databaseService;

	@PostMapping
	public CreateDatabaseResponseDto createDatabase(@RequestBody CreateDatabaseRequestDto createDatabaseRequestDto) {
		// throw exception if database name not in standard format
		// <namePrefix>_DAY_<numOfCacheDays>

		String dbNamePrefix = createDatabaseRequestDto.getDatabaseNamePrefix();
		int cacheTimePeriod = createDatabaseRequestDto.getCacheTimePeriod();
		String databaseName = dbNamePrefix + "_Day_" + cacheTimePeriod;
		databaseService.createDatabase(databaseName, cacheTimePeriod);

		return CreateDatabaseResponseDto.builder().withDatabaseName(databaseName).withCacheTimePeriod(cacheTimePeriod)
				.build();
	}

}
