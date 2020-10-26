# 10X Algorithm 

This Git repo consists of following tasks;

* FizzBuzz
* Minimum Start value


# Executing the Jar

#####The code could be build with the following maven command

mvn clean install

Once the code is build, the jar would be created under the target folder.
The jar could be executed with the following command

java -jar target/algo-1.0.0.jar <task_id> <num...1> .... <num...n>

This project reads input via command terminal interface of the spring boot. 
After processing the innput and pritning/returning back the response, this server is terminated.


#####Executing the FizzBuzz

FizzBuzz could be executed with the following command

java -jar target/algo-1.0.0.jar 1 <input_number>

Ex:- java -jar target/algo-1.0.0.jar 1 15

The outcome would be printed in the console and log file. 



#####Executing the Minimum Start Value


Minimum Start Value could be executed with the following command

java -jar target/algo-1.0.0.jar 2 <input_number_1> <input_number_2> .... <input_number_n>

Ex:- java -jar target/algo-1.0.0.jar 2 -5 4 -2 3 1

The outcome would be printed in the console and log file. This method returns the value back to the Command Line Terminal.

