package com.rosetta.onemap

import grails.test.mixin.Mock

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import spock.lang.Specification


@Mock(IpFilters)
class IpFilterFiltersSpec extends Specification {
	private static final Log log = LogFactory.getLog(this)
	
    def setup() {
    }

    def cleanup() {
    }

    void "test logging"() {
		when:
		log.info "test logging from within IpFilterFiltersSpec. Go Bucks!"
		then:
		true
    }
}

