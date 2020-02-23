package com.hotel.powerautomation.model

import spock.lang.Specification

class MainCorridorTest extends Specification {

    def "createMainCorridor"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=MainCorridor.createCorridor(5);
        then:
        list.size()>0;
        list.get(0).getMainCorridorName().equals("Main corridor 1")

    }
}
