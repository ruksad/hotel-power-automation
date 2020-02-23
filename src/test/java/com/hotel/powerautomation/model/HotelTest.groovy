package com.hotel.powerautomation.model

import com.hotel.powerautomation.model.input.InPut
import com.hotel.powerautomation.model.input.Move
import spock.lang.Specification
import uk.co.jemos.podam.api.PodamFactoryImpl

import java.util.stream.Collectors

class HotelTest extends Specification {
    def hotel=new Hotel();
    def podamFactory=new PodamFactoryImpl();
    def "InitializeHotel"() {
        given:
        def input=podamFactory.manufacturePojo(InPut.class)
        input.setNoOfFloors(5)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)
        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)
        move2.setFloorNumber(2)
        move2.setMovement(false)
        move2.setSubCorridorNumber(1)
        move2.setNoMovementsForMinutes(2)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def hotel=hotel.initializeHotel(input)
        then:
        Objects.nonNull(hotel.getFloors())
    }

    def "getDevice"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=hotel.getDevice();
        then:
        list.size()>0;
        list.get(0).state()

    }

    def "createMainCorridor"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=hotel.createMainCorridor(5);
        then:
        list.size()>0;
        list.get(0).getMainCorridorName().equals("Main corridor 1")

    }
}
