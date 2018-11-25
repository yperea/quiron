# Project Quiron

### Problem Statement
Although health sector is traditionally a conservative and slow-adapting industry, there is an increasing volume of new tools providing new alternatives to provide medical care to patients in very innovative ways, such as web platforms where video-on-demand VisitForm can be made with a variety of physicians, therapists, specialists and health professionals in general. For instance, websites like [amwell.com](https://www.amwell.com) or [livehealthonline.com](https://www.livehealthonline.com).

However, if these types of tools serve as an excellent way to provide wider access to medical care, sometimes we would like to know what happens next ... after the visit is over.

Currently, the industry, in general, does not track whether medical procedures or diagnoses work or not, and frequently patients don't monitor their health after a treatment as seriously as they should.
According to studies, doctors have a hard time finding any data exploring the long-term effects of procedures and treatements, because 84 percent of U.S.-recognized medical specialties have no data registries at all.

I would like to build a platform that allows for better control of medical treatments after the visit by tracking the progress of patients, giving them a way to evaluate the outcome of treatment and for physicians a tool to measure their effectiveness in their diagnoses and prescriptions.

### Project Technologies/Techniques
* Security/Authentication
    * Tomcat's JDBC Realm Authentication
    * Admin role: create/read/update/delete (crud) of all data
    * Patient role: create/read/update/delete (crud) their own profiles, schedules, in general any data they have entered previously.
    * Provider role: create/read/update/delete (crud) their own profiles, diagnostics, in general any data they have entered previously.
    * All: anyone can view information pages (no login)
* Database
    * MySQL
    * Store patients and providers
    * Store all data of VisitForm and treatments
* ORM Framework
    * Hibernate 5
* Dependency Management
    * Maven
* Continuing Integration and Delivery
    * AWS CodePipeline
    * AWS CodeDeploy
    * Jenkins
* Web Services consumed using Java
    * Amazon Simple Email Service (TBA)
* CSS
    * Bootstrap 4
* Data Validation
    * Bootstrap Validator for front end
* Logging
    * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting.
* Hosting
    * AWS
* Independent Research Topic/s
    * CI tools in AWS
* Project Lombok to eliminate boilerplate code like getters/setters/equals
* Unit Testing
    * JUnit tests to achieve 80%+ code coverage
* IDE: IntelliJ IDEA

### Supporting Materials
* [Screen Design](docs/design/Screens.md)
* [Database Design](db/erd/ERDProjectQuiron.png)

### [Project Plan](PROJECTPLAN.md)


