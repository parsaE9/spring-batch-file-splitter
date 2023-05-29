package com.shaparak.batch;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
@EnableBatchProcessing
public class BatchApplication implements CommandLineRunner {

	private Job job;

	private JobLauncher jobLauncher;



	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		long begin = System.currentTimeMillis();
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.addDate("jobDate", new Date())
				.addLong("jobTime", System.currentTimeMillis()).toJobParameters();

		JobExecution execution = jobLauncher.run(job, jobParameters);

		long end = System.currentTimeMillis();
		long time = end-begin;


		System.out.println("STATUS :: " + execution.getStatus());
		System.out.println("elapsed time in milli seconds :: " + time);
	}


}