/*
Oracle: where douplicate entries exist, pick only the first occurence based on ordering by two columns
*/
select *
from (
	select COLA, COLB, COLC, row_number()
	over (partition by COLD asc nulls last, COLE desc nulls last) rn
	from TBL
	where COLA = 'Y'
)
where rn = 1;