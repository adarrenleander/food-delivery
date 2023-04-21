# food-delivery
An online food delivery system built for our Service-Oriented Architecture course at the University of Twente

### Get Started
1. Run an instance of ActiveMQ message queue on port 61616.
2. Run an instance of MySQL database on port 3306.
3. Run each service separately.

The kubernetes folder contains all the files to deploy the application using Kubernetes. A separate file is created for each service. Two additional files are created, one for a MySQL database and one for an ActiveMQ message queue.