grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

forkConfig = [maxMemory: 2048, minMemory: 64, debug: true, maxPerm: 512]
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
        // runtime 'mysql:mysql-connector-java:5.1.24'
    }

    plugins {
		build ":tomcat:7.0.47"

        compile ":scaffolding:2.0.1"
        compile ":cache:1.1.1"
		compile ":spring-security-ldap:2.0-RC2"
		
		runtime ":hibernate:3.6.10.6"
        runtime ":database-migration:1.3.8"
        runtime ":jquery:1.10.2.2"
        runtime ":resources:1.2.1"
    }
}
