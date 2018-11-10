
# Event Receiver for Image Texture Ads
## Module Description
This module is a sink for all the events that are generated.
Currently it receives events from
* Adserver 
* Client devices when ads are played real time

## Technologies Used
* Flume: For event queue management
* HDFS: For big data storage
## Flume setup
[Tutorial for setting up flume with log4j](http://www.thecloudavenue.com/2013/11/using-log4jflume-to-log-application.html) 

Instructinons
1. Make sure java and hadoop environment vairables are set in ~/.bashrc. Reload by source ~/.bashrc
2. Download and extract flume.
3. Set flume-env.sh
4. Set flume.conf
5. If hdfs is required start start-dfs.sh and start-yarn.sh
6. Check from cloud portal if the ports required for agents needs to be allowed
7. Start agent using bin/flume-ng agent --conf ./conf/ -f conf/flume.conf -n visibilityMetricRecvAgent &
8. Start avro client if required using bin/flume-ng avro-client --conf conf -H localhost -p 41414 -F /etc/passwd
9. In log4j2.xml make sure that correct ports are marked and compression is off
10. See flume conf files in [src/doc](src/doc). 

## Hadoop
### Understanding concepts
Use yahoo hadoop tutorial to understand the concepts briefly
For HDFS:-https://developer.yahoo.com/hadoop/tutorial/module2.html
For MapReduce:-https://developer.yahoo.com/hadoop/tutorial/module4.html

### Create virtual computers
1. Download and install virtualbox from oracle
2. In bios menu enable virtualization technology. Without this you won't be able to creat 64 bit virtual machines
3. Create virtual machines of required config and os.
4. To install OS just mount iso file in the host machine and while booting the guest OS option to boot from iso will appear.
5. Install the OS like any other OS and do the required setup like java, hadoop download etc.

### Single node setup
See [http://pingax.com/install-hadoop2-6-0-on-ubuntu/](http://pingax.com/install-hadoop2-6-0-on-ubuntu/)
Just do following changes for ssh
```
sudo apt-get install openssh-server
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
```

### Multinode setup
http://pingax.com/install-apache-hadoop-ubuntu-cluster-setup/

### Get hands dirty with HDFS
https://developer.yahoo.com/hadoop/tutorial/module2.html
Just use hdfs dfs -ls / instead of hadoop dfs -ls / (latter method is deprecated)

### Learn Rest WEb API for dfs
https://bighadoop.wordpress.com/2013/06/02/hadoop-rest-api-webhdfs/

Tips for loading data fast into hdfs ------ https://blogs.oracle.com/datawarehousing/entry/2data_loading_into_hdfs_part1

Getting started with Pig ------- http://www.rohitmenon.com/index.php/apache-pig-tutorial-part-1/

Designing Hbase schema -------- http://www.cloudera.com/resources/hbasecon/video-hbasecon-2012-lessons-learned-from-opentsdb.html

Hbase with java ------------ http://bestlovejava.blogspot.in/2013/07/hbase-java-simple-example.html

## Pre-requisites
*  install maven 2+ ```sudo apt-get install maven```
* install java 6+
*  install git

## How to run
* ```mvn clean package```
* Start the tomcat server  
## License

This project is licensed under the MIT License

## Authors
* Sushil Kumar - [Github](https://github.com/sushilmiitb)
