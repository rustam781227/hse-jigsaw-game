package com.example.salpagarov_rustam_201_hw5;

public class Time {
    private int minute;
    private int second;

    public Time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    public String getCurrentTime() {
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }
        return minute + ":" + second;
    }

    public void oneSecondPassed() {
        ++second;
        if (second == 60) {
            ++minute;
            second = 0;
        }
    }
}
