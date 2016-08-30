(ns name-finder.core-test
  (:require [name-finder.core :refer :all]
            [midje.sweet :refer :all]))

(fact "The app should only return 4 letter biblical names that start and contain the appropriate letters"
      (find-names-with-meanings "test/names.txt") => {"ZACH" ", "}
      (parse-file "test/names.txt") => '("ZACH", "ALAN"))


