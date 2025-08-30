**Assumptions:**
------------
------------
* Architecture Framework used: TOGAF
* Architecture Vision has been developed
* Stakeholders have been Identified.
* Statement of Architecture Work has been created, and business context is validated.
* Architecture Principles and business principles are confirmed and elaborated
* TOGAF library including standard terminology, reusable components etc. are in place
* To be done in a continuous manner as monitor baseline requirements and Identify changed requirement; remove, add, modify, and re-assess priorities
* The description below is closely related to Business, Information & System and Technical Architectures

**Functional Flows:**
-----------------
-----------------
* Onboard Theatre:

![img_11.png](img_11.png)

* Onboard Show:

![img_9.png](img_9.png)

* Ticket Booking:

![img_10.png](img_10.png)

* Ticket Cancellation:
  <<-To Be Done->>
* Offer Management:
<<-To Be Done->>

**Data Model**
----------
----------
* Relational:

![img_1.png](img_1.png)  ![img_2.png](img_2.png)  ![img_3.png](img_3.png)

* No-Sql:

![img_4.png](img_4.png)  ![img_5.png](img_5.png)  ![img_6.png](img_6.png)  ![img_7.png](img_7.png)

**Approximate Data Flow**
---------------------
---------------------
* Theatre Onboarding

![img_15.png](img_15.png)

* Movie Publishing

![img_16.png](img_16.png)

* Ticket Booking

![img_17.png](img_17.png)

**API Specifications**
----------------------
----------------------
* Major APIs:-
  * Theatre Onboarding
    * @PostMapping(value = "/theatre")
      public ResponseEntity<TheatreDto> theatre(@RequestBody TheatreDto theatreDto)
  * Movie Publishing
    * @PostMapping(value = "/movie")
      public ResponseEntity<List<Show>> publishMovie(@RequestBody Shows shows)
  * Ticket Booking
    * @PostMapping(value = "/ticket")
      public ResponseEntity<BookingDetail> movie(@RequestBody BookingDetail booking)

* Other APIs:-
  * <<-To Be Done->>

**Database Design**
--------------------------
--------------------------
* RDBMS (H2) for THEATRE, BOOKING_DETAILS and SEAT_AVAILABILITY data:
  * This data to be persisted is expected to be well-structured.
  * Booking Detail table is expected to have multiple inserts/updates. 
* NOSQL (Mongo-DB) for SHOWS data:
  * This data will involve heavy querying and select statements.
  * This data is also not expected to be updated frequently after it is published.
  * It can be erased after the show period is completed and withdrawn by the theatre.

**Architectural Overview**
--------------------------
--------------------------
Technology Stack
----------------
* Architecture: [Microservices [CQRS/SAGA], Facade, Adapter etc.]
* Resilience: [Hystrix, Retry etc.]
* Build and Orchestration: [Docker, K8S, Cloud Pipelines (eg. Azure Pipeline)]
* Cloud [Azure or AWS]
* Java 8+, Spring [Boot, REST, Kafka, Data-JPA]
* Eventing and Messaging: [Kafka Topics]
* Datastore: Postgres, Schedule Repository [Redis etc.]
* Monitoring: [ELK etc.]

Logical Architecture
---------------------
![img_8.png](img_8.png)

Reference Architecture
----------------------
* The following is the basic reference architecture that has been used to come up with the System Architecture of the given problem statement.
<img width="973" height="442" alt="image" src="https://github.com/user-attachments/assets/6364f21c-f198-488b-b85e-2dbb319a4e42" />
  * Reference: https://learn.microsoft.com/en-us/azure/architecture/microservices/design/patterns

* The following is the generic reference architecture that will be used to design the internal
components wherever applicable
<img width="562" height="283" alt="image" src="https://github.com/user-attachments/assets/917dbad6-2c9d-42b8-8083-1816ef6ab9b0" />


Overall Deployment Architecture
--------------------------------
![img.png](img.png)

Disaster Recovery Architecture:
-------------------------------
Normal
![img_12.png](img_12.png)

App Server Down:
![img_13.png](img_13.png)

DB Server Down:
![img_14.png](img_14.png)

**Key NFR Overview**
----------------
----------------
* Scalability: Horizontal Scaling looks to be a feasible solution for scalability. Auto-scaling feature of the
cloud provider to generate or purge Scheduler instances based on demand.
* Availability: A highly scalable system is highly available. When a node or instance is down, the requests
will be automatically routed to the next available instance or node. This facility will be configured with
Kubernetes and Cloud Provider.
* Durability and Resilience: Task execution to be tracked and degraded gracefully by appropriate
monitoring and retrying with appropriate state. Retry to happen with help of Hystrix and there would be a
fallback after all retry options are exhausted.
* Bounded wait Time: Monitor the performance of the task execution and notify when any task takes
longer time to execute. Based on notification further actions including closing the execution etc. will be
made. The monitoring can be done using tools like App Dynamics or Controller_Advices etc.

High Level Availability Details
-------------------------------
  * Cloud provided K8S services will be responsible routing to the next available instance based on Auto-Scaling configuration
  * Resilience to be handled by Hystrix and Retry.
  * Retry for a bounded time
  * Fallback after retry exhausted
  * Long-running tasks to be monitored and purged after the cut-oﬀ time. This to be retried at any later schedule.

High Level Security Details
-------------------------------
  * Follow OWASP-10 principles including the below.
  * Use JSYPT to encrypt the Passwords in property files.
  * Use encryption or decryption wherever applicable.
  * Use Key Vault or wallets like Oracle Wallets to securely store passwords.
  * Use OAuth to secure Service to Service communications.
  * Use 2 Factor Authentication whenever interacting with Customers including Theatres or Ticket buyers.

High level Storage Details
--------------------------
  * Storage for CICD build and deploy.
  * Storage for Database, Messaging Infrastructure
  * Storage for API Instances Repository


