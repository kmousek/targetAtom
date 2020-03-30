# 테스트 시 확인 사항
1. db connection
    * target-atom-collector-volte, target-atom-process프로젝트의    /src/main/resources/application.properties
    * 해당 파일에서 db접속 정보 변경, 수집 포멧 확인
   
2. 기준정보 테이블 생성 스크립트 실행
    * target-atom-jdbc프로젝트 /data/sql/merge_table_gen.ddl 파일을 postgresql에서 테이블 생성
   
3. 필수 기준정보 등록
    * http://10.217.230.251:5000/mediawiki/index.php/TARGET-ATOM
    * WIKI 아래내역 참조하여 필수기준정보 insert
    * 테스트 관련 기준정보 생성
    * 파일:CreateMznRefTabScripts.txt 
    * 기준정보설명
    * TB_CDRSEND_BASE_INFO
    * TB_WFLOW_INFO
    * TB_FILE_FMT_INFO
    * TB_CDRCOLL_BASE_INFO
   
4. 수집하여 process처리된 결과 파일은 아래와 같은 경로에 생성됨.
    * /WRK/SND/NRAT/VOVLTE/GURO1
    * target-atom-process프로젝트의 /src/main/java/com/ktds/targetatom/bean/AuditHandler.java의 sendToDistributor함수에 하드코딩되어있음.

5. audit에서 수집 파일 처리 후 target dir설정하는 부분이 정상처리 안되어 2번과 같이 하드코딩 되어있는듯 함.
    * TB_ADIT_HST.TRM_DIR_NM에 처리된 파일의 경로가 저장되어야 할 것 같은데,,,,,안됨,,,소스?로직? 확인 필요,,,,