package com.test1.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pankaj on 11/19/15.
 */
public class HourlyDataModel  implements Parcelable {
    private String time;
    private String summary;
    private String icon;
    private String cloudCover;
    private String precipIntensity;
    private String precipProbablity;
    private String temperature;
    private String dewPoint;
    private String windSpeed;
    private String humidity;
    private String visibility;
    private String pressure;

    public HourlyDataModel(){

    }

    public HourlyDataModel(Parcel source) {

        String[] data= new String[4];
    //new String[]{this.time,this.summary,this.temperature,this.icon});
        source.readStringArray(data);
        this.time= data[0];
        this.summary= data[1];
        this.temperature=  data[2];
        this.icon=  data[3];
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(String precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public String getPrecipProbablity() {
        return precipProbablity;
    }

    public void setPrecipProbablity(String precipProbablity) {
        this.precipProbablity = precipProbablity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }


    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.time,this.summary,this.temperature,this.icon});

    }

    public static final Parcelable.Creator<HourlyDataModel> CREATOR= new Parcelable.Creator<HourlyDataModel>() {

        @Override
        public HourlyDataModel createFromParcel(Parcel source) {
            return new HourlyDataModel(source);  //using parcelable constructor
        }

        @Override
        public HourlyDataModel[] newArray(int size) {
            return new HourlyDataModel[size];
        }
    };
}
