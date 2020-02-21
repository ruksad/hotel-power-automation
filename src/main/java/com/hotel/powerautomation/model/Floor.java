package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import lombok.Data;

@Data
public class Floor {

  private String floorName;
  private List<Corridor> corridors = new ArrayList<>();

}
