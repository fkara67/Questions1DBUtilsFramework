@db
  Feature: Homework DB verification

    ##1 - Find out the customer id, name, surname, the city and the country of the customers who
    #    make the most purchases and how much money they have spent ?

    Scenario Outline: Verify the homework first questions's answer with DB
      Given "<customer_id>" "<fullname>" "<city>" "<country>" "<sum>" should match the DB record

      Examples:
        | customer_id | fullname     | city        | country | sum    |
        | 148         | Eleanor Hunt | Saint-Denis | Runion  | 211.55 |