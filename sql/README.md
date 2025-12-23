Creating an Oracle schema (user)

Files created:
- create_dba_schema.sql    -- Privileged script: create tablespace + user
- create_user_only.sql    -- Create only the user on an existing tablespace
- sample_objects.sql      -- Sample objects to run as the new user

Before you run
- Replace placeholders: <USERNAME>, <PASSWORD>, <TABLESPACE_NAME>, <DATAFILE_PATH>, <SIZE>, <QUOTA>, <DEFAULT_TABLESPACE>.
- Datafile paths must be valid on the Oracle database server (not your Windows client).
- Don't run privileged scripts as a normal user. Use SYSDBA or SYSTEM where appropriate.

Run from Windows PowerShell using SQL*Plus (sqlplus) or SQLcl (sql):

Using sqlplus (DBA to create tablespace + user):

```powershell
# Connect as SYSDBA (you will be prompted for password)
sqlplus / as sysdba
-- then run inside sqlplus:
@D:\github\jdbc-samples\sql\create_dba_schema.sql
```

Using sqlplus to run create_user_only.sql as SYSTEM:

```powershell
sqlplus system@//dbhost:1521/ORCL
-- after entering system password at the prompt:
@D:\github\jdbc-samples\sql\create_user_only.sql
```

Run sample objects as the new user (client machine example):

```powershell
sqlplus <USERNAME>/<PASSWORD>@//dbhost:1521/ORCL
@D:\github\jdbc-samples\sql\sample_objects.sql
```

Notes and security
- Avoid storing passwords in plain text scripts; use secure credential stores.
- The scripts use simple grants. Tighten permissions for production.
- If your database uses a listener/TNS alias, adjust connection strings accordingly.

