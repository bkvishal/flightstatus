Flight Status Serivce App
-------------------
**Scope**:
- This app is a minimalistic implementation of Flight Status search service
- This app for the time being uses a third party data source other than own database or ElasticSearch connectivity
- This app is not a finished one but only a proof of concept type solution
- This app acts individually without any business dependency hence can be used as a microservice/API wrapper for suitable solutions

**Usage**:
- Clone the app 
- Build
- Run the app without any profile [_for example_: `mvn spring-boot:run`]
- The app will be running in port `:8080`
- The flight status route is: `/flight-status`
- Allowed params are: 
  - `flightPrefix`: the short code used by airlines [_for example:_ `EW` for `EuroWings`]
  - `flightNo`: the numeric flight number provided [_for example:_ `2465`]
  - `travelDate`: the date of travel [_date format_: `YYYY-MM-DD` - _for example:_ `2020-01-13`]

**Docker and ELK Stack**
- Run `docker-compose up -d`
- Run `mvn spring-boot:run`
- Then hit the exposed route (`/flight-status?flightPrefix=EW&flightNo=2465&travelDate=2020-01-14`) as described above
- Then pointing `kibana` at `http://localhost:5601` will show the logs
  
**Known Issues**:
- The third party data source I have used is a free service, so it might have some limitation with respect to maximum number of calls per day, etc.
- For the same reason, some downtime can also be expected.