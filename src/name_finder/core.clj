(ns name-finder.core
  (:require [clojure.string :as str]
            [name-finder.filters :refer [name-filter]]
            [name-finder.meanings :refer [fetch-wikis]])
  (:gen-class))

(defn parse-file 
  "Takes a file containing a list of names and 
   applies the name-filter filter in the filters namespace"
  [file]
  (filter name-filter (take 1000 (map str/trim (str/split-lines (slurp file))))))

(defn find-names-with-meanings 
  "Takes a file containing a list of names and 
   applies the filters in the filters namespace before 
   looking up the meanings on wikipedia and 
   returning a map of name to meaning"
  [file]
  (let [names (parse-file file)
        meanings (fetch-wikis (set (map first names)))]
    (into (sorted-map) (map #(hash-map %1 (meanings %1)) (filter #(get meanings %1) names)))
    ))

(defn -main
  "Read the file input as the first argument and return the allowed names"
  [& args]
  (let [names (find-names-with-meanings (first args))]
    (doall (map #(println (first %1) (second %1)) names)))
)
