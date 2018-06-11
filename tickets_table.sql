--------------------------------------------------------
--  File created - Monday-June-11-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table T_TICKET
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."T_TICKET" 
   (	"TDATE" VARCHAR2(20 BYTE), 
	"ID" NUMBER, 
	"DESCRIPTION" VARCHAR2(100 BYTE), 
	"USER_ID" NUMBER, 
	"TNUMBER" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index T_TICKET_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."T_TICKET_PK" ON "SYSTEM"."T_TICKET" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger T_TICKET_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."T_TICKET_TRG" 
BEFORE INSERT ON T_TICKET 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."T_TICKET_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_TICKET_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."T_TICKET_TRG1" 
BEFORE INSERT ON T_TICKET 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT T_TICKET_SEQ1.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."T_TICKET_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table T_TICKET
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."T_TICKET" ADD CONSTRAINT "T_TICKET_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."T_TICKET" MODIFY ("ID" NOT NULL ENABLE);
