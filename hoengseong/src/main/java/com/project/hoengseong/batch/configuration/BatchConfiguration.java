package com.project.hoengseong.batch.configuration;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.project.hoengseong.batch.dao.BatchMapper;
import com.project.hoengseong.batch.model.LogDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "배치 BatchConfiguration", description = "배치 Job, Step Conf")
@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {
	
	private final BatchMapper batchMapper;

    @Bean
    public Job batchJob(JobRepository jobRepository, @Qualifier("batchStep1") Step batchStep1, @Qualifier("batchStep2") Step batchStep2) {
        return new JobBuilder("batchJob", jobRepository)
                .start(batchStep1)
                .next(batchStep2)
                .build();
    }

    @Bean
    public Step batchStep1(JobRepository jobRepository, PlatformTransactionManager tx) {
        return new StepBuilder("batchStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================================");
                    System.out.println(" batchStep1 executed ");
                    System.out.println("====================================");
                    String id = batchMapper.idRand();	//random ID값
                    LogDTO dto = new LogDTO();
                    dto.setId(id);
                    batchMapper.insertLog(dto);
                    return RepeatStatus.FINISHED;
                }, tx).build();
    }

    @Bean
    public Step batchStep2(JobRepository jobRepository, PlatformTransactionManager tx) {
        return new StepBuilder("batchStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================================");
                    System.out.println(" batchStep2 executed ");
                    System.out.println("====================================");
                    return RepeatStatus.FINISHED;
                }, tx).build();
    }


}