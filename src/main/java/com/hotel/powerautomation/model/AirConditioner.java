package com.hotel.powerautomation.model;

import com.hotel.powerautomation.model.abstractmodels.Device;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AirConditioner implements Device {

  //TODO this should be added the property file
  public static final int AC_POWER_UNITS = 10;

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

  @Override
  public boolean state() {
    return state;
  }

  @Override
  public String stateToString() {
    return this.state?"ON":"OFF";
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("AC :");
    sb.append(stateToString()).append("\n");
    return sb.toString();
  }
}
