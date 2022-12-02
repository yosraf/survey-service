# survey-service
## Rest API
## Create a new survey
### Request

`POST http://localhost:8080/api/http/v1/surveys`

    curl --location --request POST 'localhost:8080/api/http/v1/surveys' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "first survey",
    "questions": [
    {
    "title": "how are you doing?",
    "options": [
        {"optionValue": "good},
        {"optionValue": "excellent"},
        {"optionValue": "not good"}
        ]
    }]}'
### Response
    {"uuid":"36453349-e82d-4b5b-b7e6-4fe6452f5742","name":"first survey","questions":[{"title":"how are you doing?","options":[{"optionValue":"good"},{"optionValue":"excellent"},{"optionValue":"not good"}]}]}
