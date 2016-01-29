package com.example.myproject;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.example.myproject.entities.CustomPojo;
import com.example.myproject.entities.InputRecord;
import com.example.myproject.processor.CustomItemProcessor;

/**
 * This class contains all the configuration of the Spring Batch application. It
 * contains readers, writers, processors, jobs, steps and all the needed beans.
 * 
 * 
 *
 */
@Configuration
@EnableBatchProcessing
@EntityScan(basePackages="com.example.myproject.entities")
public class SpringBatchTutorialConfiguration {
	/**
	 * Returns a reader
	 * 
	 * @return
	 */
	@Bean
	public ItemReader<InputRecord> reader() {
		// flat file item reader (using an csv extractor)
		FlatFileItemReader<InputRecord> reader = new FlatFileItemReader<InputRecord>();
		reader.setResource(new ClassPathResource("input.csv"));
		reader.setLineMapper(new DefaultLineMapper<InputRecord>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[]{"id", "description"});
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<InputRecord>() {
					{
						setTargetType(InputRecord.class);
					}
				});
			}
		});
		return reader;
	}

	/**
	 * Returns a processor
	 * 
	 * @return
	 */
	@Bean
	public ItemProcessor<InputRecord, CustomPojo> processor() {
		return new CustomItemProcessor();
	}

	/**
	 * Returns a writer
	 * 
	 * @param dataSource
	 * @return
	 * @throws SQLException
	 */
	// @Bean
	// public ItemWriter<CustomPojo> writer(DataSource dataSource) {
	// JdbcBatchItemWriter<CustomPojo> writer = new
	// JdbcBatchItemWriter<CustomPojo>();
	//
	// writer.setSql("INSERT INTO pojo (id, description) VALUES (:id,
	// :description)");
	// writer.setDataSource(dataSource);
	// writer.setItemSqlParameterSourceProvider(
	// new BeanPropertyItemSqlParameterSourceProvider<CustomPojo>());
	// return writer;
	//
	// }
	@Bean
	public ItemWriter<CustomPojo> writer(LocalContainerEntityManagerFactoryBean lcemfb) throws SQLException {
		JpaItemWriter writer = new JpaItemWriter<CustomPojo>();
		writer.setEntityManagerFactory(lcemfb.getObject());
	
		return writer;
	}

//	@Bean
//	public DataSource dataSource() throws SQLException {
//		// mysql data source
//		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3307/spring_batch");
//		dataSource.setUsername("root");
//		dataSource.setPassword("password");
//		return dataSource;
//	}

	/**
	 * Returns a job bean
	 */
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}
	/**
	 * Returns a step. .allowStartIfComplete(true) forces re-execution of a step even if it completed successfully.
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<InputRecord> reader,
			ItemWriter<CustomPojo> writer, ItemProcessor<InputRecord, CustomPojo> processor) {
		/* it handles bunches of 10 units */
		return stepBuilderFactory.get("step1").<InputRecord, CustomPojo> chunk(10).reader(reader)
				.processor(processor).writer(writer).allowStartIfComplete(true).build();
	}

	/**
	 * Utilities
	 * 
	 * @throws SQLException
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws SQLException {

		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter());
		lef.setJpaProperties(new Properties());
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(false);

		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jpaVendorAdapter;
	}
}