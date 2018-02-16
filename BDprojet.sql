REM **************************************************************************
REM BASE DE DONNEES PROJET APP WEB SERVEUR
REM JACQUES, PIERRE COUDERC
REM **************************************************************************

CREATE TABLE UTILISATEUR (
	numUtil INTEGER CONSTRAINT pk_util PRIMARY KEY,
	password VARCHAR2(30),
	typeUtil VARCHAR2(30)
	REM typeUtil plutot qu heritage puisqu il n y aurait qu un seul attribut dans les sous tables 
)
/

CREATE TABLE DOCUMENT (
	numDoc INTEGER CONSTRAINT pk_doc PRIMARY KEY,
	nomDoc VARCHAR2(30),
	emprunteur INTEGER CONSTRAINT fk_util FOREIGN KEY REFERENCES UTILISATEUR(numUtil)
)
/

CREATE TABLE LIVRE (
	numLivre INTEGER CONSTRAINT pk_docL PRIMARY KEY,
	REM autres attributs 
)
/

CREATE TABLE DVD (
	numDVD INTEGER CONSTRAINT pk_docD PRIMARY KEY,
	REM autres attributs 
)
/

CREATE TABLE CD (
	numCD INTEGER CONSTRAINT pk_docC PRIMARY KEY,
	REM autres attributs 
)
/

ALTER TABLE LIVRE 
	ADD CONSTRAINT (fk_docL)
	REFERENCES DOCUMENT(numDoc)
	ON DELETE CASCADE
/

ALTER TABLE DVD 
	ADD CONSTRAINT (fk_docD)
	REFERENCES DOCUMENT(numDoc)
	ON DELETE CASCADE
/

ALTER TABLE CD 
	ADD CONSTRAINT (fk_docC)
	REFERENCES DOCUMENT(numDoc)
	ON DELETE CASCADE
/

