grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

forkConfig = [maxMemory: 2048, minMemory: 64, debug: false, maxPerm: 512]
grails.project.fork = [
  test: forkConfig, // configure settings for the test-app JVM
  run: forkConfig, // configure settings for the run-app JVM
  war: forkConfig, // configure settings for the run-war JVM
  console: forkConfig // configure settings for the Swing console JVM
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
		grailsCentral()
		
        mavenLocal()
        mavenCentral()
		mavenRepo 'http://repo.spring.io/milestone'
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime 'mysql:mysql-connector-java:5.1.24'
    }

    plugins {
		build ":tomcat:7.0.54"

        compile ":scaffolding:2.1.1"
        compile ":cache:1.1.8"
		compile ":codenarc:0.23"
		compile ":jslint:0.6"
		compile ":quartz:1.0.2"
		compile ":quartz-monitor:1.0"
		compile ':spring-security-core:2.0-RC4'
		compile ':spring-security-ldap:2.0-RC2'
		compile ':csv:0.3.1'

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.19"
        runtime ":jquery:1.11.1"
        runtime ":resources:1.2.14"
    }
}

codenarc.extraIncludeDirs=['grails-app/jobs']
codenarc.reports = {
	// Each report definition is of the form:
	//    REPORT-NAME(REPORT-TYPE) {
	//        PROPERTY-NAME = PROPERTY-VALUE
	//        PROPERTY-NAME = PROPERTY-VALUE
	//    }

	MyHtmlReport('html') {                  // Report type is 'html'
		outputFile = 'target/CodeNarcReport.html'
		title = 'CodeNarc Report'
	}
codenarc.properties = {
		GrailsDomainHasEquals.enabled=false
		GrailsDomainHasToString.enabled=false
	}
}