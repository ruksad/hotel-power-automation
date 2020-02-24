package com.hotel.powerautomation.model

import spock.lang.Specification
import uk.co.jemos.podam.api.PodamFactoryImpl

class SubCorridorTest extends Specification {

    def "createCorridor"(){
        given:
        //def light=podamFactory.manufacturePojo()
        when:
        def list=SubCorridor.createCorridor(5);
        then:
        list.size()>0;
        list.get(0).getDevices().get(0).stateToString()=="OFF"

    }

    def "update"(){
        given:
        def subcor=new SubCorridor();
        def podamFactory=new PodamFactoryImpl();
        def cor=podamFactory.manufacturePojo(SubCorridor.class)

        when:
        subcor.update(cor,true);
        then:
        cor.subCorridorName !=null

    }
}
