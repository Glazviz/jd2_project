package it.academy.data;

import java.io.Serializable;
import java.util.Objects;

public class SensorsData implements Serializable {

    private int id;
    private String sensorsName;
    private String sensorsModel;
    private String sensorsType;
    private int sensorsRangeFrom;
    private int sensorsRangeTo;
    private String sensorsUnit;
    private String sensorsLocation;
    private String sensorsDescriptions;

    public int getId() {
        return id;
    }

    public String getSensorsName() {
        return sensorsName;
    }

    public String getSensorsModel() {
        return sensorsModel;
    }

    public String getSensorsType() {
        return sensorsType;
    }

    public int getSensorsRangeFrom() {
        return sensorsRangeFrom;
    }

    public int getSensorsRangeTo() {
        return sensorsRangeTo;
    }

    public String getSensorsUnit() {
        return sensorsUnit;
    }

    public String getSensorsLocation() {
        return sensorsLocation;
    }

    public String getSensorsDescriptions() {
        return sensorsDescriptions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSensorsName(String sensorsName) {
        this.sensorsName = sensorsName;
    }

    public void setSensorsModel(String sensorsModel) {
        this.sensorsModel = sensorsModel;
    }

    public void setSensorsType(String sensorsType) {
        this.sensorsType = sensorsType;
    }

    public void setSensorsRangeFrom(int sensorsRangeFrom) {
        this.sensorsRangeFrom = sensorsRangeFrom;
    }

    public void setSensorsRangeTo(int sensorsRangeTo) {
        this.sensorsRangeTo = sensorsRangeTo;
    }

    public void setSensorsUnit(String sensorsUnit) {
        this.sensorsUnit = sensorsUnit;
    }

    public void setSensorsLocation(String sensorsLocation) {
        this.sensorsLocation = sensorsLocation;
    }

    public void setSensorsDescriptions(String sensorsDescriptions) {
        this.sensorsDescriptions = sensorsDescriptions;
    }

    @Override
    public String toString() {
        return "SensorsData{" +
                "id=" + id +
                ", sensorsName='" + sensorsName + '\'' +
                ", sensorsModel='" + sensorsModel + '\'' +
                ", sensorsType='" + sensorsType + '\'' +
                ", sensorsRangeFrom=" + sensorsRangeFrom +
                ", sensorsRangeTo=" + sensorsRangeTo +
                ", sensorsUnit='" + sensorsUnit + '\'' +
                ", sensorsLocation='" + sensorsLocation + '\'' +
                ", sensorsDescriptions='" + sensorsDescriptions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorsData)) return false;

        SensorsData that = (SensorsData) o;

        if (id != that.id) return false;
        if (sensorsRangeFrom != that.sensorsRangeFrom) return false;
        if (sensorsRangeTo != that.sensorsRangeTo) return false;
        if (!Objects.equals(sensorsName, that.sensorsName)) return false;
        if (!Objects.equals(sensorsModel, that.sensorsModel)) return false;
        if (!Objects.equals(sensorsType, that.sensorsType)) return false;
        if (!Objects.equals(sensorsUnit, that.sensorsUnit)) return false;
        if (!Objects.equals(sensorsLocation, that.sensorsLocation))
            return false;
        return Objects.equals(sensorsDescriptions, that.sensorsDescriptions);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sensorsName != null ? sensorsName.hashCode() : 0);
        result = 31 * result + (sensorsModel != null ? sensorsModel.hashCode() : 0);
        result = 31 * result + (sensorsType != null ? sensorsType.hashCode() : 0);
        result = 31 * result + sensorsRangeFrom;
        result = 31 * result + sensorsRangeTo;
        result = 31 * result + (sensorsUnit != null ? sensorsUnit.hashCode() : 0);
        result = 31 * result + (sensorsLocation != null ? sensorsLocation.hashCode() : 0);
        result = 31 * result + (sensorsDescriptions != null ? sensorsDescriptions.hashCode() : 0);
        return result;
    }
}
