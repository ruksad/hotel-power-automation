package com.hotel.powerautomation.model;

import java.util.ArrayList;
import java.util.List;
import com.hotel.powerautomation.model.abstractmodels.Corridor;
import com.hotel.powerautomation.model.input.InPut;
import lombok.Data;

@Data
public class Hotel {

  private List<Floor> floors = new ArrayList<>();

  public static Hotel initializeHotel(InPut inPut){
    Hotel hotel=new Hotel();
    for(int i=0;i<inPut.getNoOfFloors();i++){
      final Floor floor = new Floor();
      floor.setFloorName("Floor "+(i+1));

      for(int j=0;j<inPut.getNoOfMainCorridors();j++){

        final MainCorridor mainCorridor = new MainCorridor();
        mainCorridor.setCorridorName("Main corridor "+(j+1));

        for(int k=0;k<inPut.getNoOfSubCorridors();k++){
          final SubCorridor subCorridor = new SubCorridor();
          subCorridor.setSubCorridorName("Main corridor "+(k+1));
        }
      }
    }
    return hotel;
  }

}
