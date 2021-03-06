DROP TABLE IF EXISTS AVATAR;

CREATE TABLE AVATAR
(
  ID INTEGER PRIMARY KEY,
  LEVEL INTEGER
);

 DROP TABLE IF EXISTS JETPACK;

 CREATE TABLE JETPACK
 (
   ID INTEGER PRIMARY KEY,
   LEVEL INTEGER
);

 DROP TABLE IF EXISTS TN_USER;

 CREATE TABLE TN_USER
 (
   ID INTEGER PRIMARY KEY,
   USER_NAME VARCHAR,
   DISTANCE INTEGER,
   LEVEL INTEGER,
   AVATAR_ID INTEGER REFERENCES AVATAR(ID),
   JETPACK_ID INTEGER REFERENCES JETPACK(ID),
   PASSWORD VARCHAR,
   POSITION INTEGER
 );
