DROP SEQUENCE FORMAT_ROUTE_MAP_INFO_SEQ CASCADE;
CREATE SEQUENCE FORMAT_ROUTE_MAP_INFO_SEQ INCREMENT 1
     MINVALUE 1001 MAXVALUE 9999 CYCLE;

DROP TABLE FORMAT_ROUTE_MAP_INFO;
CREATE TABLE FORMAT_ROUTE_MAP_INFO (
  SEQ                      NUMERIC(4,0) NOT NULL DEFAULT NEXTVAL('FORMAT_ROUTE_MAP_INFO_SEQ'),
  MODULE_NAME              VARCHAR(10),
     MODULE_TYPE           VARCHAR(10),
     MODULE_FORMAT         VARCHAR(10),
     ROUTE_FILE_NAME       VARCHAR(100),
     ROUTE_FILE_PATH       VARCHAR(255),
     USE_YN                CHAR(1)
)
WITH (
     OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE FORMAT_ROUTE_MAP_INFO ADD CONSTRAINT "FORMAT_ROUTE_MAP_INFO_PK" UNIQUE (SEQ);

INSERT INTO FORMAT_ROUTE_MAP_INFO
(MODULE_NAME, MODULE_TYPE, MODULE_FORMAT, ROUTE_FILE_NAME, ROUTE_FILE_PATH, USE_YN)
VALUES
('TEST', 'TEST', 'TEST', 'fileTransferRoute.xml', '/home/routes/batch/', 'Y');

insert into format_route_map_info
(module_name, module_type, module_format, route_file_name, route_file_path, use_yn)
values
('TEST','TEST','TEST','mu11Route.xml','/targetline/targetline-script/script/route/batch/','Y');

DROP SEQUENCE FORMAT_RULE_MAP_INFO_SEQ CASCADE;
CREATE SEQUENCE FORMAT_RULE_MAP_INFO_SEQ INCREMENT 1
     MINVALUE 1001 MAXVALUE 9999 CYCLE;

DROP TABLE format_rule_map_info;
create table format_rule_map_info (
  SEQ                         NUMERIC(4,0) NOT NULL DEFAULT NEXTVAL('FORMAT_RULE_MAP_INFO_SEQ'),
     FORMAT                   varchar(10),
     FORMAT_TYPE              varchar(10),
     FIRST_CRET_DT            date,
     FIRST_CRET_TRTR_ID       varchar(11), 
     FIRST_CRET_PGM_ID        varchar(300),
     LAST_CHG_DT              date,
     LAST_CHG_TRTR_ID         varchar(11),
     LAST_CHG_PGM_ID          varchar(300),
     DROOLS_PATH              varchar(255),
     EFFECTIVE_DATE           date,
     EXPIRATE_DATE            date
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE FORMAT_ROUTE_MAP_INFO ADD CONSTRAINT "FORMAT_RULE_MAP_INFO_PK" UNIQUE (SEQ);

DROP SEQUENCE FILE_INPUT_AUDIT_SEQ;
CREATE  SEQUENCE  FILE_INPUT_AUDIT_SEQ increment 1
    minvalue 100000000001  MAXVALUE  999999999999 cycle
;

--Data Insert
insert into format_rule_map_info (format, format_type, FIRST_CRET_DT, FIRST_CRET_TRTR_ID, FIRST_CRET_PGM_ID, drools_path, effective_date, expirate_date)
values('TEST', '1', now(), 'MANUAL', 'MANUAL', '/home/rules/batch', TO_DATE('20190901','YYYYMMDD'), TO_DATE('99991231','YYYYMMDD'));
                                                                                                 
--insert into format_rule_map_info (format, format_type, FIRST_CRET_DT, FIRST_CRET_TRTR_ID, FIRST_CRET_PGM_ID, drools_path, effective_date, expirate_date)
values('TEST', '2', now(), 'MANUAL', 'MANUAL', '/home/rules/', TO_DATE('20190901','YYYYMMDD'), TO_DATE('99991231','YYYYMMDD'));
                                                                                                
--insert into format_rule_map_info (format, format_type, FIRST_CRET_DT, FIRST_CRET_TRTR_ID, FIRST_CRET_PGM_ID, drools_path, effective_date, expirate_date)
values('TEST', '3', now(), 'MANUAL', 'MANUAL', '/home/rules/sftp', TO_DATE('20190901','YYYYMMDD'), TO_DATE('99991231','YYYYMMDD'));
                                                                                                  
--insert into format_rule_map_info (format, format_type, FIRST_CRET_DT, FIRST_CRET_TRTR_ID, FIRST_CRET_PGM_ID, drools_path, effective_date, expirate_date)
values('TEST', '4', now(), 'MANUAL', 'MANUAL', '/home/rules/test', TO_DATE('20190901','YYYYMMDD'), TO_DATE('99991231','YYYYMMDD'));


DROP TABLE FILE_INPUT_AUDIT;
create table FILE_INPUT_AUDIT (
     IDENTIFIER           NUMERIC(15,0) NOT NULL DEFAULT NEXTVAL('FILE_INPUT_AUDIT_SEQ'),
     FILE_NAME            varchar(300),
     FILE_PATH            varchar(300),
     NE_ID                char(5),
     FORMAT_ID            char(7),
     FILE_ID              integer,
     FILE_DATE            char(8),
     FILE_TIME            char(6),
     FIRST_CRET_DT        date,
     FIRST_CRET_TRTR_ID   varchar(11), 
     FIRST_CRET_PGM_ID    varchar(300),
     LAST_CHG_DT          date,
     LAST_CHG_TRTR_ID     varchar(11),
     LAST_CHG_PGM_ID      varchar(300),
     FILE_SIZE            integer,
     PROCESS_ID           integer
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE FILE_INPUT_AUDIT ADD CONSTRAINT "FILE_INPUT_AUDIT_PK" UNIQUE (IDENTIFIER);

DROP SEQUENCE FILE_PROCESSING_AUDIT_SEQ CASCADE;
CREATE  SEQUENCE  FILE_PROCESSING_AUDIT_SEQ increment 1
    minvalue 100000000001  MAXVALUE  999999999999 cycle
;

DROP TABLE FILE_PROCESSING_AUDIT;
create table FILE_PROCESSING_AUDIT (
     IDENTIFIER             NUMERIC(15,0) NOT NULL DEFAULT NEXTVAL('FILE_PROCESSING_AUDIT_SEQ'),
     INPUT_IDENTIFIER       bigint REFERENCES FILE_INPUT_AUDIT (IDENTIFIER),
     PARENT_IDENTIFIER      bigint,
     FIRST_CRET_DT          date,
     FIRST_CRET_TRTR_ID     varchar(11), 
     FIRST_CRET_PGM_ID      varchar(300),
     LAST_CHG_DT            date,
     LAST_CHG_TRTR_ID       varchar(11),
     LAST_CHG_PGM_ID        varchar(300),
     PROCESS_ID             char(10),
     INPUT_RECORD_CNT       integer,
     DROP_RECORD_CNT        integer,
     ERROR_RECORD_CNT       integer,
     OUTPUT_RECORD_CNT      integer,
     NE_ID                  char(5),
     FORMAT_ID              char(7),
     FILE_ID                integer,
     FILE_DATE              char(8),
     FILE_TIME              char(6),
     OUTPUT_FILE_NAME       varchar(300),
     OUTPUT_FILE_PATH       varchar(255),
     STATUS                 char(2),
     TARGET_DSS_ID          char(5),
     TRANSFER_STATUS        char(2),
     TRANSFER_DATE          date     
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE FILE_PROCESSING_AUDIT ADD CONSTRAINT "FILE_PROCESSING_AUDIT_PK" UNIQUE (IDENTIFIER);
                                                                                                  
--SELECT TABLE
select * from format_route_map_info;

select * from format_rule_map_info;          
                                                                                                                                    
select * from FILE_INPUT_AUDIT;
                                                                                                                                    
select * from FILE_PROCESSING_AUDIT;


DROP SEQUENCE TRANS_ONLINE_TRM_SEQ CASCADE;
CREATE  SEQUENCE  TRANS_ONLINE_TRM_SEQ increment 1
    minvalue 100000000001  MAXVALUE  999999999999 cycle
;

DROP TABLE TRANS_ONLINE_TRM_HST;
CREATE UNLOGGED TABLE TRANS_ONLINE_TRM_HST
(
      CARDAPV_TRM_SEQ       NUMERIC(15,0) NOT NULL DEFAULT NEXTVAL('TRANS_ONLINE_TRM_SEQ'),
      DEAL_INNT_NO          NUMERIC(15,0) NOT NULL,
      FIRST_CRET_DT         TIMESTAMP NOT NULL,
      FIRST_CRET_TRTR_ID    VARCHAR(11),
      LAST_CHG_DT           TIMESTAMP,
      LAST_CHG_TRTR_ID      VARCHAR(11),
      FIRST_CRET_PGM_ID     VARCHAR(300),
      LAST_CHG_PGM_ID       VARCHAR(300),
      APV_TYPE_CD           VARCHAR(5),
      TRM_DT                TIMESTAMP NOT NULL,
      CARD_NO               VARCHAR(32),
      RESP_CD               VARCHAR(10),
      APV_NO                VARCHAR(10),
      TRM_COMP_CD           VARCHAR(10),
      SOAP_REQUEST          VARCHAR(1000),
      SOCKET_REQUEST        VARCHAR(1000),
      SOCKET_RESPONSE       VARCHAR(1000),
      SOAP_RESPONSE         VARCHAR(1000)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TRANS_ONLINE_TRM_HST ADD CONSTRAINT "TRANS_ONLINE_TRM_HST_PK" UNIQUE (CARDAPV_TRM_SEQ);

DROP INDEX TRANS_ONLINE_TRM_HST_IX1;
CREATE INDEX TRANS_ONLINE_TRM_HST_IX1 On TRANS_ONLINE_TRM_HST(DEAL_INNT_NO, TRM_COMP_CD);



-- SELECT TABLE
select * from TRANS_ONLINE_TRM_HST;


DROP TABLE OL_CRDBIN_NO_ADM_HST;
CREATE UNLOGGED TABLE OL_CRDBIN_NO_ADM_HST
(
  CRDBIN_NO                VARCHAR(7)       NOT NULL,
  EFCT_ST_DT               DATE                NOT NULL,
  EFCT_FNS_DT              DATE                NOT NULL,  
  SYS_TRTR_ID              VARCHAR(15)      NOT NULL,
  SYS_TRT_ORG_ID           VARCHAR(15),
  SYS_SVC_ID               VARCHAR(50),
  SYS_COMP_ID              VARCHAR(50),
  SYS_RECD_CRET_DT         DATE                   NOT NULL,
  SYS_RECD_CHG_DT          DATE,
  CARD_CMPN_CD             VARCHAR(3)       NOT NULL,
  CRDT_VAN_DIV_CD          VARCHAR(3),
  CRDBIN_NO_NM             VARCHAR(100)     NOT NULL,
  AWDR_EFCT_YN             VARCHAR(1)       NOT NULL,
  USE_YN                   VARCHAR(1)       NOT NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE OL_CRDBIN_NO_ADM_HST ADD CONSTRAINT "OL_CRDBIN_NO_ADM_HST_PK" UNIQUE 
(CRDBIN_NO, EFCT_ST_DT, EFCT_FNS_DT);

--File Request History
DROP SEQUENCE FILE_REQUEST_HST_SEQ CASCADE;
CREATE SEQUENCE FILE_REQUEST_HST_SEQ INCREMENT 1
     MINVALUE 10001 MAXVALUE 99999 CYCLE;

create table FILE_TRANSFER_STATUS_HST (
     SEQ                   NUMERIC(5,0) NOT NULL DEFAULT NEXTVAL('FILE_REQUEST_HST_SEQ'),
     GUBUN                 char(2)      NOT NULL,
     FILE_PATH             varchar(50),
     FILE_NAME             varchar(50),
     FIRST_CRET_DT         date,
     FIRST_CRET_TRTR_ID    varchar(11), 
     FIRST_CRET_PGM_ID     varchar(300),
     LAST_CHG_DT           date,
     LAST_CHG_TRTR_ID      varchar(11),
     LAST_CHG_PGM_ID       varchar(300),
     FILE_TYPE             char(4),
     REQUEST_DATE          date,
     STATUS                char(2),
     RESPONSE              char(3)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE FILE_TRANSFER_STATUS_HST ADD CONSTRAINT "FILE_TRANSFER_STATUS_HST_PK" UNIQUE (SEQ);

