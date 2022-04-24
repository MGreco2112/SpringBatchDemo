package com.batch.example.demo.configs;

import com.batch.example.demo.models.BitcoinRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Bean
    public FlatFileItemReader<BitcoinRecord> recordItemReader() {
        FlatFileItemReader<BitcoinRecord> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("/data/bitstamp_cleaned.csv"));

        DefaultLineMapper<BitcoinRecord> recordLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] {"unix_timestamp", "date_time", "open", "high", "low", "close", "volume_btc", "volume_currency", "weighted_price"});

        recordLineMapper.setLineTokenizer(tokenizer);

        recordLineMapper.setFieldSetMapper(new BtcFieldSetMapper());

        recordLineMapper.afterPropertiesSet();

        reader.setLineMapper(recordLineMapper);

        return reader;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    @Bean
    public JdbcBatchItemWriter<BitcoinRecord> recordItemWriter() {
        JdbcBatchItemWriter<BitcoinRecord> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);

        itemWriter.setSql("INSERT INTO bitcoin_record VALUES(:unix_timestamp, :date_time, :open, :high, :low, :close, :volume_btc, :volume_currency, :weighted_price)");

        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {

        return stepBuilderFactory.get("step1")
                .<BitcoinRecord, BitcoinRecord> chunk(10)
                .reader(recordItemReader())
                .writer(recordItemWriter())
                .build();
    }


}
