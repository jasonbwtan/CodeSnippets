drop table if exists Batch_JOB_EXECUTION_CONTEXT;
drop table if exists Batch_JOB_EXECUTION_PARAMS;
drop table if exists Batch_JOB_EXECUTION_SEQ;
drop table if exists Batch_JOB_SEQ;
drop table if exists Batch_STEP_EXECUTION_CONTEXT;
drop table if exists Batch_STEP_EXECUTION_SEQ;
drop table if exists Batch_STEP_EXECUTION;
drop table if exists Batch_JOB_EXECUTION;
drop table if exists Batch_JOB_INSTANCE;

DROP TABLE IF EXISTS POJO;

CREATE TABLE POJO  (
    id VARCHAR(20),
    description VARCHAR(20),
    addedField VARCHAR(50)
);