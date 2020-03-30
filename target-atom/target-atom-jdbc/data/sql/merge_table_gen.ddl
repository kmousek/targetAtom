DROP SEQUENCE FORMAT_ROUTE_MAP_INFO_SEQ CASCADE;
CREATE SEQUENCE FORMAT_ROUTE_MAP_INFO_SEQ INCREMENT 1
     MINVALUE 1001 MAXVALUE 9999 CYCLE;

DROP TABLE FORMAT_ROUTE_MAP_INFO;
CREATE TABLE FORMAT_ROUTE_MAP_INFO (
     SEQ                   NUMERIC(4,0) NOT NULL DEFAULT NEXTVAL('FORMAT_ROUTE_MAP_INFO_SEQ'),
     MODULE_NAME           VARCHAR(10),
     MODULE_TYPE           VARCHAR(10),
     FORMAT                VARCHAR(10),
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
(MODULE_NAME, MODULE_TYPE, FORMAT, ROUTE_FILE_NAME, ROUTE_FILE_PATH, USE_YN)
VALUES
('TEST', 'TEST', 'TEST', 'fileTransferRoute.xml', '/home/routes/batch/', 'Y');

insert into format_route_map_info
(module_name, module_type, format, route_file_name, route_file_path, use_yn)
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



/**************************************************************************************************/
/**************************************************************************************************/
/************************************** MZN package table *****************************************/
/**************************************************************************************************/
/**************************************************************************************************/

/********************** tb_wflow_info  **********************/

-- 1)테이블명 : TB_WFLOW_INFO
CREATE UNLOGGED TABLE TB_WFLOW_INFO
(
     WFLOW_INST_ID          VARCHAR(11)          NOT NULL,
     EXP_DT                 TIMESTAMP          NOT NULL,
     FIRST_CRET_DT          TIMESTAMP          NOT NULL,
     FIRST_CRET_TRTR_ID     VARCHAR(11)         NOT NULL,
     LAST_CHG_DT            TIMESTAMP          NOT NULL,
     LAST_CHG_TRTR_ID       VARCHAR(11)          NOT NULL,
     FIRST_CRET_PGM_ID      VARCHAR(300)     NOT NULL,
     LAST_CHG_PGM_ID        VARCHAR(300)     NOT NULL,
     EFCT_ST_DT             TIMESTAMP          NOT NULL,
     WFLOW_RMARK            VARCHAR(750),
     WNWLS_TYPE_CD          VARCHAR(3)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_WFLOW_INFO ADD CONSTRAINT "TB_WFLOW_INFO_PK" UNIQUE 
(WFLOW_INST_ID, EXP_DT);

ALTER TABLE TB_WFLOW_INFO ADD PRIMARY KEY 
(WFLOW_INST_ID, EXP_DT);








-- 2)테이블명 : TB_CDRCOLL_BASE_INFO
CREATE UNLOGGED TABLE TB_CDRCOLL_BASE_INFO
(
     WFLOW_INST_ID               VARCHAR(11)    NOT NULL,
     CDR_FILE_COLEC_DIR_NM       VARCHAR(150) NOT NULL,
     ORIGIN_FMT_ID               VARCHAR(11)  NOT NULL,
     EXP_DT                      TIMESTAMP    NOT NULL,
     FIRST_CRET_DT               TIMESTAMP    NOT NULL  DEFAULT now(),
     FIRST_CRET_TRTR_ID          VARCHAR(11)  NOT NULL,
     LAST_CHG_DT                 TIMESTAMP    NOT NULL  DEFAULT now(),
     LAST_CHG_TRTR_ID            VARCHAR(11)  NOT NULL,
     FIRST_CRET_PGM_ID           VARCHAR(300) NOT NULL,
     LAST_CHG_PGM_ID             VARCHAR(300) NOT NULL,
     EFCT_ST_DT                  TIMESTAMP    NOT NULL,
     CDRCOLL_SRVR_ID             VARCHAR(11),
     NE_ID                       VARCHAR(11),
     NE_TYPE_ID                  VARCHAR(11),
     FNS_FILE_CRET_YN            VARCHAR(1),
     FNS_FILE_DIV_CD             VARCHAR(3),
     MISS_FILE_SEQ_CONF_YN       VARCHAR(1),
     RSND_APY_YN                 VARCHAR(1),
     FTP_NE_DIR_NM               VARCHAR(150),
     CAL_SPATN_YN                VARCHAR(1),
     CDR_ZERO_COLEC_YN           VARCHAR(1),
     CDR_HEAD_COLEC_YN           VARCHAR(1),
     CDRCOLL_VRF_YN              VARCHAR(1),
     CDRCOLL_VRF_ID              VARCHAR(11),
     COLEC_FILE_SIZE_VRF_YN      VARCHAR(1),
     COLEC_FILE_HEAD_SIZE        NUMERIC(5,0),
     COLEC_FILE_RECD_SIZE        NUMERIC(5,0),
     COLEC_FILE_TRLR_SIZE        NUMERIC(5,0),
     DUPL_FILE_COLEC_YN          VARCHAR(1),
     FILE_CRET_CYCL_TIME         NUMERIC(5,0),
     NE_TYPE_ROAM_YN             VARCHAR(1),
     LIST_FILE_CRET_YN           VARCHAR(1),
     COLEC_CDR_INCL_SBST         VARCHAR(500),
     CDR_FILE_COLEC_CYCL_TIME    NUMERIC(5,0),
     CDR_COLEC_FILE_PERD_DIV_CD  VARCHAR(3),
     CDR_COLEC_FILE_CNT          NUMERIC(10,0),
     PIA_YN                      VARCHAR(1)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_CDRCOLL_BASE_INFO ADD CONSTRAINT "TB_CDRCOLL_BASE_INFO_PK" UNIQUE 
(wflow_inst_id, cdr_file_colec_dir_nm, origin_fmt_id, exp_dt);

ALTER TABLE TB_CDRCOLL_BASE_INFO ADD PRIMARY KEY 
(wflow_inst_id, cdr_file_colec_dir_nm, origin_fmt_id, exp_dt);






drop table TB_WLESS_SPCL_NO_BAS;
CREATE TABLE TB_WLESS_SPCL_NO_BAS
(
idfy_tel_no              varchar(4) NOT NULL,
pfx_tel_no               varchar(4) NOT NULL,
hho_st_no                varchar(12) NOT NULL,
wless_mkt_div_cd         varchar(3) NOT NULL,
exp_dt                   timestamp NOT NULL,
first_cret_dt            timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id       varchar(11) NOT NULL,
last_chg_dt              timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id         varchar(11) NOT NULL,
first_cret_pgm_id        varchar(300) NOT NULL,
last_chg_pgm_id          varchar(300) NOT NULL,
efct_st_dt               timestamp NOT NULL,
hho_fns_no               varchar(12),
airtm_yn                 varchar(1) NOT NULL,
airtm_chage_yn           varchar(1),
airtm_chage_amt          numeric(18,3),
drop_yn                  varchar(1),
specl_no_type_id         varchar(11) NOT NULL,
spcl_no_sbst             varchar(1500),
featr_id                 varchar(11),
spcl_no_id               numeric(22),
svc_prvr_yn              varchar(1),
svc_prvr_id              varchar(11),
svc_prvr_featr_id        varchar(11),
setl_file_nm             varchar(255),
setl_prvr_div_cd         varchar(7),
setl_featr_id            varchar(11),
airtm_featr_yn           varchar(1),
airtm_featr_id           varchar(11),
setl_etc_featr_id        varchar(11),
pps_drop_yn              varchar(1),
innet_yn                 varchar(1),
spcl_fns_pfx_tel_no      varchar(4),
spcl_fns_indiv_tel_no    varchar(4),
np_id                    varchar(11)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_wless_spcl_no_bas ADD CONSTRAINT "tb_wless_spcl_no_bas_pk" UNIQUE (idfy_tel_no,pfx_tel_no,hho_st_no,wless_mkt_div_cd,exp_dt,efct_st_dt);




DROP TABLE TB_PFIX_RGN_BAS;

CREATE TABLE TB_PFIX_RGN_BAS
(
pfix_idfy_no varchar(20) NOT NULL,
exp_dt timestamp NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
efct_st_dt timestamp NOT NULL,
setl_file_nm varchar(255),
setl_prvr_div_cd varchar(6),
setl_featr_id varchar(11),
setl_featr_sbst varchar(1500),
idfy_tel_no varchar(4),
innet_yn varchar(1),
setl_etc_featr_id varchar(11),
wrlin_wless_div_val varchar(5),
np_id varchar(11)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_pfix_rgn_bas ADD CONSTRAINT "tb_pfix_rgn_bas_pk" UNIQUE (pfix_idfy_no,exp_dt);




-- 23) : TB_PROF_BAS
CREATE UNLOGGED TABLE TB_PROF_BAS
(
    prof_item_id                   character varying(20) COLLATE pg_catalog."default" NOT NULL,
    prof_item_div_id               character varying(11) COLLATE pg_catalog."default" NOT NULL,
    exp_dt                         timestamp without time zone NOT NULL,
    first_cret_dt                  timestamp without time zone NOT NULL,
    first_cret_trtr_id             character varying(11) COLLATE pg_catalog."default" NOT NULL,
    last_chg_dt                    timestamp without time zone NOT NULL,
    last_chg_trtr_id               character varying(11) COLLATE pg_catalog."default" NOT NULL,
    first_cret_pgm_id              character varying(300) COLLATE pg_catalog."default" NOT NULL,
    last_chg_pgm_id                character varying(300) COLLATE pg_catalog."default" NOT NULL,
    efct_st_dt                     timestamp without time zone NOT NULL,
    prof_item_val                  character varying(50) COLLATE pg_catalog."default",
    prof_item_sbst                 character varying(1500) COLLATE pg_catalog."default",
    CONSTRAINT tb_prof_bas_pkey PRIMARY KEY (prof_item_id, prof_item_div_id, exp_dt),
    CONSTRAINT "TB_PROF_BAS_PK" UNIQUE (prof_item_id, prof_item_div_id, exp_dt)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_PROF_BAS ADD CONSTRAINT "TB_PROF_BAS_PK" UNIQUE 
(prof_item_id, prof_item_div_id, exp_dt);

--ALTER TABLE TB_PROF_BAS ADD PRIMARY KEY 
--(prof_item_id, prof_item_div_id, exp_dt);





-- 3)테이블명 : TB_CDRSEND_BASE_INFO
CREATE UNLOGGED TABLE TB_CDRSEND_BASE_INFO
(
     WFLOW_INST_ID     VARCHAR(11) NOT NULL,
     CDR_FILE_COLEC_DIR_NM     VARCHAR(150) NOT NULL,
     ORIGIN_FMT_ID     VARCHAR(11) NOT NULL,
     CDR_CHANGE_FMT_ID     VARCHAR(11) NOT NULL,
     TRM_DIR_NM     VARCHAR(150) NOT NULL,
     EXP_DT     TIMESTAMP NOT NULL,
     FIRST_CRET_DT     TIMESTAMP NOT NULL,
     FIRST_CRET_TRTR_ID     VARCHAR(11) NOT NULL,
     LAST_CHG_DT     TIMESTAMP NOT NULL,
     LAST_CHG_TRTR_ID     VARCHAR(11) NOT NULL,
     FIRST_CRET_PGM_ID     VARCHAR(300) NOT NULL,
     LAST_CHG_PGM_ID     VARCHAR(300) NOT NULL,
     EFCT_ST_DT     TIMESTAMP NOT NULL,
     NE_ID     VARCHAR(11),
     NE_TYPE_ID     VARCHAR(11),
     DSS_ID     VARCHAR(11),
     DSS_DTL_ID     VARCHAR(50),
     CDRSEND_FILE_CRET_YN     VARCHAR(1),
     CAMA_HEAD_CRET_YN     VARCHAR(1),
     DSS_DIV_CD     VARCHAR(3),
     FNS_FILE_CRET_YN     VARCHAR(1),
     CDR_FILE_TYPE_CD     VARCHAR(3),
     CDRSEND_TBL_CRET_YN     VARCHAR(1),
     FNS_FILE_DIV_CD     VARCHAR(3),
     LIST_FILE_CRET_YN     VARCHAR(1),
     TRM_QUE_NM    VARCHAR(100)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_CDRSEND_BASE_INFO ADD CONSTRAINT "TB_CDRSEND_BASE_INFO_PK" UNIQUE 
(WFLOW_INST_ID, CDR_FILE_COLEC_DIR_NM, ORIGIN_FMT_ID, CDR_CHANGE_FMT_ID, TRM_DIR_NM, EXP_DT);

ALTER TABLE TB_CDRSEND_BASE_INFO ADD PRIMARY KEY 
(WFLOW_INST_ID, CDR_FILE_COLEC_DIR_NM, ORIGIN_FMT_ID, CDR_CHANGE_FMT_ID, TRM_DIR_NM, EXP_DT);





-- 18)테이블명 : TB_ERR_MAPPG_BAS
CREATE UNLOGGED TABLE TB_ERR_MAPPG_BAS
(
     NE_TYPE_ID     VARCHAR(11) NOT NULL,
     MAPPG_FIELD_ID     VARCHAR(50) NOT NULL,
     FIELD_ODRG     INT NOT NULL,
     EXP_DT     TIMESTAMP NOT NULL,
     FIRST_CRET_DT     TIMESTAMP NOT NULL,
     FIRST_CRET_TRTR_ID     VARCHAR(11) NOT NULL,
     LAST_CHG_DT     TIMESTAMP NOT NULL,
     LAST_CHG_TRTR_ID     VARCHAR(11) NOT NULL,
     FIRST_CRET_PGM_ID     VARCHAR(300) NOT NULL,
     LAST_CHG_PGM_ID     VARCHAR(300) NOT NULL,
     EFCT_ST_DT     TIMESTAMP NOT NULL,
     CDR_ERR_ID     VARCHAR(7) NOT NULL,
     MZ_ERR_LEVEL_DIV_CD     VARCHAR(3) NOT NULL,
     MZ_ERR_PRIRT_NO     INT,
     OLD_MZ_ERR_ID     VARCHAR(11)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_ERR_MAPPG_BAS ADD CONSTRAINT "TB_ERR_MAPPG_BAS_PK" UNIQUE 
(NE_TYPE_ID, MAPPG_FIELD_ID, FIELD_ODRG, EXP_DT);

ALTER TABLE TB_ERR_MAPPG_BAS ADD PRIMARY KEY 
(NE_TYPE_ID, MAPPG_FIELD_ID, FIELD_ODRG, EXP_DT);













/***********************************************
TB_INTL_PFIX_CHANGE_BAS 생성
*************************************************/
CREATE TABLE TB_INTL_PFIX_CHANGE_BAS
(
carr_id varchar(11) NOT NULL,
edp_id varchar(11) NOT NULL,
exp_dt timestamp NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
efct_st_dt timestamp NOT NULL,
intl_pfix_id varchar(11)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_intl_pfix_change_bas ADD CONSTRAINT "tb_intl_pfix_change_bas_pk" UNIQUE (carr_id,edp_id,exp_dt);



/***********************************************
TB_INTL_PFIX_BAS 생성
*************************************************/
CREATE TABLE TB_INTL_PFIX_BAS
(
intl_pfix_id varchar(11) NOT NULL,
zone_id varchar(11) NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
aux_intl_pfix_id varchar(11),
hngl_cntry_nm varchar(90),
eng_cntry_nm varchar(90)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_intl_pfix_bas ADD CONSTRAINT "tb_intl_pfix_bas_pk" UNIQUE (intl_pfix_id,zone_id);



/***********************************************
TB_CAL_NO_BAS 생성
*************************************************/
CREATE TABLE TB_CAL_NO_BAS
(
pfix_idfy_no varchar(20) NOT NULL,
pfx_tel_no varchar(4) NOT NULL,
indiv_tel_no varchar(4) NOT NULL,
exp_dt timestamp NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
efct_st_dt timestamp NOT NULL,
callg_pfix_sbst varchar(1500),
drop_yn varchar(1) NOT NULL,
setl_drop_yn varchar(1) NOT NULL
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


ALTER TABLE tb_cal_no_bas ADD CONSTRAINT "tb_cal_no_bas_pk" UNIQUE (pfix_idfy_no,pfx_tel_no,indiv_tel_no,exp_dt);




/***********************************************
TB_FEATR_BAS 생성
*************************************************/
CREATE TABLE TB_FEATR_BAS
(
featr_fmt_id varchar(11) NOT NULL,
featr_fmt_type_id varchar(11) NOT NULL,
featr_no varchar(11) NOT NULL,
exp_dt timestamp NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
efct_st_dt timestamp NOT NULL,
featr_type_cd varchar(3) NOT NULL,
featr_id varchar(11) NOT NULL,
drop_yn varchar(1) NOT NULL,
featr_sbst varchar(1500),
arvr_rat_yn varchar(1),
setl_drop_yn varchar(1),
aux_featr_id varchar(11),
cust_hndset_indc_sbst varchar(1500),
indc_yn varchar(1)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_featr_bas ADD CONSTRAINT "tb_featr_bas_pk" UNIQUE (featr_fmt_id,featr_fmt_type_id,featr_no,exp_dt);


/***********************************************
TB_SVC_PRVR_BAS 생성
*************************************************/
CREATE TABLE TB_SVC_PRVR_BAS
(
svc_prvr_id varchar(11) PRIMARY KEY NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
svc_prvr_nm varchar(90),
svc_prvr_type_cd varchar(3)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tb_svc_prvr_bas ADD CONSTRAINT "tb_svc_prvr_bas_pk" UNIQUE (svc_prvr_id);




/***********************************************
TB_NO_MOV_BIZR_BAS 생성
*************************************************/
CREATE TABLE TB_NO_MOV_BIZR_BAS
(
np_id varchar(11) NOT NULL,
exp_dt timestamp NOT NULL,
first_cret_dt timestamp DEFAULT now() NOT NULL,
first_cret_trtr_id varchar(11) NOT NULL,
last_chg_dt timestamp DEFAULT now() NOT NULL,
last_chg_trtr_id varchar(11) NOT NULL,
first_cret_pgm_id varchar(300) NOT NULL,
last_chg_pgm_id varchar(300) NOT NULL,
efct_st_dt timestamp NOT NULL,
setl_file_nm varchar(255),
setl_prvr_div_cd varchar(6),
setl_featr_id varchar(11),
setl_featr_sbst varchar(1500),
innet_yn varchar(1),
aux_setl_featr_id varchar(11)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


ALTER TABLE tb_no_mov_bizr_bas ADD CONSTRAINT "tb_no_mov_bizr_bas_pk" UNIQUE (np_id,exp_dt);




-- 3)테이블명 : TB_FILE_FMT_INFO
CREATE UNLOGGED TABLE TB_FILE_FMT_INFO
(
     CDR_FILE_FMT_ID               VARCHAR(11)          NOT NULL,
     FIRST_CRET_DT               TIMESTAMP          NOT NULL,
     FIRST_CRET_TRTR_ID          VARCHAR(11)          NOT NULL,
     LAST_CHG_DT                    TIMESTAMP          NOT NULL,
     LAST_CHG_TRTR_ID          VARCHAR(11)          NOT NULL,
     FIRST_CRET_PGM_ID          VARCHAR(300)     NOT NULL,
     LAST_CHG_PGM_ID               VARCHAR(300)     NOT NULL,
     CDR_FILE_FMT_TYPE_CD     VARCHAR(3),
     CDR_FILE_NMNG_RULE_SBST     VARCHAR(1500),
     DATA_VRF_DTL_SBST          VARCHAR(1500),
     FILE_NM_LEN                    NUMERIC(5,0)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE TB_FILE_FMT_INFO ADD CONSTRAINT "TB_FILE_FMT_INFO_PK" UNIQUE 
(CDR_FILE_FMT_ID);

ALTER TABLE TB_FILE_FMT_INFO ADD PRIMARY KEY 
(CDR_FILE_FMT_ID);



-- 7)테이블명 : TB_ADIT_HST
CREATE  SEQUENCE  TB_ADIT_HST_SEQ increment 1
    minvalue 1000000001  MAxVALUE  9999999999 cycle
;

CREATE UNLOGGED TABLE TB_ADIT_HST
(
      ADIT_CRET_SEQ       NUMERIC(15,0) NOT NULL DEFAULT NEXTVAL('TB_ADIT_HST_SEQ'),
      FIRST_CRET_DT       TIMESTAMP NOT NULL,
      FIRST_CRET_TRTR_ID  VARCHAR(11) NOT NULL,
      LAST_CHG_DT       TIMESTAMP NOT NULL,
      LAST_CHG_TRTR_ID       VARCHAR(11) NOT NULL,
      FIRST_CRET_PGM_ID       VARCHAR(300) NOT NULL,
      LAST_CHG_PGM_ID       VARCHAR(300) NOT NULL,
      UP_ADIT_SEQ         NUMERIC(15,0) NOT NULL,
      FILE_NM             VARCHAR(255),
       TRM_DIR_NM          VARCHAR(255),
      ORGN_FILE_NM        VARCHAR(255),
      STTUS               VARCHAR(10),
      MODULE_NM           VARCHAR(255),
      RCRD_CNT            NUMERIC(12,0) default 0,
      ERR_CNT             NUMERIC(12,0) default 0,
      PRC_CNT             NUMERIC(12,0) default 0,
       FILE_TYPE           VARCHAR(10)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;