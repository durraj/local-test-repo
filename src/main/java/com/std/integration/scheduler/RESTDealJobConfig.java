package com.std.integration.scheduler;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.std.common.tracelog.LoggingDealProcessor;
import com.std.common.tracelog.LoggingDealWriter;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;

@Configuration
public class RESTDealJobConfig {

    private static final String PROPERTY_REST_API_URL = "rest.api.to.database.job.api.url";

    @Bean
    ItemReader<DealsDTO> restDealReader(Environment environment, RestTemplate restTemplate) {
        return new RESTDealReader(environment.getRequiredProperty(PROPERTY_REST_API_URL), restTemplate,false);
    }

    @Bean
    ItemProcessor<DealsDTO, DealsDTO> restDealProcessor() {
        return new LoggingDealProcessor();
    }

    @Bean
    ItemWriter<DealsDTO> restDealWriter() {
        return new LoggingDealWriter();
    }

    @Bean
    Step restDealStep(ItemReader<DealsDTO> restDealReader,
                         ItemProcessor<DealsDTO, DealsDTO> restDealProcessor,
                         ItemWriter<DealsDTO> restDealWriter,
                         StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("restDealStep")
                .<DealsDTO,DealsDTO>chunk(1)
                .reader(restDealReader)
                .processor(restDealProcessor)
                .writer(restDealWriter)
                .build();
    }

   /* @Bean
    public Job importUserJob(STDJobCompletionNotificationListener listener,
    		@Qualifier("restDealStep") Step restDealStep) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }*/
    @Bean
    public Job restDealJob(JobBuilderFactory jobBuilderFactory,
    		STDJobCompletionNotificationListener listener,
            @Qualifier("restDealStep") Step restDealStep) {
			return jobBuilderFactory.get("restDealJob")
			     .incrementer(new RunIdIncrementer())
			     .listener(listener)
			     .flow(restDealStep)
			     .end()
			     .build();
			}
   /* public Job restDealJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("restDealStep") Step restDealStep) {
        return jobBuilderFactory.get("restDealJob")
                .incrementer(new RunIdIncrementer())
                .flow(restDealStep)
                .end()
                .build();
    }*/
}
