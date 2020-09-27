package com.poi.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class POIData {

    String poiName;
    String poiCategory;
    String poiStatus;

    public POIData(String poiName, String poiCategory, String poiStatus) {
        this.poiName = poiName;
        this.poiCategory = poiCategory;
        this.poiStatus = poiStatus;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiCategory() {
        return poiCategory;
    }

    public void setPoiCategory(String poiCategory) {
        this.poiCategory = poiCategory;
    }

    public String getPoiStatus() {
        return poiStatus;
    }

    public void setPoiStatus(String poiStatus) {
        this.poiStatus = poiStatus;
    }

    @Override
    public String toString() {
        return "com.poi.model.POIData{" +
                "poiName='" + poiName + '\'' +
                ", poiCategory='" + poiCategory + '\'' +
                ", poiStatus='" + poiStatus + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof POIData)) return false;
        POIData poiData = (POIData) o;
        return poiName.equals(poiData.poiName) &&
                poiCategory.equals(poiData.poiCategory) &&
                poiStatus.equals(poiData.poiStatus);
    }


    @Override
    public int hashCode() {
        return Objects.hash(poiName, poiCategory, poiStatus);
    }


}

