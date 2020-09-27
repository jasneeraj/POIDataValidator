package com.poi;

import com.poi.model.POIData;
import com.poi.validator.POIDataValidator;
import com.poi.validator.POIValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.poi.model.POIStatus.NEW;


/**
 * As a Main class we need to add Kafka Steam Cloud API to consume messages from there
 * After reading from kafka we will pass that message to validation engine
 * If Success the POI Data object will be enriched as Validated Status for Further processing or
 * add in failure Map for Manual verification
 *
 * Same Maps can be used to generate reports for Failure and Success messages and calculate throuput
 *
 * To avoid unnecessary calls to read from DB or avoid I/O for reports make the Low latency system,
 * So read details of POIData messages from in memory MAPs itself.
 *
 **/


@SpringBootApplication
public class POIDataValidatorApplication {

	static final Logger logger = LoggerFactory.getLogger(POIDataValidatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(POIDataValidatorApplication.class, args);

		POIValidator poiValidator = new POIDataValidator();

		 /*
			Need to add logic to read from Kafka Stream
			and parse into POIData object
		 */

		logger.info("Creating POI object");
		POIData poiData = new POIData("BeatAll Sports","SPORTS",NEW);
		logger.info("POI object created as "+ poiData.toString());

		logger.info("Started processing POI object as "+ poiData.toString());
		poiValidator.doValidate(poiData);

		logger.info("POI object processed as "+ poiData.toString());
	}

}
