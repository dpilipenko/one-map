package com.rosetta.onemap

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;

class IpFilters {

	private static final Log log = LogFactory.getLog(this)
	
    def filters = {
        all(controller:'*', action:'*') {
            before = {
//				def ipAddress = request.getHeader("Client-IP")
//		        log.info ipAddress
//		        if (!ipAddress)
//		            log.info request.getHeader("X-Forwarded-For")
//		
//		        if (!ipAddress)
//		            log.info request.remoteAddr
            }
            after = { Map model ->
            }
            afterView = { Exception e ->

            }
        }
    }
}
