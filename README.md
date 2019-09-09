# Steps to run
### For Server make sure to set rmi.server.hostname correctly
```shell script
java -Djava.rmi.server.hostname=10.1.37.202 -jar out/artifacts/distrib_a3_server_jar2/distrib-a3.jar
```

### For client
```shell script
java -jar out/artifacts/distrib_a3_client_jar2/distrib-a3.jar
```


Source: Read A.1 at
[https://docs.oracle.com/javase/7/docs/technotes/guides/rmi/faq.html#domain](https://docs.oracle.com/javase/7/docs/technotes/guides/rmi/faq.html#domain)
