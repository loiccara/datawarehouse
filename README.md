# Work actually done
- I unfortunately had to timebox my work, so not everything is done. Some decisions were taken to work faster, some 
decisions were taken to highlight some skills.
- I worked with TDD on the search part, but I did not focus much on the read part. 
- The ingestion of the CSV should have been handled with a CRON job or springBatch. I created a small endpoint to 
trigger the ingestion instead, for the sake of getting it done quicker.
- I do not believe that such an API would be a good datawarehouse if I was relying on multiple SQL queries covering all
different possibilities. If we increase the number of metrics, or the number of dimensions, the number of possibilities
grows exponentially. That is why I decided to build a query builder.
- A better solution would have been using either some kind of SQL/HQL/QueryDSL builder for grouping as well. But as I
chose to go for JPA Specifications, it was becoming a mess to do it quickly. Also, grouping on the server side and not 
the DB side helped highlighting some groovy skills. It is however not how this should be developed in a company.
- in a better solution, the selection of which metric should be returned would be handled by the actual DB request, not 
in the service layer. I decided to always add the CTR for the time being.
- this whole solution is missing a lot the point of a datawarehouse, which is supposed to handle very fast queries on 
large amounts of data, partly with the use of projections. I only ingested the data in a h2 database, without creating 
actual projections in the database (such as obvious grouping per day or month, obvious grouping by regular dimensions)
- the h2 console is available for the sake of this exercise, but should never be available on PROD

# How to run
- execute at the root of the project:
> ./gradlew bootrun

# How to use
- trigger the ingestion of the csv file (this part can take a moment)
>curl -v -X POST localhost:8080/read
- verify that everything is in the database with the H2 console (Check parameters in application.properties)
> http://localhost:8080/h2-console/login.do
- query without grouping
> curl -v -X POST localhost:8080/search -H "Content-Type: application/json" -d '{"metrics":["CLICKS", "IMPRESSIONS", "CTR"], "filters":[{"dimension":"DATASOURCE", "value":"Google Ads"}, {"dimension":"CAMPAIGN", "value":"GDN_Retargeting"}]}'
- query with grouping
> curl -v -X POST localhost:8080/search -H "Content-Type: application/json" -d '{"metrics":["CLICKS", "IMPRESSIONS", "CTR"], "groupingBy":"CAMPAIGN", "filters":[{"dimension":"DATASOURCE", "value":"Google Ads"}, {"dimension":"CAMPAIGN", "value":"GDN_Retargeting"}]}'