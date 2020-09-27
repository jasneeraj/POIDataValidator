package com.poi.service;

import com.poi.model.POIData;
import com.poi.validator.POIDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class POIAllReports implements POIReport {

    private static final Logger logger = LoggerFactory.getLogger(POIAllReports.class);

    @Autowired
    POIDataValidator poiDataValidator;

    @Override
    public void generateReport() {
        if(null != poiDataValidator) {
            Map<POIData, String> validatedMap = poiDataValidator.getValidatedMessageForPersistence();

            for (Map.Entry<POIData,String> entry : validatedMap.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
            }
        }else{
            logger.info("Validated Map is not available");
        }

    }
}
