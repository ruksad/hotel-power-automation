package com.hotel.powerautomation.model;

public class AirConditioner implements Device {

  //TODO this should be added the property file
  public static final int AC_POWER_UNITS = 15;

  private boolean state;

  public AirConditioner() {
    this.state=true;
  }

  @Override
  public void action(boolean turnOn) {
    state = turnOn;
  }

  @Override
  public int units() {
    return AC_POWER_UNITS;
  }
}
