CREATE LOGIN redata 
    WITH PASSWORD = 'T0taldata';
GO

-- Creates a database user for the login created above.
CREATE USER redata FOR LOGIN redata;
GO