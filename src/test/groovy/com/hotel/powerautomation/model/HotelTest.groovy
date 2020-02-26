package com.hotel.powerautomation.model

import com.hotel.powerautomation.model.input.Input
import com.hotel.powerautomation.model.input.Move
import spock.lang.Specification
import uk.co.jemos.podam.api.PodamFactoryImpl

class HotelTest extends Specification {
    def hotel=new Hotel();
    def podamFactory=new PodamFactoryImpl();
    def "InitializeHotel"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
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
        hotel.getFloors().get(0).currentPowerConsumption()==50
    }

    def "InitializeHotel with on light turned off"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
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
        hotel.getFloors().get(0).getMainCorridors().get(0).getDevices().get(0).action(false)
        then:
        Objects.nonNull(hotel.getFloors())
        hotel.getFloors().get(0).currentPowerConsumption()==45
    }

    def "consumeMoves with different move floors"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(2)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(2)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with same move floors"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(1)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(2)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with same move  same corridor floors"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)
        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(1)
        move2.setMovement(true)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(2)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with no movement with different floors"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(false);
        move1.setSubCorridorNumber(2)
        move1.setNoMovementsForMinutes(3)

        move2.setFloorNumber(2)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(2)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with same corridor same floor with less time no movement"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(1)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(1)
        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with same corridor same floor 3 moves"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)
        def move3=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(1)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(3)

        move3.setFloorNumber(1)
        move3.setMovement(true);
        move3.setSubCorridorNumber(2)

        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)
        list.add(move3)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

    def "consumeMoves with same corridor different floor floor 3 moves"() {
        given: " default hotel implementation"
        def input=podamFactory.manufacturePojo(Input.class)
        input.setNoOfFloors(2)
        input.setNoOfMainCorridors(2)
        input.setNoOfSubCorridors(2);
        def move1=podamFactory.manufacturePojo(Move.class)
        def move2=podamFactory.manufacturePojo(Move.class)
        def move3=podamFactory.manufacturePojo(Move.class)

        move1.setFloorNumber(1)
        move1.setMovement(true);
        move1.setSubCorridorNumber(2)

        move2.setFloorNumber(1)
        move2.setMovement(false)
        move2.setSubCorridorNumber(2)
        move2.setNoMovementsForMinutes(3)

        move3.setFloorNumber(1)
        move3.setMovement(true);
        move3.setSubCorridorNumber(1)

        def list=input.getMoves()
        list.clear()
        list.add(move1);
        list.add(move2)
        list.add(move3)

        when:
        def returns =hotel.consumeMoves(input)
        then:
        returns
    }

}
