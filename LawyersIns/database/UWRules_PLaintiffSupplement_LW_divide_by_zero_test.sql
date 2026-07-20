SET NOCOUNT ON;

DECLARE @NumberOfInjuryCasesIn12Month INT = 10;
DECLARE @litigationSum INT = 0;
DECLARE @attorneyCount INT = 1;
DECLARE @ratio INT;

SET @ratio = ((@NumberOfInjuryCasesIn12Month / NULLIF(@litigationSum, 0)) * 100) / @attorneyCount;
IF @ratio IS NOT NULL
	THROW 51000, 'Zero litigationSum must produce NULL instead of an error.', 1;

SET @NumberOfInjuryCasesIn12Month = 201;
SET @litigationSum = 100;
SET @attorneyCount = 2;
SET @ratio = ((@NumberOfInjuryCasesIn12Month / NULLIF(@litigationSum, 0)) * 100) / @attorneyCount;
IF @ratio <> 100
	THROW 51000, 'Non-zero integer-division behavior changed.', 1;

PRINT 'UWRules_PLaintiffSupplement_LW divide-by-zero checks passed.';
