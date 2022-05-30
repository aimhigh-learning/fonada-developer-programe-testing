package com.fonada.srana.test.service;

import com.fonada.srana.test.entities.Thread1;
import com.fonada.srana.test.entities.Thread2;
import com.fonada.srana.test.entities.Thread3;
import com.fonada.srana.test.repository.Thread1Repository;
import com.fonada.srana.test.repository.Thread2Repository;
import com.fonada.srana.test.repository.Thread3Repository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author sandeep.rana
 */
@Service
@Transactional @Slf4j
public class CsvService {

    @Autowired
    private Thread1Repository thread1Repository;

    @Autowired
    private Thread2Repository thread2Repository;

    @Autowired
    private Thread3Repository thread3Repository;


    /**
     * The main thread method from here to go
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void letsGo() throws ExecutionException, InterruptedException {

        StopWatch sw = new StopWatch();
        sw.start("Start reading thread 1 csv file and write into database table tread1 ....");

        Thread1Exec thread1Exec = new Thread1Exec();
        Thread t1 = new Thread(thread1Exec);

        t1.start();
        sw.stop();
        sw.start("Start reading thread 2 csv file and write into database table tread2 ....");

        Thread2Exe thread2Exe = new Thread2Exe();
        Thread t2 = new Thread(thread2Exe);
        t2.start();
        sw.stop();


        // wait to finish alll ...
        t1.join(); t2.join();
        sw.start("Start manipulating  data and process in thread 3");
        startThread3();

        sw.stop();
        log.info(sw.prettyPrint());



    }

    /**
     * Thread1 which insert the basic as per instruction
     */
    public class Thread1Exec implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            InputStream resource = new ClassPathResource("static/thread1.csv").getInputStream();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            List<Thread1> thread11List = new ArrayList<>();
            boolean isHeader = true;
            for (CSVRecord csvRecord : csvRecords) {
                if(isHeader) {isHeader = false; continue; }
                Thread1 thread1 = new Thread1().setTps(csvRecord.get(1))
                        .setVendorId(csvRecord.get(0));
                thread11List.add(thread1);
            }
            thread1Repository.saveAll(thread11List);

        }
    }

    /**
     * Thread2 which insert the vendorId , number and message
     */
    public class Thread2Exe implements  Runnable {

        @SneakyThrows
        @Override
        public void run() {
            InputStream resource = new ClassPathResource("static/thread2.csv").getInputStream();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            List<Thread2> thread11List = new ArrayList<>();
            boolean isHeader = true;
            for (CSVRecord csvRecord : csvRecords) {
                if(isHeader) {isHeader = false; continue; }
                Thread2 thread2 = new Thread2().setMessage(csvRecord.get(2))
                        .setVendorId(csvRecord.get(1))
                        .setNumber(Integer.parseInt(csvRecord.get(0)));
                thread11List.add(thread2);
            }
            thread2Repository.saveAll(thread11List);
        }
    }

    /**
     * Final thread which will will start the dynamic thread and insert ito thread3 table
     * @return
     */
    private boolean startThread3() {
        // read the data from thread1
        Iterable<Thread1> thread1Data =  thread1Repository.findAll();
        DynamicThread dynamicThread = new DynamicThread();
        for (Thread1 thread1: thread1Data) {
            Thread t = new Thread(dynamicThread, thread1.getVendorId());
            t.start();
        }
        return Boolean.TRUE;
    }

    /**
     * The dynamic thread which read the data into insert into thread3
     */
    public class DynamicThread implements Runnable {
        @Override
        public void run() {
            String vendorId = Thread.currentThread().getName();
            Optional<Thread2> thread2Optional =  thread2Repository.findById(vendorId);

            List<Thread3> thread3Lst = new ArrayList<>();

            // change thread2 to thead3
            if(!thread2Optional.isEmpty()) {
                Thread2 t = thread2Optional.get();
                Thread3 t3 = new Thread3().setMessage(t.getMessage())
                        .setNumber(t.getNumber())
                        .setVendorId(t.getVendorId());
                thread3Lst.add(t3);
            }

            thread3Repository.saveAll(thread3Lst);
        }
    }

}
