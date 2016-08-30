(ns name-finder.filters-test
  (:require [name-finder.filters :refer :all]
            [midje.sweet :refer :all]))

(fact "Names should be filtered on length, starting letter and letters contained"
      (filter-length "HARRY") => false
      (filter-length "TONY") => true
      (filter-length "TOM") => false
      (filter-first-letter "ADAM") => true
      (filter-first-letter "RAY") => false
      (filter-first-letter "TOM") => false
      (filter-first-letter "JOE") => false
      (filter-first-letter "EVE") => false
      (filter-first-letter "CAT") => false
      (filter-first-letter "ZOE") => true
      (boolean (contains-one-mandatory "TIM")) => false
      (boolean (contains-one-mandatory "TIA")) => true
      (boolean (contains-one-mandatory "RIA")) => true
      )