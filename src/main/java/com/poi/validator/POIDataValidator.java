package com.poi.validator;

import com.poi.model.POIData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.poi.model.POIStatus.*;

@Component
public class POIDataValidator implements POIValidator, Comparator {

    private static  final Logger logger = LoggerFactory.getLogger(POIDataValidator.class);

    // Store Validated messages into TreeMap for Persistence into downstream
    HashMap<POIData, String> validatedMessageForPersistence = new HashMap<>();

    // Store validation failure messages into TreeMap for manual verification
    HashMap<POIData, String> failureMessageForPersistence = new HashMap<>();

    @Override
    public void doValidate(POIData poiData) {

        String validCategories;
        List<String> categoriesList = new ArrayList<>();

        // Unfortunately Configuration for loading properties not working on my system as well as with Defaults,
        // So using File reader to load properties
        // Is to make the Category filters further extendable.

        FileReader reader = null;
        try {
            reader = new FileReader(System.getProperty("user.dir")+"/src/main/resources/application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p = new Properties();
        try {
            p.load(reader);
            validCategories = p.getProperty("validCategoriesList");
            categoriesList = Arrays.asList(validCategories.split(","));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if( validatePOIName(poiData) && validatePOICategory(poiData, categoriesList) && validatePOIStatus(poiData)){
            poiData.setPoiStatus(VALIDATED);
            validatedMessageForPersistence.put(poiData,VALIDATED);
            logger.info("POI object added into Validated Map for Persistence "+ poiData.toString());
        }else{
            poiData.setPoiStatus(ERROR);
            failureMessageForPersistence.put(poiData,ERROR);
            logger.info("POI object added into error Map for Manual Verification "+ poiData.toString());
        }

    }

    boolean validatePOIName(POIData poiData){
        return !StringUtils.isEmpty(poiData.getPoiName());
    }

    boolean validatePOICategory(POIData poiData, List<String> categoriesList){
        return categoriesList.contains(poiData.getPoiCategory());
    }

    boolean validatePOIStatus(POIData poiData){
        return !StringUtils.isEmpty(poiData.getPoiName()) && NEW.equals(poiData.getPoiStatus());
    }

    public Map<POIData, String> getValidatedMessageForPersistence() {
        return validatedMessageForPersistence;
    }

    public Map<POIData, String> getFailureMessageForPersistence() {
        return failureMessageForPersistence;
    }

    @Override
    public int compare(Object o1,Object o2) {
        if(((POIData)o1).getPoiCategory() == ((POIData)o2).getPoiCategory()) {
            if (((POIData) o1).getPoiName() == ((POIData) o2).getPoiName()) {
                return 0;
            }else if (((POIData) o1).getPoiName() == ((POIData) o2).getPoiName()) {
                return 0;
            } return 1;
                }
        else
            return 1;
    }

}
