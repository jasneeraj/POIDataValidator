package com.poi.validator;

import com.poi.model.POIData;
import org.junit.jupiter.api.Test;

import static com.poi.model.POIStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POIDataValidatorApplicationTest {

    POIValidator poiDataValidator = new POIDataValidator();

    @Test
    public void whenAllDetailsCorrect() {
        POIData poiData = new POIData("TestName", "SPORTS", NEW);
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),VALIDATED);
    }

    @Test
    public void whenPOINameIsBlank() {
        POIData poiData = new POIData("", "SPORTS", NEW);
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),ERROR);
    }

    @Test
    public void whenPOICategoryIsBlank() {
        POIData poiData = new POIData("TestName", "", NEW);
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),ERROR);
    }

    @Test
    public void whenCategoryIsOutOfValidList() {
        POIData poiData = new POIData("TestName", "GAMES", NEW);
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),ERROR);
    }

    @Test
    public void whenPOIMessageStatusIsNotNew() {
        POIData poiData = new POIData("TestName", "SPORTS", VALIDATED);
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),ERROR);
    }

    @Test
    public void whenPOIMessageStatusIsBlank() {
        POIData poiData = new POIData("TestName", "SPORTS", "");
        poiDataValidator.doValidate(poiData);
        assertEquals(poiData.getPoiStatus(),ERROR);
    }
}
