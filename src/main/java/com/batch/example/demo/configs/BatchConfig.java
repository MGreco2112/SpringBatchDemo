package com.batch.example.demo.configs;

import com.batch.example.demo.models.BitcoinRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob(Step step) {
        jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step).end().build();
    }

    @Bean
    public Step orderStep1(JdbcBatchItemWriter<BitcoinRecord> writer) {

        return stepBuilderFactory.get("orderStep1").<BitcoinRecord, BitcoinRecord> chunk(10)
                .reader(flatFileItemReader())
                .processor()
    }


}
