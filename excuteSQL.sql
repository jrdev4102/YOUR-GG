--------------------------------------------------------
--  오라클 12c 계정 설정을 위한 세션 변경 
--------------------------------------------------------
ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

--------------------------------------------------------
--  yourgg 계정 및 모든 테이블 삭제
--------------------------------------------------------
DROP USER yourgg CASCADE;

--------------------------------------------------------
--  yourgg 계정 생성
--------------------------------------------------------
CREATE USER yourgg IDENTIFIED BY root;

--------------------------------------------------------
--  yourgg 계정에 DBA 권한 부여
--------------------------------------------------------
GRANT DBA TO yourgg;

--------------------------------------------------------
--  시퀀스 생성 (초기화를 위한 드랍, 에러 날 수 있음)
--------------------------------------------------------
DROP SEQUENCE YOURGG.BOARD_SEQ;
DROP SEQUENCE YOURGG.COMMENT_SEQ;
CREATE SEQUENCE YOURGG.BOARD_SEQ;
CREATE SEQUENCE YOURGG.COMMENT_SEQ;

--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "YOURGG"."BOARD" 
   (	"BOARD_NUMBER" NUMBER, 
	"BOARD_TITLE" VARCHAR2(100), 
	"BOARD_CONTENT" VARCHAR2(4000), 
	"BOARD_WRITER" VARCHAR2(20), 
	"BOARD_REGDATE" DATE, 
	"BOARD_PASSWORD" CHAR(60)
   );

--------------------------------------------------------
--  DDL for Index BOARD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "YOURGG"."BOARD_PK" ON "YOURGG"."BOARD" ("BOARD_NUMBER");
--------------------------------------------------------
--  Constraints for Table BOARD
--------------------------------------------------------

  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_TITLE" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_WRITER" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_REGDATE" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD" ADD CONSTRAINT "BOARD_PK" PRIMARY KEY ("BOARD_NUMBER");
  ALTER TABLE "YOURGG"."BOARD" MODIFY ("BOARD_PASSWORD" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table BOARD_COMMENT
--------------------------------------------------------

  CREATE TABLE "YOURGG"."BOARD_COMMENT" 
   (	"BOARD_NUMBER" NUMBER, 
	"COMMENT_NUMBER" NUMBER, 
	"COMMENT_WRITER" VARCHAR2(20), 
	"COMMENT_CONTENT" VARCHAR2(2000), 
	"COMMENT_REGDATE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Index COMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "YOURGG"."COMENT_PK" ON "YOURGG"."BOARD_COMMENT" ("COMMENT_NUMBER");

--------------------------------------------------------
--  Constraints for Table BOARD_COMMENT
--------------------------------------------------------

  ALTER TABLE "YOURGG"."BOARD_COMMENT" MODIFY ("BOARD_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD_COMMENT" MODIFY ("COMMENT_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD_COMMENT" MODIFY ("COMMENT_WRITER" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD_COMMENT" MODIFY ("COMMENT_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD_COMMENT" MODIFY ("COMMENT_REGDATE" NOT NULL ENABLE);
  ALTER TABLE "YOURGG"."BOARD_COMMENT" ADD CONSTRAINT "COMEMNT_PK" PRIMARY KEY ("COMMENT_NUMBER");
  ALTER TABLE "YOURGG"."BOARD_COMMENT" ADD CONSTRAINT "COMMENT_FK" FOREIGN KEY ("BOARD_NUMBER") REFERENCES "YOURGG"."BOARD" ("BOARD_NUMBER") ON DELETE CASCADE;

--------------------------------------------------------
-- 첫글  더미데이터 생성 
--------------------------------------------------------
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) VALUES ("YOURGG"."BOARD_SEQ".NEXTVAL, '안녕하세요', '첫글입니다', '관리자', SYSDATE, '$2a$10$Djo8h6/3hHeJFFTBb3LbW.Btl0joFPyE4fcrPf1JFsDRnZqg7XNW6');

--------------------------------------------------------
--  더미데이터 생성
--------------------------------------------------------
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");
INSERT INTO "YOURGG"."BOARD" (BOARD_NUMBER, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_REGDATE, BOARD_PASSWORD) (SELECT "YOURGG"."BOARD_SEQ".NEXTVAL, '제목', '내용', '작성자', SYSDATE, '12345' FROM "YOURGG"."BOARD");

