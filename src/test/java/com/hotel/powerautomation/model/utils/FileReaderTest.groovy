package com.hotel.powerautomation.model.utils

import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class FileReaderTest extends Specification {

    def fileReader=new FileReader();
    def "Read"() {
        given:
        def filepath=new ClassPathResource("input.txt");
        when:
        def input=fileReader.read(filepath.getURL().file)
        then:
        input.noOfFloors>0

    }


}
