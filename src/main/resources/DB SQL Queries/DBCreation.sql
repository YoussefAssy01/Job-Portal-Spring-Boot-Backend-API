use JobPortal;


BEGIN TRANSACTION;

BEGIN TRY
	create table Employer(
		id BIGINT IDENTITY(1,1) primary key,
		name nvarchar(50) not null,
		email nvarchar(100) unique not null,
		company_name nvarchar(100),
		info NVARCHAR(MAX)
	)
	create table Job_seeker(
		id BIGINT IDENTITY(1,1) primary key,
		name nvarchar(50) not null,
		email nvarchar(100) unique not null,
		resume_link nvarchar(100)
	)

	create table Admin(
		id BIGINT IDENTITY(1,1) primary key,
		name nvarchar(50) not null,
		email nvarchar(100) unique not null
	)

	create table Job(
		id BIGINT IDENTITY(1,1) primary key,
		title nvarchar(50) not null,
		description nvarchar(150),
		location nvarchar(100) not null,
		salary DECIMAL(12,2) default 0,
		status char(3) not null default 'PEN',
		employer_id bigint not null,
		posted_at DATETIME NOT NULL DEFAULT GETDATE(),
		CONSTRAINT fk_job_employer FOREIGN KEY (employer_id)
        REFERENCES Employer(id)
		on update cascade
        ON DELETE CASCADE,
		CONSTRAINT chk_job_status CHECK (status IN ('PEN', 'APP', 'REJ'))
)

	CREATE TABLE Application (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    job_id BIGINT NOT NULL,
    job_seeker_id BIGINT NOT NULL,
    status char(3) NOT NULL DEFAULT 'PEN',
    CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES Job(id),
    CONSTRAINT fk_app_seeker FOREIGN KEY (job_seeker_id) REFERENCES Job_seeker(id),
    CONSTRAINT uq_job_seeker UNIQUE (job_id, job_seeker_id),
    CONSTRAINT chk_app_status CHECK (status IN ('PEN', 'APP', 'REJ'))
)

	CREATE INDEX idx_job_location_posted ON Job(location, posted_at);
	CREATE INDEX idx_Application_Jobseekerid ON Application(job_seeker_id);
	CREATE INDEX idx_Application_Jobid ON Application(job_id);

    COMMIT TRANSACTION;
    PRINT 'DB created successfully.';
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION; 
    PRINT 'failed to create DB and rolled back.';
    PRINT ERROR_MESSAGE(); 
END CATCH;




