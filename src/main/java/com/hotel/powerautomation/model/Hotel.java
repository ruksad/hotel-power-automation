package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Hotel {

  private List<Floor> floors = new ArrayList<>();

}
