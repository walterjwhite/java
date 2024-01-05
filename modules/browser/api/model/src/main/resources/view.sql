-- view
create or replace view NEXTCRAWL as

select
	id,
	version,
	breadthfirst,
	crawlfrequency,
	maxdepth
from (
select
	C.ID,
	C.version,
	C.BREADTHFIRST,
	C.CRAWLFREQUENCY,
	C.MAXDEPTH,

	MCS.CRAWL_ID,
	MCS.MID,
	case
		when (END_DATE is NULL) then NOW()
		when (crawl_frequency = 'Hourly') then now() + interval '1 HOUR'
		when (crawl_frequency = 'Daily') then now() + interval '1 DAY'
		when (crawl_frequency = 'Weekly') then now() + interval '1 WEEK'
		when (crawl_frequency = 'Monthly') then now() + interval '1 MONTH'
		else NOW() + interval '1 DAY'
	end as NEXTRUN

from
	PUBLIC.CRAWL C
	left outer join (
		select
			CRAWL_ID,
			MAX(ID) as MID
		from
			CRAWLSESSION CS
		group by CRAWL_ID
	) MCS on MCS.CRAWL_ID = C.ID
	left outer join CRAWLSESSION CS on CS.ID = MCS.MID
order by
	NEXTRUN asc nulls FIRST
limit 1
) as crawl
where
	NEXTRUN >= now();

--select * from nextcrawl;