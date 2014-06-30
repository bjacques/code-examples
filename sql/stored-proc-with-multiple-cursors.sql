--------------------------------------------------------
--  DDL for Package JOB_PKG
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "BRAD"."JOB_PKG" AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
  TYPE job_type IS REF CURSOR;

END JOB_PKG;

/


--------------------------------------------------------
--  DDL for Package STAFF_PKG
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "BRAD"."STAFF_PKG" AS 

  TYPE person_type IS REF CURSOR RETURN BRAD.persons%ROWTYPE;
  
  PROCEDURE getFamilyFirstNames(
    v_lastName IN varchar2,
    c_firstNames OUT person_type,
    c_jobs OUT JOB_PKG.job_type
	);

END STAFF_PKG;

/


--------------------------------------------------------
--  DDL for Package Body STAFF_PKG
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "BRAD"."STAFF_PKG" AS

PROCEDURE getFamilyFirstNames(
	v_lastName IN varchar2,
	c_firstNames OUT person_type,
  c_jobs OUT JOB_PKG.job_type
	)
AS BEGIN
  open c_firstNames for
    SELECT * FROM persons WHERE upper(lastName) = upper(v_lastName);
    
  open c_jobs for
    select * from jobs;
END;

END STAFF_PKG;

/


--------------------------------------------------------
--  Example Procedurte call with multiple OUT params and
--  cursors defined across multiple packages
--------------------------------------------------------

DECLARE
  V_LASTNAME VARCHAR2(200);
  C_FIRSTNAMES BRAD.STAFF_PKG.person_type;
  C_JOBS JOB_PKG.job_type;
BEGIN
  V_LASTNAME := NULL;

  STAFF_PKG.GETFAMILYFIRSTNAMES(
    V_LASTNAME => V_LASTNAME,
    C_FIRSTNAMES => C_FIRSTNAMES,
    C_JOBS => C_JOBS
  );
  /* Legacy output: 
DBMS_OUTPUT.PUT_LINE('C_FIRSTNAMES = ' || C_FIRSTNAMES);
*/ 
  :C_FIRSTNAMES := C_FIRSTNAMES; --<-- Cursor
  /* Legacy output: 
DBMS_OUTPUT.PUT_LINE('C_JOBS = ' || C_JOBS);
*/ 
  :C_JOBS := C_JOBS; --<-- Cursor
--rollback; 
END;
