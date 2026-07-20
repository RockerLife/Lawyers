USE [accinsurancedb]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[AccountManagementSearchLW_p]
(
	@AccountName varchar(50),
	@AccountNumber varchar(20),
	@EmailId varchar(150),
	@PolicyEffectiveMonthYear varchar(10),
	@PhoneNumber varchar(100),
	@StateCode varchar(50),
	@isBrokered char(1)
)
AS
BEGIN
	SET NOCOUNT ON;

	SET @AccountName = LTRIM(RTRIM(ISNULL(@AccountName, '')));
	SET @AccountNumber = LTRIM(RTRIM(ISNULL(@AccountNumber, '')));
	SET @EmailId = LTRIM(RTRIM(ISNULL(@EmailId, '')));
	SET @PolicyEffectiveMonthYear = LTRIM(RTRIM(ISNULL(@PolicyEffectiveMonthYear, '')));
	SET @PhoneNumber = LTRIM(RTRIM(ISNULL(@PhoneNumber, '')));
	SET @StateCode = LTRIM(RTRIM(ISNULL(@StateCode, '')));
	SET @isBrokered = ISNULL(@isBrokered, 'N');

	DECLARE @AccountNameLike varchar(51);
	DECLARE @EmailLike varchar(151);
	DECLARE @AccountNumberInt int;
	DECLARE @PolicyEffectiveStartDate datetime;
	DECLARE @PolicyEffectiveEndDate datetime;

	IF @AccountName <> ''
		SET @AccountNameLike = @AccountName + '%';

	IF @EmailId <> ''
		SET @EmailLike = @EmailId + '%';

	IF @AccountNumber <> ''
		SET @AccountNumberInt = CONVERT(int, @AccountNumber);

	IF @PolicyEffectiveMonthYear <> '' AND CHARINDEX('/', @PolicyEffectiveMonthYear) > 0
	BEGIN
		DECLARE @Month varchar(2);
		DECLARE @Year varchar(4);

		SET @Month = SUBSTRING(@PolicyEffectiveMonthYear, 1, CHARINDEX('/', @PolicyEffectiveMonthYear) - 1);
		SET @Year = SUBSTRING(@PolicyEffectiveMonthYear, CHARINDEX('/', @PolicyEffectiveMonthYear) + 1, 4);
		SET @PolicyEffectiveStartDate = CONVERT(datetime, @Year + RIGHT('0' + @Month, 2) + '01', 112);
		SET @PolicyEffectiveEndDate = DATEADD(month, 1, @PolicyEffectiveStartDate);
	END

	CREATE TABLE #Accounts
	(
		AccountName varchar(100) NOT NULL,
		AccountID int NOT NULL,
		isBrokeragePolicy char(1) NOT NULL
	);

	DECLARE @Sql nvarchar(max);
	DECLARE @ParamDefinition nvarchar(1000);

	SET @ParamDefinition = N'
		@AccountNameLike varchar(51),
		@EmailLike varchar(151),
		@AccountNumberInt int,
		@PhoneNumber varchar(100),
		@StateCode varchar(50),
		@PolicyEffectiveStartDate datetime,
		@PolicyEffectiveEndDate datetime';

	SET @Sql = N'
;WITH FirstPolicyTransaction AS
(
	SELECT PolicyKey, MIN(TransactionSequence) AS TransactionSequence
	FROM PolicyTransaction WITH (NOLOCK)
	GROUP BY PolicyKey
),
FirstFirm AS
(
	SELECT PolicyKey, MIN(VersionSequence) AS VersionSequence
	FROM FirmLW WITH (NOLOCK)
	GROUP BY PolicyKey
)
INSERT INTO #Accounts (AccountID, AccountName, isBrokeragePolicy)
SELECT DISTINCT
	p.AccountID,
	UPPER(LTRIM(RTRIM(at.AccountName))) AS AccountName,
	''N'' AS isBrokeragePolicy
FROM Policy p WITH (NOLOCK)
INNER JOIN FirstPolicyTransaction fpt
	ON fpt.PolicyKey = p.PolicyKey
INNER JOIN PolicyTransaction pt WITH (NOLOCK)
	ON pt.PolicyKey = fpt.PolicyKey
	AND pt.TransactionSequence = fpt.TransactionSequence
INNER JOIN Account at WITH (NOLOCK)
	ON at.AccountKey = pt.AccountKey
INNER JOIN AccountAddress act WITH (NOLOCK)
	ON act.AccountKey = pt.AccountKey
INNER JOIN Address adds WITH (NOLOCK)
	ON adds.AddressKey = act.AddressKey
INNER JOIN FirstFirm ff
	ON ff.PolicyKey = p.PolicyKey
INNER JOIN FirmLW f WITH (NOLOCK)
	ON f.PolicyKey = ff.PolicyKey
	AND f.VersionSequence = ff.VersionSequence
WHERE p.MarketableProductKey = 2
	AND f.isUncompletedData <> ''Y''';

	IF @AccountName <> ''
		SET @Sql = @Sql + N'
	AND at.AccountName LIKE @AccountNameLike';

	IF @EmailId <> ''
		SET @Sql = @Sql + N'
	AND at.AccountEmail LIKE @EmailLike';

	IF @AccountNumber <> ''
		SET @Sql = @Sql + N'
	AND p.AccountID = @AccountNumberInt';

	IF @PhoneNumber <> ''
		SET @Sql = @Sql + N'
	AND adds.WorkPhone = @PhoneNumber';

	IF @StateCode <> ''
		SET @Sql = @Sql + N'
	AND adds.StateCode = @StateCode';

	IF @PolicyEffectiveStartDate IS NOT NULL
		SET @Sql = @Sql + N'
	AND p.PolicyEffectiveDate >= @PolicyEffectiveStartDate
	AND p.PolicyEffectiveDate < @PolicyEffectiveEndDate';

	EXEC sp_executesql
		@Sql,
		@ParamDefinition,
		@AccountNameLike = @AccountNameLike,
		@EmailLike = @EmailLike,
		@AccountNumberInt = @AccountNumberInt,
		@PhoneNumber = @PhoneNumber,
		@StateCode = @StateCode,
		@PolicyEffectiveStartDate = @PolicyEffectiveStartDate,
		@PolicyEffectiveEndDate = @PolicyEffectiveEndDate;

	IF @isBrokered = 'Y'
	BEGIN
		SET @Sql = N'
INSERT INTO #Accounts (AccountID, AccountName, isBrokeragePolicy)
SELECT DISTINCT
	br.AccountID,
	UPPER(LTRIM(RTRIM(br.AccountName))) AS AccountName,
	''Y'' AS isBrokeragePolicy
FROM BrokerageLW br WITH (NOLOCK)
WHERE 1 = 1';

		IF @AccountName <> ''
			SET @Sql = @Sql + N'
	AND br.AccountName LIKE @AccountNameLike';

		IF @EmailId <> ''
			SET @Sql = @Sql + N'
	AND br.Email LIKE @EmailLike';

		IF @AccountNumber <> ''
			SET @Sql = @Sql + N'
	AND br.AccountID = @AccountNumberInt';

		IF @PhoneNumber <> ''
			SET @Sql = @Sql + N'
	AND br.Phone = @PhoneNumber';

		IF @StateCode <> ''
			SET @Sql = @Sql + N'
	AND br.State = @StateCode';

		IF @PolicyEffectiveStartDate IS NOT NULL
			SET @Sql = @Sql + N'
	AND br.BrokerPolicyEffectiveDate >= @PolicyEffectiveStartDate
	AND br.BrokerPolicyEffectiveDate < @PolicyEffectiveEndDate';

		EXEC sp_executesql
			@Sql,
			@ParamDefinition,
			@AccountNameLike = @AccountNameLike,
			@EmailLike = @EmailLike,
			@AccountNumberInt = @AccountNumberInt,
			@PhoneNumber = @PhoneNumber,
			@StateCode = @StateCode,
			@PolicyEffectiveStartDate = @PolicyEffectiveStartDate,
			@PolicyEffectiveEndDate = @PolicyEffectiveEndDate;
	END

	CREATE CLUSTERED INDEX IX_Accounts_Search
		ON #Accounts (isBrokeragePolicy, AccountName, AccountID);

	;WITH DistinctAccounts AS
	(
		SELECT AccountName, AccountID, isBrokeragePolicy
		FROM #Accounts
		GROUP BY AccountName, AccountID, isBrokeragePolicy
	)
	SELECT
		ROW_NUMBER() OVER (ORDER BY isBrokeragePolicy, AccountName, AccountID) + 9 AS SN,
		AccountName AS FirmName,
		AccountID AS AccountNumber,
		AccountID,
		AccountName,
		isBrokeragePolicy
	FROM DistinctAccounts
	ORDER BY isBrokeragePolicy, SN;
END
GO
