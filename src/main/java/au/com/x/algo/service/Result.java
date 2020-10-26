package au.com.x.algo.service;

import au.com.x.algo.exception.InvalidInputException;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *  Service layer for processing the algorithmic coding
 */


@Service
public class Result {

    private static ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(Result.class.getPackage().getName());

    /**
     *
     * @param inputList   Input list's 0th index always pointed to the type of operation to be performed on the
     *                    subsequent elements. If the Oth Index is
     *                    1 --> fizzBuzz operation 2 --> minX operation
     * @return            always -1 for fizzBuzz as the method doesn't return anything
     *                    a valid value returned from minX function
     * @throws InvalidInputException --> Erroneous/missing input
     */
    public int processRequest(List<Integer> inputList) throws InvalidInputException {


        if(inputList.size() >= 2){
            int selection = inputList.get(0);
            inputList.remove(0);

            if(selection == 1) {
                this.fizzBuzz(inputList.get(0));
            } else if (selection == 2) {
                return this.minX(inputList.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray());
            }
        } else {
            throw new InvalidInputException("Input is not valid");
        }

        return -1;
    }

    /**
     * A simple logic to print fizzBuzz
     *
     * @param n Only the numbers from 1 - 19 is allowed to print
     * @throws InvalidInputException
     */
    public void fizzBuzz(int n) throws InvalidInputException {

        if(n > 0 && n < 20 ){
            StringBuilder stringBuilder = new StringBuilder();
            for(int index = 1; index <= n; ++index ){

                if(index%3 == 0 && index%5 == 0 ){
                    stringBuilder.append("FizzBuzz");
                } else if (index%3 == 0) {
                    stringBuilder.append("Fizz");
                } else if (index%5 == 0) {
                    stringBuilder.append("Buzz");
                } else {
                    stringBuilder.append(index);
                }

                if(index < n){
                    stringBuilder.append("\n");
                }
            }
            logger.info(stringBuilder.toString());
        } else {
            throw new InvalidInputException("Number range is not allowed");
        }
    }

    /**
     * The logic is to find the most lowest minimal sum obtained at a step, and deduce it from 1
     *  1 - (minimal value).
     * @param numsArray
     * @return
     * @throws InvalidInputException
     */
    public int minX(int[] numsArray) throws InvalidInputException {

        if(isInValidInputParamPresent(numsArray)){
            throw new InvalidInputException("Input array consists of invalid values");
        }

        int total = 0;
        int minValue = 0;
        for (int num: numsArray) {
            total += num;
            minValue = Math.min(total, minValue);
        }
        return 1 - minValue;

    }

    private boolean isInValidInputParamPresent(int[] numsArray){
        return numsArray.length > 10 || Arrays.stream(numsArray).parallel().anyMatch(x -> x < -100 || x > 100);
    }
}
